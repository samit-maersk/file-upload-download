package net.demo.fileuploaddownload.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@Slf4j
public class FileUploadDownloadHandler {
    public Mono<ServerResponse> upload(ServerRequest request) {
        return request.multipartData()
                .flatMap(stringPartMultiValueMap -> {
                    var stringPartMap= stringPartMultiValueMap.toSingleValueMap();
                    var filePart = (FilePart) stringPartMap.get("file");
                    return filePart
                            .transferTo(Path.of("/tmp/"+filePart.filename()))
                            .doOnError(e -> log.error("Error during file transfer to the path {}",e.getMessage()))
                            .doOnSuccess(s -> log.info("File Uploaded Successfully"))
                            .flatMap(res -> {
                                log.info("File Upload Completed {}",res);
                                return ok().body(Mono.just(filePart.filename() + "Uploaded"), String.class);
                            });
                }).then(ok().body(Mono.just("Uploaded"), String.class));
    }

    public Mono<ServerResponse> download(ServerRequest request) {
        return Mono.empty();
    }

    public Mono<ServerResponse> azureUpload(ServerRequest request) {
        return Mono.empty();
    }

    public Mono<ServerResponse> azureDownload(ServerRequest request) {
        return Mono.empty();
    }
}
