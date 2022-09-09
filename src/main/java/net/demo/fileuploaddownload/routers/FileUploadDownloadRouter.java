package net.demo.fileuploaddownload.routers;

import net.demo.fileuploaddownload.handlers.FileUploadDownloadHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FileUploadDownloadRouter {
    @Bean
    public RouterFunction<ServerResponse> router(FileUploadDownloadHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/upload"), handler::upload)
                .andRoute(RequestPredicates.POST("/azure/upload"), handler::azureUpload)
                .andRoute(RequestPredicates.GET("/download"), handler::download)
                .andRoute(RequestPredicates.GET("/azure/download"), handler::azureDownload);
    }
}
