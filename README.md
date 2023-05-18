# Return JSON objects as Response in Spring Boot

### 1. Create response handler

```
 public class ResponseHandler {
 
     public static ResponseEntity<Object> generateTheResponse(
         Date date, 
         String typeResponse, 
         String message, 
         HttpStatus status, 
         Object responseObj
     ) {
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
```

### 2. Use the ResponseHandler in Controller 

```
    // Add
    @PostMapping
    public ResponseEntity<Object> Post(@RequestBody UserEntity params) {
        try {
            UserEntity result = userService.Post(params);
            return ResponseHandler.generateSuccessResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    // Get
    @GetMapping
    public ResponseEntity<Object> Get() {
        try {
            List<UserEntity> result = userService.Get();
            return ResponseHandler.generateSuccessResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Get By ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> Get(@PathVariable int id) {
        try {
            UserEntity result = userService.Get(id);
            return ResponseHandler.generateSuccessResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Update
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> Update(@PathVariable int id, @RequestBody UserEntity params) {
        try {
            UserEntity result = userService.Update(params, id);
            return ResponseHandler.generateSuccessResponse("Updated", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {
        try {
            String result = userService.Delete(id);
            return ResponseHandler.generateSuccessResponse("Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateErrorResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
```
