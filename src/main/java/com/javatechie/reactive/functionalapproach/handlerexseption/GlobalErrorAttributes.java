package com.javatechie.reactive.functionalapproach.handlerexseption;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 2 вариант обработчика ошибок для функционального подхода, он
 * перекрывает 1-й вариант (BookAPIExceptionHandler).
 *
 * @see BookAPIExceptionHandler
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorMap = new HashMap<>();
        Throwable error = getError(request);
        errorMap.put("message", error.getMessage());
        errorMap.put("endpoint url ", request.path());
        return errorMap;
    }
}
