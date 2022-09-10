package net.demo.fileuploaddownload.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.demo.fileuploaddownload.services.UploadService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FileUploadDownloadHandler {
    private final UploadService uploadService;

    public Mono<ServerResponse> upload(ServerRequest request) {
        return request.multipartData()
                .flatMap(stringPartMultiValueMap -> {
                    var stringPartMap= stringPartMultiValueMap.toSingleValueMap();
                    var filePart = (FilePart) stringPartMap.get("file");
                    return uploadService.upload(filePart,"/tmp/");
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
