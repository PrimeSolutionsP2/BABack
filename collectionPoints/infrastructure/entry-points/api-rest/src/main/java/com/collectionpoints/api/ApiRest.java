package com.collectionpoints.api;
import com.collectionpoints.api.utils.GenericResponse;
import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.usecase.CollectionPointUseCase;
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
            @RequestParam(name = "userName", required = false) String userName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "status",required = false) String status
    ) {
        try{
            CollectionPointFilter cpf = CollectionPointFilter.builder()
                    .userId(userId)
                    .userName(userName)
                    .email(email)
                    .name(name)
                    .address(address)
                    .city(city)
                    .state(state)
                    .country(country)
                    .status(status)
                    .build();

            GenericResponse<List<CollectionPoint>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getAll(cpf), "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<List<CollectionPoint>> response = new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> getById(@PathVariable(name = "id") Integer id) {
        try {
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getById(id), "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/request")
    public ResponseEntity<GenericResponse<CollectionPoint>> request(@RequestBody CollectionPointRequest collectionPointRequest) {
        try {
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.requestCollectionPoint(collectionPointRequest), "CREATED SUCCESSFULLY");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> changeStatus(@PathVariable(name = "id") Integer id, @RequestBody CollectionPointStatusChange collectionPointStatusChange) {
        try {
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.changeStatus(id, collectionPointStatusChange), "OK");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "INTERNAL SERVER ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
