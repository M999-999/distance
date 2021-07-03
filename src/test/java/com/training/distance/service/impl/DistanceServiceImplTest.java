package com.training.distance.service.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.dto.DistanceDto;
import com.training.distance.exceptions.AppException;
import com.training.distance.exceptions.AppExceptionCodes;
import com.training.distance.repository.impl.DistanceRepositoryImpl;
import com.training.distance.validator.PropertyValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class DistanceServiceImplTest {

    @Mock
    private DistanceRepositoryImpl distanceRepository;
    //@Mock
    private PropertyValidator propertyValidator;

    private DistanceServiceImpl distanceServiceUnderTest;
    private AppExceptionCodes appExceptionCodes ;
    @BeforeEach
    void setUp() {
        propertyValidator = new PropertyValidator();
        distanceServiceUnderTest = new DistanceServiceImpl(distanceRepository,propertyValidator);
    }

    @Test
    @DisplayName("3.0.4 Duplicated City Name Exception")
    public void failedTocreateDuplicatedCities() {

    City target = new City();
    City source = new City();
    target.setName("A");
    source.setName("A");

    AppException exception = assertThrows(AppException.class,
            ()-> distanceServiceUnderTest.create(
                    new DistanceDto(new Distance(target,source,1))));
    String expectedMessage = appExceptionCodes.DUPLICATED_PROPERTY_VALUE.toString();
    String actualMessage = exception.getInfo().name();

    assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    @DisplayName("3.0.1 Create new Distance entity")
    void create() {
        City target = new City();
        City source = new City();
        target.setName("A");
        source.setName("B");
        Distance distance = new Distance(target,source,1);

        assertDoesNotThrow(()->
            distanceServiceUnderTest.create(new DistanceDto(distance)));
    }

    @Test
    @DisplayName("3.0.2 Failed to Create new Distance entity with EMPTY City name")
    void failedToCreateDistanceWithNullCity() {
        City target = new City();
        City source = new City();
        target.setName("A");
        source.setName("");
         AppException exception = assertThrows(AppException.class,()->
                distanceServiceUnderTest.create(new DistanceDto(new Distance(target,source,1))));
        String expectedMessage = appExceptionCodes.EMPTY_PROPERTY_VALUE.toString();
        String actualMessage = exception.getInfo().name();

        assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
    }

    @Test
    @DisplayName("3.0.3 Failed to Create new Distance entity with null City obj")
    void failedToCreateDistanceWithSameCity() {
        City target = new City();
        City source = new City();
        target.setName("A");
        source.setName("A");
        Distance distance = new Distance(target,source,1);
        //TODO implement to throw AppException - currently NullPointerException
        //assertThrows(Exception.class,()-> new Distance(target,source,1));
        assertThrows(Exception.class,()->
                distanceServiceUnderTest.create(new DistanceDto(distance)));
    }

    @Test
    @DisplayName("3.0.5 Failed to Create new Distance entity with null Distance obj")
    void failedToCreateDistanceWithNullDistance() {
        Distance distance = null;
        //TODO implement to throw AppException - currently NullPointerException
        assertThrows(Exception.class,()->
                distanceServiceUnderTest.create(new DistanceDto(distance)));
    }
}