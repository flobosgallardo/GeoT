package old.geot.entity;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;


public class ApiError {

    private Timestamp timestamp;
    private HttpStatus message;
    private String status;
    private String error;
    private String path;


    public ApiError(Timestamp timestamp, HttpStatus message, String status, String error, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
    }


    public Timestamp getTimestamp() {
        return timestamp;
    }

    public HttpStatus getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
