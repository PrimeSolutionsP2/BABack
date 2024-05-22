package co.com.collections.jpa;

import co.com.collections.jpa.entity.*;
import co.com.collections.jpa.helper.AdapterOperations;
import co.com.collections.model.collectionpoint.CollectionPointCustom;
import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.PickupRequestCustom;
import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import co.com.collections.model.user.User;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import co.com.collections.model.pickuprequest.dto.CollectionByStateHistoricDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PickupRequestRepositoryAdapter extends AdapterOperations<PickupRequest, PickupRequestEntity, String, PickupRequestRepository>
implements co.com.collections.model.pickuprequest.gateways.PickupRequestRepository
{

    private final PickupRequestCustomRepository repositoryCustom;

    public PickupRequestRepositoryAdapter(PickupRequestRepository repository, PickupRequestCustomRepository repositoryCustom,ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d,PickupRequest.PickupRequestBuilder.class).build());
        this.repositoryCustom = repositoryCustom;
    }


    @Override
    public PickupRequest getPickupRequest(String id) {
        return this.findById(id);
    }

    @Override
    public PickupRequest updatePickupRequest(PickupRequest pickupRequest) {
        return this.save(pickupRequest);
    }

    @Override
    public PickupRequest createPickupRequest(PickupRequest pickupRequest) {
        return this.save(pickupRequest);
    }

    @Override
    public List<CollectionByStateHistoricDTO> getCollectionsByStateHistoric() {
        List<CollectionByStateHistoricDTO> collectionByStateHistoricDTO = this.repositoryCustom.findCollectionByStatesHistoric();
        return collectionByStateHistoricDTO;
    }
    @Override
    public List<PickupRequestCustom> getPickupRequestsCustom(Integer pickupRequestStatusId, String searchFilterValue, String recollectorUserId) {
        List<PickupRequestCustomEntity> pickupRequestEntities  = this.repositoryCustom.findByFilters(pickupRequestStatusId, searchFilterValue, recollectorUserId);
        List<PickupRequestCustom> pickupRequests  = new ArrayList<>();
        for (PickupRequestCustomEntity pickupRequestEntity : pickupRequestEntities ) {
            UserEntity userEntity = pickupRequestEntity.getUser();
            User user = null;
            if (userEntity != null) {
                user = User.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .lastName(userEntity.getLastName())
                        .email(userEntity.getEmail())
                        .phoneNumber(userEntity.getPhoneNumber())
                        .roleId(userEntity.getRoleId())
                        .build();
            }
            CollectionPointCustomEntity collectionPointCustomEntity = pickupRequestEntity.getCollectionPoint();
            UserEntity collectionPointOwnerEntity = collectionPointCustomEntity.getUser();
            User collectionPointOwner = User.builder()
                    .id(collectionPointOwnerEntity.getId())
                    .name(collectionPointOwnerEntity.getName())
                    .lastName(collectionPointOwnerEntity.getLastName())
                    .email(collectionPointOwnerEntity.getEmail())
                    .phoneNumber(collectionPointOwnerEntity.getPhoneNumber())
                    .roleId(collectionPointOwnerEntity.getRoleId())
                    .build();
            CollectionPointCustom collectionPoint = CollectionPointCustom.builder()
                    .id(collectionPointCustomEntity.getId())
                    .user(collectionPointOwner)
                    .agreementCode(collectionPointCustomEntity.getAgreementCode())
                    .address(collectionPointCustomEntity.getAddress())
                    .city(collectionPointCustomEntity.getCity())
                    .state(collectionPointCustomEntity.getState())
                    .country(collectionPointCustomEntity.getCountry())
                    .build();
            PickupRequestStatusEntity pickupRequestStatusEntity = pickupRequestEntity.getPickupRequestStatus();
            PickupRequestStatus pickupRequestStatus = PickupRequestStatus.builder()
                    .id(pickupRequestStatusEntity.getId())
                    .name(pickupRequestStatusEntity.getName())
                    .build();
            PickupRequestCustom pickupRequest = PickupRequestCustom.builder()
                    .id(pickupRequestEntity.getId())
                    .user(user)
                    .commentary(pickupRequestEntity.getCommentary())
                    .pickupDate(pickupRequestEntity.getPickupDate())
                    .dateCreate(pickupRequestEntity.getDateCreate())
                    .pickupRequestStatus(pickupRequestStatus)
                    .collectionPoint(collectionPoint)
                    .kilograms(pickupRequestEntity.getKilograms())
                    .build();

            pickupRequests.add(pickupRequest);

        }
        return pickupRequests;
    }

}
