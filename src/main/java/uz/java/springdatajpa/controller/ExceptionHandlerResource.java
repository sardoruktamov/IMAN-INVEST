package uz.java.springdatajpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.java.springdatajpa.dto.ErrorDto;
import uz.java.springdatajpa.dto.ResponseDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestControllerAdvice
public class ExceptionHandlerResource {

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> validationErrorHandler(MethodArgumentNotValidException e){
        List<ErrorDto> collect = e.getBindingResult()
                .getFieldErrors().stream()
                .map(f -> {
                    String fieldName = f.getField();
                    String message = f.getDefaultMessage();
                    String rejectedValue = String.valueOf(f.getRejectedValue());
                    return new ErrorDto(fieldName, message + " Rejected value: " + rejectedValue);
                }).collect(toList());

        return ResponseEntity.badRequest()
                .body(ResponseDto.<Void>builder()
                        .errors(collect)
                        .message("Validation error")
                        .code(1)
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> nullPointerException(NullPointerException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseDto.<Void>builder()
                        .code(2)
                        .message("NullPointerException occurred. Cause: " + errorToString(e))
                        .build());
    }

    private String errorToString(Throwable t){
        StringBuilder errorMessage = new StringBuilder(t.getMessage());
        while (t.getCause() != null){
            t = t.getCause();
            errorMessage.append(System.lineSeparator())
                    .append(t.getMessage());
        }

        return errorMessage.toString();
    }
}
