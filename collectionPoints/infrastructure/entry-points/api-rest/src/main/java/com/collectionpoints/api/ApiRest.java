package com.collectionpoints.api;
import com.collectionpoints.api.utils.GenericResponse;
import com.collectionpoints.model.CollectionPoint;
import com.collectionpoints.model.dto.CollectionPointFilter;
import com.collectionpoints.model.dto.CollectionPointRequest;
import com.collectionpoints.model.dto.CollectionPointStatusChange;
import com.collectionpoints.model.dto.SpecificStats;
import com.collectionpoints.model.exception.CustomException;
import com.collectionpoints.usecase.CollectionPointUseCase;
import com.collectionpoints.usecase.file.FileUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/collectionPoints", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<GenericResponse<CollectionPoint>> updateCollectionPoint(@PathVariable(name = "id") Integer id, @RequestParam(name = "role", required = true) String role, @RequestBody CollectionPoint collectionPoint) throws CustomException {
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.updateCollectionPoint(id, role, collectionPoint), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/general-stats")
    public ResponseEntity<GenericResponse<HashMap<String, Integer>>> getAllStatesStats(){
        GenericResponse<HashMap<String, Integer>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getAllStatesStats(), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/specific-stats")
    public ResponseEntity<GenericResponse<HashMap<String, Integer>>> getStats(@RequestBody SpecificStats specificStats){
        GenericResponse<HashMap<String, Integer>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getStats(specificStats), "OK");
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

    @PostMapping("/uploadIdFile/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> uploadIdFile(@RequestParam("idFile") MultipartFile idFile,
                                                                         @RequestParam(name = "role", required = true) String role,
                                                                         @PathVariable("id") Integer id) throws IOException, CustomException {
        fileUseCase.uploadFile("ID-" + id, Objects.requireNonNull(idFile.getOriginalFilename()), idFile.getBytes());
        CollectionPoint collectionPointToUpdate = useCase.getById(id);
        collectionPointToUpdate.setUserIdFile(true);
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.updateCollectionPoint(id, role, collectionPointToUpdate), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/uploadPlaceImage/{id}")
    public ResponseEntity<GenericResponse<CollectionPoint>> uploadPlaceImage(@RequestParam("idFile") MultipartFile idFile,
                                                                             @RequestParam(name = "role", required = true) String role,
                                                                             @PathVariable("id") Integer id) throws IOException, CustomException {
        fileUseCase.uploadFile("IMAGE-" + id, Objects.requireNonNull(idFile.getOriginalFilename()), idFile.getBytes());
        CollectionPoint collectionPointToUpdate = useCase.getById(id);
        collectionPointToUpdate.setPlaceImage(true);
        GenericResponse<CollectionPoint> response = new GenericResponse<>(HttpStatus.CREATED.value(), useCase.updateCollectionPoint(id, role, collectionPointToUpdate), "CREATED SUCCESSFULLY");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    @GetMapping("/general-stats")
    public ResponseEntity<GenericResponse<HashMap<String, Integer>>> getAllStatesStats(){
        GenericResponse<HashMap<String, Integer>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getAllStatesStats(), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/specific-stats")
    public ResponseEntity<GenericResponse<HashMap<String, Integer>>> getStats(@RequestBody SpecificStats specificStats){
        GenericResponse<HashMap<String, Integer>> response = new GenericResponse<>(HttpStatus.OK.value(), useCase.getStats(specificStats), "OK");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
