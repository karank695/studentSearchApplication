package com.backend.studentsearch.globalexceptionhandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handResourceNoteFound(ResourceNotFoundException ex) {
        HashMap<String, String> errorResp = new HashMap<>();
        errorResp.put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorResp.put("errorMessage", ex.getMessage());
        return ResponseEntity.ok(errorResp);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(NoResourceFoundException ex) {
        HashMap<String, String> errorResp = new HashMap<>();
        errorResp.put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorResp.put("errorMessage", ex.getMessage());
        return ResponseEntity.ok(errorResp);
    }
      @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(MissingServletRequestParameterException ex) {
        HashMap<String, String> errorResp = new HashMap<>();
        errorResp.put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorResp.put("errorMessage", ex.getMessage());
        return ResponseEntity.ok(errorResp);
    }
}
