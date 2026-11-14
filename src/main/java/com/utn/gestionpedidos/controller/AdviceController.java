package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.exception.ApiError;
import com.utn.gestionpedidos.exception.RecursoDuplicadoException;
import com.utn.gestionpedidos.exception.RecursoNoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiError> noEncontrado(RecursoNoEncontradoException ex,
                                                  HttpServletRequest request) {
        return respuesta(HttpStatus.NOT_FOUND, ex.getMessage(), request, Map.of());
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ApiError> duplicado(RecursoDuplicadoException ex,
                                               HttpServletRequest request) {
        return respuesta(HttpStatus.CONFLICT, ex.getMessage(), request, Map.of());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validacion(MethodArgumentNotValidException ex,
                                                HttpServletRequest request) {
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errores.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return respuesta(HttpStatus.BAD_REQUEST, "Hay datos inválidos en la solicitud", request, errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> inesperado(Exception ex, HttpServletRequest request) {
        return respuesta(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error inesperado", request, Map.of());
    }

    private ResponseEntity<ApiError> respuesta(HttpStatus status, String mensaje,
                                                HttpServletRequest request,
                                                Map<String, String> validaciones) {
        ApiError error = new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                mensaje, request.getRequestURI(), validaciones);
        return ResponseEntity.status(status).body(error);
    }
}
