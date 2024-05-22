package co.com.collections.usecase.pickuprequest;

import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.PickupRequestCustom;
import co.com.collections.model.pickuprequest.dto.CollectionByStateAndDateHistoricDTO;
import co.com.collections.model.pickuprequest.gateways.PickupRequestRepository;
import co.com.collections.model.pickuprequest.dto.CollectionByStateHistoricDTO;
import co.com.collections.usecase.pickuprequest.dto.CompletePickupRequestDTO;
import co.com.collections.usecase.pickuprequest.dto.UpdatePickupRequestRecollectorAndPickupDateDTO;
import co.com.collections.usecase.pickuprequeststatus.PickupRequestStatusUseCase;
import co.com.collections.usecase.util.date.DateUtils;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class PickupRequestUseCase {
    private final PickupRequestRepository pickupRequestRepository;
    private final Integer pickupRequestPendingStatusId = 1;
    public PickupRequest getPickupRequest(String id) {
        return pickupRequestRepository.getPickupRequest(id);
    }

    public PickupRequest updatePickupRequest(PickupRequest pickupRequest) {
        return pickupRequestRepository.updatePickupRequest(pickupRequest);
    }

    public PickupRequest createPickupRequest(PickupRequest data) {


        PickupRequest pickupRequest = PickupRequest.builder()
                .collectionPointId(data.getCollectionPointId())
                .pickupDate(data.getPickupDate())
                .commentary(data.getCommentary())
                .dateCreate(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC))
                .pickupRequestStatusId(this.pickupRequestPendingStatusId)
                .build();
        return pickupRequestRepository.createPickupRequest(pickupRequest);
    }

    public List<PickupRequestCustom> getPickupRequestsCustom(Integer pickupRequestStatusId, String searchFilterValue, String id) {
        return pickupRequestRepository.getPickupRequestsCustom(pickupRequestStatusId, searchFilterValue, null,id);
    }

    public List<PickupRequestCustom> getPickupRequestsCustomRecollector(Integer pickupRequestStatusId, String searchFilterValue, String recollectorId, String id) {
        if(recollectorId == null || recollectorId.isEmpty()) {
            throw new IllegalArgumentException("El id del recolector no puede ser nulo o vacío");
        }
        return pickupRequestRepository.getPickupRequestsCustom(pickupRequestStatusId, searchFilterValue, recollectorId,id);
    }

    public void updatePickupRequestRecollectorAndPickupDate(UpdatePickupRequestRecollectorAndPickupDateDTO updatePickupRequestRecollectorAndPickupDateDTO) {
        PickupRequest pickupRequest = pickupRequestRepository.getPickupRequest(updatePickupRequestRecollectorAndPickupDateDTO.getPickupRequestId());
        if(pickupRequest == null) {
            throw new IllegalArgumentException("Solicitud de recogida no encontrada");
        }
        if(updatePickupRequestRecollectorAndPickupDateDTO.getPickupDate() == null && updatePickupRequestRecollectorAndPickupDateDTO.getRecollectorId() == null) {
            return;
        }
        if(updatePickupRequestRecollectorAndPickupDateDTO.getPickupDate() != null) {
            if (!isFutureDateTime(updatePickupRequestRecollectorAndPickupDateDTO.getPickupDate())) {
                throw new IllegalArgumentException("La fecha de recogida debe ser futura");
            }
            pickupRequest.setPickupDate(updatePickupRequestRecollectorAndPickupDateDTO.getPickupDate());
        }
        if(updatePickupRequestRecollectorAndPickupDateDTO.getRecollectorId() != null) {
            pickupRequest.setUserId(updatePickupRequestRecollectorAndPickupDateDTO.getRecollectorId());
        }

        if(pickupRequest.getPickupDate() != null && pickupRequest.getUserId() != null && pickupRequest.getPickupRequestStatusId() != 2) {
            pickupRequest.setPickupRequestStatusId(PickupRequestStatusUseCase.PICKUP_REQUEST_STATUS_ID_SCHEDULED);
        }
        pickupRequestRepository.updatePickupRequest(pickupRequest);
    }

    private boolean isFutureDateTime(LocalDateTime dateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return dateTime.isAfter(currentDateTime);
    }

    public void completePickupRequest(CompletePickupRequestDTO completePickupRequest) {
        PickupRequest pickupRequest = pickupRequestRepository.getPickupRequest(completePickupRequest.getPickupRequestId());
        if(pickupRequest.getPickupRequestStatusId() != PickupRequestStatusUseCase.PICKUP_REQUEST_STATUS_ID_SCHEDULED) {
            throw new IllegalArgumentException("La solicitud de recogida no está programada");
        }

        pickupRequest.setKilograms(completePickupRequest.getKilogramsRecolected());
        pickupRequest.setPickupRequestStatusId(PickupRequestStatusUseCase.PICKUP_REQUEST_STATUS_ID_COMPLETE);
        pickupRequestRepository.updatePickupRequest(pickupRequest);
    }

    public List<CollectionByStateHistoricDTO> getCollectionsByStateHistoric() {
        return pickupRequestRepository.getCollectionsByStateHistoric();
    }

    public List<CollectionByStateAndDateHistoricDTO> getCollectionsByStateAndDateHistoric(String filterState, Integer filterMonth, Integer filterYear) {
        List<CollectionByStateAndDateHistoricDTO> collections = pickupRequestRepository.getCollectionsByStateAndDateHistoric(filterState, filterMonth, filterYear);

        Map<LocalDateTime, CollectionByStateAndDateHistoricDTO> collectionMap = collections.stream()
                .collect(Collectors.toMap(
                        dto -> dto.getPickupDate().withHour(0).withMinute(0).withSecond(0).withNano(0),
                        dto -> dto
                ));
        List<LocalDateTime> datesInMonth = DateUtils.getDatesInMonth(filterYear, filterMonth);
        List<CollectionByStateAndDateHistoricDTO> completeCollections = new ArrayList<>();
        long cumulativeSum = 0;
        for (LocalDateTime date : datesInMonth) {
            CollectionByStateAndDateHistoricDTO dto;
            if (collectionMap.containsKey(date)) {
                dto = collectionMap.get(date);
                cumulativeSum += dto.getTotalKilogramsCollected();
            } else {
                dto = new CollectionByStateAndDateHistoricDTO(filterState, 0L, date);
            }
            completeCollections.add(new CollectionByStateAndDateHistoricDTO(dto.getState(), cumulativeSum, dto.getPickupDate()));
        }

        return completeCollections;
    }
}
