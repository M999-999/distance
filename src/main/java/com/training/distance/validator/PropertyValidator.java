package com.training.distance.validator;

import com.training.distance.exceptions.AppException;
import com.training.distance.exceptions.AppExceptionCodes;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {
public void validateDuplicatedPropertyValue(String propertyTitle, String value1, String value2){
    if (value1 == null || value1.isEmpty()
            || value2 == null || value2.isBlank()) {
        throw new AppException(
                AppExceptionCodes.EMPTY_PROPERTY_VALUE, propertyTitle, value2);
    } else  if (value1.equalsIgnoreCase(value2)) {
            throw new AppException(
                    AppExceptionCodes.DUPLICATED_PROPERTY_VALUE, propertyTitle, value2);
    }

}
}
