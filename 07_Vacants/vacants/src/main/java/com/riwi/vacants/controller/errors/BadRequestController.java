// package com.riwi.vacants.controller.errors;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import com.riwi.vacants.utils.DTO.errors.BaseErrorResponse;
// import com.riwi.vacants.utils.DTO.errors.ErrorResponse;
// import com.riwi.vacants.utils.exceptions.IdNotFoundException;

// @RestControllerAdvice
// @ResponseStatus(code = HttpStatus.BAD_REQUEST)
// public class BadRequestController {
    
//     @ExceptionHandler(IdNotFoundException.class)
//     public BaseErrorResponse handleIdNotFound(IdNotFoundException exception){

//         return ErrorResponse.builder()
//         .message(exception.getMessage())
//         .status(HttpStatus.BAD_REQUEST.name())
//         .code(HttpStatus.BAD_REQUEST.value()).build();

//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public BaseErrorResponse handlerErrors(MethodArgumentNotValidException exception){
        
//         List<String> errors =new ArrayList<>();

//         exception.getAllErrors().forEach(exception -> errors.add());
//     }
// }