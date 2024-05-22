package com.collectionpoints.usecase.file;

import lombok.RequiredArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@RequiredArgsConstructor
public class FileUseCase {

    public void uploadFile(String collectionPointName, String originalFileName, byte[] content) throws IOException {
        if(originalFileName.split("\\.")[1].equals("pdf")){
            var idFileData = new File("/tmp/" + collectionPointName + ".pdf");
            Files.write(Path.of(idFileData.getAbsolutePath()), content, StandardOpenOption.CREATE);
            System.out.println("File " + originalFileName + " saved succesfully...");
        }
    }

    public byte[] downLoadFile(String fileName) throws IOException {
        System.out.println("File " + fileName + " being downloaded...");
        return Files.readAllBytes(Path.of("/tmp/" + fileName));
    }

}
