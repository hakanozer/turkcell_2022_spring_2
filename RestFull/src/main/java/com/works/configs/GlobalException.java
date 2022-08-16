package com.works.configs;

import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.errors, errorParse(ex));
        return new ResponseEntity<>( hm, HttpStatus.BAD_REQUEST );
    }

    public List<Map> errorParse( MethodArgumentNotValidException ex ) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<Map> ls = new ArrayList<>();
        for( FieldError error : fieldErrors ) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            Map<String, String> hm = new HashMap<>();
            hm.put("field", field);
            hm.put("message", message);
            ls.add(hm);
        }
        return ls;
    }

}
