package br.edu.infnet.alexandrenetoapi.exceptions.handler;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.alexandrenetoapi.exceptions.CarException;
import br.edu.infnet.alexandrenetoapi.exceptions.ClientException;

import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        Map<String, String> mapFields = new HashMap<String, String>();
        Map<String, String> mapInfo = new HashMap<String, String>();

        mapInfo.put("timestamp", LocalDateTime.now().toString());
        mapInfo.put("context", "Request");
        mapInfo.put("error", e.getMessage());

        e.getBindingResult().getAllErrors().forEach(
            (err) -> {
                mapFields.put(
                    ((FieldError)err).getField(),
                    err.getDefaultMessage()
                );
            }
        );

        map.put("information", mapInfo);
        map.put("fields", mapFields);

        return new ResponseEntity<Map<String, Map<String, String>>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarException.class)
    public ResponseEntity<Map<String, String>> handlerCarException(CarException e) {
        return new ResponseEntity<Map<String, String>>(e.getMap(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Map<String, String>> handlerCarException(ClientException e) {
        return new ResponseEntity<Map<String, String>>(e.getMap(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handlerIllegalArgumentException(IllegalArgumentException e) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("context", "Illegal Argument");
        map.put("error", e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
