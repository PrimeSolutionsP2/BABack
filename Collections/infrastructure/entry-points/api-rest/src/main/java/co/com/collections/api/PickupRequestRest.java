package co.com.collections.api;
import co.com.collections.api.response.GenericResponse;
import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.model.pickuprequest.PickupRequestCustom;
import co.com.collections.usecase.pickuprequest.PickupRequestUseCase;
import co.com.collections.util.validator.ValidationException;
import co.com.collections.api.validator.PickupRequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/collections", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PickupRequestRest {

    private final PickupRequestUseCase useCase;
    private final PickupRequestValidator validator;

    @GetMapping(path = "/")
    public String commandName() {
        return "Hello World";
    }

    @PostMapping(path = "/createRequestCollection")
    public ResponseEntity<GenericResponse<Void>> createRequestCollection(@RequestBody PickupRequest pickupRequest) {
        try{
            validator.validate(pickupRequest);
            useCase.createPickupRequest(pickupRequest);
            return ResponseEntity.ok(new GenericResponse<>("Solicitud de recogida creada exitosamente", HttpStatus.OK.value(), null));
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException || e instanceof ValidationException) {
                return ResponseEntity.badRequest().body(new GenericResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null));
        }

    }

    @GetMapping(path = "/requestCollectionsAdmin")
    public ResponseEntity<GenericResponse<List<PickupRequestCustom>>> getRequestCollectionsAdmin(@RequestParam(required = false) Integer pickupRequestStatusId, @RequestParam(required = false) String filterSearchValue) {
        return ResponseEntity.ok(new GenericResponse<List<PickupRequestCustom>>("OK", HttpStatus.OK.value(), null, useCase.getPickupRequestsCustom(pickupRequestStatusId, filterSearchValue)));
    }
}
