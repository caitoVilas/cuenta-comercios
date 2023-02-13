package com.comercios.cuentaComercios.exception;

import com.comercios.cuentaComercios.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDTO> notFoundException(Exception e, HttpServletRequest request){
        ErrorDTO response = new ErrorDTO(404, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDTO> badRequestException(Exception e, HttpServletRequest request){
        ErrorDTO response = new ErrorDTO(400, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ErrorDTO> unauthorized(Exception e, HttpServletRequest request){
        ErrorDTO response = new ErrorDTO(401, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
