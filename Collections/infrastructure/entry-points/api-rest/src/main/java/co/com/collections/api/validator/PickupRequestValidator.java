package co.com.collections.api.validator;

import co.com.collections.model.pickuprequest.PickupRequest;
import co.com.collections.util.validator.ValidationException;
import co.com.collections.util.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Component
public class PickupRequestValidator implements Validator<PickupRequest> {

    private static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public void validate(PickupRequest request) throws ValidationException {
        if (Objects.isNull(request.getStorageId()) || request.getStorageId() <= 0) {
            throw new ValidationException("Storage ID is required and must be a positive value");
        }

        if (Objects.isNull(request.getPickupDate()) ) {
            throw new ValidationException("Pickup Date is required");
        }

        try {
            LocalDateTime.parse(request.getPickupDate().toString(), this.ISO_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Pickup Date must be in ISO 8601 format");
        }
    }
}
