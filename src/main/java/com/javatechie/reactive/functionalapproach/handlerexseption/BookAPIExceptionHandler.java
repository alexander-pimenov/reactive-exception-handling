package com.javatechie.reactive.functionalapproach.handlerexseption;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 1 вариант обработчика ошибок для функционального подхода (кастомный), есть
 * еще 2-й вариант (GlobalErrorAttributes), но если использовать и 2-й то он перекроет 1-й.
 *
 * @see GlobalErrorAttributes
 */
@Component
public class BookAPIExceptionHandler extends AbstractErrorWebExceptionHandler {


    /*не забыть сдеалть бин для WebProperties.Resources*/
    public BookAPIExceptionHandler(ErrorAttributes errorAttributes,
                                   WebProperties.Resources resources, //бин нужно создавать в конфигурации
                                   ApplicationContext applicationContext,
                                   ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        //нужны для чтения и записи ошибок через ServerCodecConfigurer
        this.setMessageReaders(configurer.getReaders());
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), //обрабатываем все эндпоинты роута (т.е. только роуты функционального подхода)
                this::renderException    //обработчик ошибок
        );
    }

    private Mono<ServerResponse> renderException(ServerRequest request) {
        Map<String, Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        //если нам не нужны некоторые поля, то удаляем их
        error.remove("status");
        error.remove("requestId");
        //error.remove("path");
        return ServerResponse.status(HttpStatus.BAD_REQUEST) //этот статус также передается и во второй обработчик ошибок GlobalErrorAttributes
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error));

    }
}
