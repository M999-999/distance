package com.training.distance.validator;

import com.training.distance.exceptions.AppException;
import com.training.distance.exceptions.AppExceptionCodes;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {
//    public void validateDuplicatedPropertyValue(String propertyTitle, String value1, String value2) throws DistanceAppException {
public void validateDuplicatedPropertyValue(String propertyTitle, String value1, String value2){
        if (value1.equalsIgnoreCase(value2)) {
//            try {
                throw new AppException(
                        AppExceptionCodes.DUPLICATED_PROPERTY_VALUE, propertyTitle, value2);
//            } catch (AppException e) {
//                e.printStackTrace();
//            }
        }
    }
}
