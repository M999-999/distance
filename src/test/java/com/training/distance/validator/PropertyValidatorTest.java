package com.training.distance.validator;

import com.training.distance.exceptions.AppException;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class PropertyValidatorTest {
    private PropertyValidator propertyValidator;
    @Test
    @DisplayName("2.0.1 not Duplicated City Name")
    void validatePropertyValue() {
        propertyValidator = new PropertyValidator();
        Assertions.assertDoesNotThrow(()->propertyValidator.validateDuplicatedPropertyValue("testProp", "one", "two")
        );
    }
    @Test
    @DisplayName("2.0.2 Duplicated City Name")
    void validateDuplicatedPropertyValue() {
        propertyValidator = new PropertyValidator();
        Assertions.assertThrows(AppException.class, ()->propertyValidator.validateDuplicatedPropertyValue("testProp",
                "one", "one")
        );
    }
}