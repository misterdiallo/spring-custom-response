package com.misterdiallo.backend.springcustomresponse.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResponseHandler {


    public static ResponseEntity<Object> generateTheResponse(Date date, String typeResponse, String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("date", date.toString());
        map.put("type", typeResponse.toUpperCase()); // "SUCCESS","ERROR","EXCEPTION"
        map.put("message", message);
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> generateSuccessResponse(String message, HttpStatus status, Object responseObj) {
        return generateTheResponse(
                Date.from(Instant.now()),
                "success",
                message,
                status,
                responseObj
        );
    }

    public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatus status, Object responseObj) {
        return generateTheResponse(
                Date.from(Instant.now()),
                "error",
                message,
                status,
                responseObj
        );
    }

    public static ResponseEntity<Object> generateExceptionResponse(String message, HttpStatus status, Object responseObj) {
        return generateTheResponse(
                Date.from(Instant.now()),
                "exception",
                message,
                status,
                responseObj
        );
    }
}
