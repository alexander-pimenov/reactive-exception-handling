package com.javatechie.reactive.functionalapproach.route;

import com.javatechie.reactive.functionalapproach.handler.BookHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AppRouterConfig {

    @Autowired
    private BookHandler bookHandler;

    /*бин для работы с обработчиком ошибок функционального подхода*/
    @Bean
    public WebProperties.Resources resources(){
        return new WebProperties.Resources();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/route/books", bookHandler::getBooks)
                .GET("/route/books/{bookId}",bookHandler::getBookById)
                .build();
    }
}
