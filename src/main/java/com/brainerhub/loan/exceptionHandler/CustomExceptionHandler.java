package com.brainerhub.loan.exceptionHandler;

import com.brainerhub.loan.responseDto.APIResponseBuilder;
import com.brainerhub.loan.responseDto.GenericResponse;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getRequestParameterException() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Request parameters invalid", HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getMethodNotSupportedException() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Method not supported", HttpStatus.METHOD_NOT_ALLOWED));
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getNullPointerException() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Data not Found", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getUnsupportedEncoding() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Error encoding parameter", HttpStatus.NOT_ACCEPTABLE));
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getUnauthorizedException(HttpClientErrorException.Unauthorized e) {
        return ResponseEntity.ok(APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), HttpStatus.UNAUTHORIZED));
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.ok(APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getRuntimeException() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Error runtime exception", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getNoHandlerFoundException() {
        return ResponseEntity
                .ok(APIResponseBuilder.build(Boolean.FALSE, "Error no handler exception", HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(GeneralSecurityException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getGeneralSecurityException() {
        return ResponseEntity.ok(
                APIResponseBuilder.build(Boolean.FALSE, "Error security exception", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getBindException(BindException e) {
        return ResponseEntity.ok(
                APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> getDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.ok(
                APIResponseBuilder.build(Boolean.FALSE, e.getMessage(), HttpStatus.CONFLICT));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<GenericResponse> handleInvalidArgument(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                APIResponseBuilder.build(Boolean.FALSE, errors.toString(), HttpStatus.BAD_REQUEST));
    }

}
