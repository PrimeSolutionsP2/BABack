package com.collectionpoint.api;
import com.collectionpoint.api.utils.GenericResponse;
import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.dto.CollectionPointFilter;
import com.collectionpoint.model.dto.CollectionPointRequest;
import com.collectionpoint.usecase.CollectionPointUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/collectionPoints", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private CollectionPointUseCase useCase;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<CollectionPoint>>> getAll(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "status",required = false) String status
    ) {
        CollectionPointFilter cpf = CollectionPointFilter.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .address(address)
                .city(city)
                .state(state)
                .country(country)
                .status(status)
                .build();

        GenericResponse<List<CollectionPoint>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getAll(cpf), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> getById(@PathVariable(name = "id") Integer id) {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getById(id), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<GenericResponse<CollectionPoint>> request(@RequestBody CollectionPointRequest collectionPointRequest) {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.requestCollectionPoint(collectionPointRequest), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
