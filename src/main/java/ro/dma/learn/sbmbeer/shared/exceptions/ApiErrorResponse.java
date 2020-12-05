package ro.dma.learn.sbmbeer.shared.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private List<String> validations;
    private String details;

    public ApiErrorResponse(HttpStatus status) {
        timestamp = LocalDateTime.now();
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getValidations() {
        return validations;
    }

    public void setValidations(List<String> validations) {
        this.validations = validations;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiErrorResponse{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", validations=").append(validations);
        sb.append(", details='").append(details).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
