package com.collectionpoints.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import com.collectionpoints.api.utils.GenericResponse;
import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.model.exception.CustomException;
import com.collectionpoints.usecase.CollectionPointUseCase;
import com.collectionpoints.usecase.file.FileUseCase;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/collectionPoints")
public class ApiRest {

    private final Path fileStorageLocation;
    private final CollectionPointUseCase useCase;
    private final FileUseCase fileUseCase;

    public ApiRest(CollectionPointUseCase useCase, FileUseCase fileUseCase) {
        this.fileStorageLocation =  Paths.get("/tmp").toAbsolutePath().normalize();;
        this.useCase = useCase;
        this.fileUseCase = fileUseCase;
    }

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
            @RequestParam(name = "status", required = false) String status
    ) {
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

    @PostMapping("/uploadIdFile/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> uploadIdFile(@RequestParam("idFile") MultipartFile idFile,
                                                                            @PathVariable("id") Integer id) throws IOException, CustomException {
        fileUseCase.uploadFile("ID-" + id, Objects.requireNonNull(idFile.getOriginalFilename()), idFile.getBytes());
        CollectionPoint collectionPointToUpdate = useCase.getById(id);
        collectionPointToUpdate.setUserIdFile(true);
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.updateCollectionPoint(id, collectionPointToUpdate), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/uploadPlaceImage/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> uploadPlaceImage(@RequestParam("idFile") MultipartFile idFile,
                                                                            @PathVariable("id") Integer id) throws IOException, CustomException {
        fileUseCase.uploadFile("IMAGE-" + id, Objects.requireNonNull(idFile.getOriginalFilename()), idFile.getBytes());
        CollectionPoint collectionPointToUpdate = useCase.getById(id);
        collectionPointToUpdate.setPlaceImage(true);
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.updateCollectionPoint(id, collectionPointToUpdate), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> changeStatus(@PathVariable(name = "id") Integer id, @RequestBody CollectionPointStatusChange collectionPointStatusChange) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.changeStatus(id, collectionPointStatusChange), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    ResponseEntity<GenericResponse<CollectionPoint>> updateCollectionPoint(@PathVariable(name = "id") Integer id, @RequestBody CollectionPoint collectionPoint) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.updateCollectionPoint(id, collectionPoint), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable(name = "fileName") String fileName, HttpServletRequest request) throws IOException {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (IOException ex) {
            throw new IOException("File not found " + fileName);
        }
    }
}
