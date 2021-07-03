package com.training.distance.validator;

import com.training.distance.exceptions.AppException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class PropertyValidatorTest {
    //@Mock
    private PropertyValidator propertyValidator;

    @BeforeEach
    void setUp() {
         propertyValidator = new PropertyValidator();
    }
    @Test
    @DisplayName("2.0.1 not Duplicated City Name")
    void validatePropertyValue() {
        //propertyValidator = new PropertyValidator();
        Assertions.assertDoesNotThrow(()->propertyValidator.validateDuplicatedPropertyValue("testProp", "one", "two")
        );
    }
    @Test
    @DisplayName("2.0.2 Duplicated City Name")
    void validateDuplicatedPropertyValue() {
        //propertyValidator = new PropertyValidator();
        Assertions.assertThrows(AppException.class, ()->propertyValidator.validateDuplicatedPropertyValue("testProp",
                "one", "one")
        );
    }
}