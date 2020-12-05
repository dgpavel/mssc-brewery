package ro.dma.learn.sbmbeer.shared.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        List<String> validations = new ArrayList<>();
        //
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST);
        apiErrorResponse.setMessage("Datele trimise sunt invalide");
        apiErrorResponse.setDetails(webRequest.getDescription(false));
        //
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            validations.add(fieldError.getField()
                    .concat(" : ")
                    .concat(fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "")
                    .concat(";")
                    .concat(" rejectedValue")
                    .concat(" : ")
                    .concat(fieldError.getRejectedValue() != null ? String.valueOf(fieldError.getRejectedValue()) : ""));
        }
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        for (ObjectError objectError : globalErrors) {
            validations.add(objectError.getDefaultMessage());
        }
        apiErrorResponse.setValidations(validations);
        //
        log.error(ex.getMessage(), ex);
        //
        return apiErrorResponse;
    }
}
