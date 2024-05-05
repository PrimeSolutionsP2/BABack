package co.com.collections.api;

import co.com.collections.api.response.GenericResponse;
import co.com.collections.model.pickuprequeststatus.PickupRequestStatus;
import co.com.collections.usecase.pickuprequeststatus.PickupRequestStatusUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pickup-request-status", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PickupRequestStatusRest {
    private final PickupRequestStatusUseCase useCase;

    @GetMapping(path = "/status")
    public ResponseEntity<GenericResponse<List<PickupRequestStatus>>> getPickupRequestStatus() {
        return ResponseEntity.ok(new GenericResponse<>("OK", HttpStatus.OK.value(), null, useCase.getPickupRequestStatus()));
    }
}
