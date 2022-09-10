package net.demo.fileuploaddownload.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@Service
@Slf4j
public class UploadService {
    public Mono<String> upload(FilePart filePart, String path) {
        return filePart.transferTo(Path.of(path+filePart.filename()))
                .doOnError(e -> log.error("Error during file transfer to the path {}",e.getMessage()))
                .doOnSuccess(s -> log.info("File Uploaded Successfully"))
                .flatMap(res -> {
                    log.info("File Upload Completed {}",res);
                    return Mono.just(filePart.filename() + "Uploaded");
                });
    }
}
