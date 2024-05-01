package com.collectionpoints.api;
import com.collectionpoints.api.utils.GenericResponse;
import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.model.exception.CustomException;
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
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "statusId", required = false) String statusId
    ) {
        CollectionPointFilter cpf = CollectionPointFilter.builder()
                .search(search)
                .statusId(statusId)
                .build();

        GenericResponse<List<CollectionPoint>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getAll(cpf), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> getById(@PathVariable(name = "id") Integer id) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getById(id), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/request")
    public ResponseEntity<GenericResponse<CollectionPoint>> request(@RequestBody CollectionPointRequest collectionPointRequest) {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.requestCollectionPoint(collectionPointRequest), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/change-status/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> changeStatus(@PathVariable(name = "id") Integer id, @RequestBody CollectionPointStatusChange collectionPointStatusChange) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.changeStatus(id, collectionPointStatusChange), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    ResponseEntity<GenericResponse<CollectionPoint>> updateCollectionPoint(@PathVariable(name = "id") Integer id, @RequestBody CollectionPoint collectionPoint) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.updateCollectionPoint(id, collectionPoint), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
