package com.collectionpoint.api;
import com.collectionpoint.model.CollectionPoint;
import com.collectionpoint.model.CollectionPointFilter;
import com.collectionpoint.usecase.CollectionPointUseCase;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/collectionPoints", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private CollectionPointUseCase useCase;

    @GetMapping("/")
    public ResponseEntity<List<CollectionPoint>> getAll(
            @RequestParam(name = "userId", required = false) String userId,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "country", required = false) String country,
            @RequestParam(name = "status",required = false) String status
    ) {
        CollectionPointFilter cpf = new CollectionPointFilter(userId, name, email, address, city, state, country, status);
        return new ResponseEntity<>(useCase.getAll(cpf), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionPoint> getById(@PathParam("id") int id) {
        return new ResponseEntity<>(useCase.getById(id), HttpStatus.OK);
    }
}
