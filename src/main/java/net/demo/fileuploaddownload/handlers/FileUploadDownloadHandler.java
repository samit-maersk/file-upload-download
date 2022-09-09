package net.demo.fileuploaddownload.handlers;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class FileUploadDownloadHandler {
    public Mono<ServerResponse> upload(ServerRequest request) {
        return request.multipartData()
                .flatMap(stringPartMultiValueMap -> {
                    Part part = stringPartMultiValueMap.toSingleValueMap().get("file");
                    var name = part.name();
                    var content = part.content();
                    return ServerResponse.ok().body(Mono.just(name), String.class);
                });
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
