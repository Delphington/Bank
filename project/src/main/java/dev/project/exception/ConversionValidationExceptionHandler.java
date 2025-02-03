package dev.project.exception;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@UtilityClass
public class ConversionValidationExceptionHandler {

    public Long handleTypeConversionErrors(final String tempId) {
        Long id;
        try {
            id = Long.parseLong(tempId);
        } catch (RuntimeException e) {
            throw new DataConversionException("Incorrect type clientId");
        }
        return id;
    }

    public void handleValidationErrors(final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new ValidationException(errors);
        }
    }
}
