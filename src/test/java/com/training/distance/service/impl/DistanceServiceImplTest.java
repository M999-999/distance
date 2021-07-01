package com.training.distance.service.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.dto.DistanceDto;
import com.training.distance.repository.impl.DistanceRepositoryImpl;
import com.training.distance.validator.PropertyValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class DistanceServiceImplTest {

    @Mock
    private DistanceRepositoryImpl distanceRepository;
    @Mock
    private PropertyValidator propertyValidator;

    private DistanceServiceImpl distanceServiceUnderTest;

    @BeforeEach
    void setUp() {
//        distanceRepository = mock(DistanceRepositoryImpl.class);;
//        propertyValidator = mock(PropertyValidator.class);;
        distanceServiceUnderTest = new DistanceServiceImpl(distanceRepository,propertyValidator);
    }

    @Test
    @DisplayName("3.0.1 Create new Distance entity")
    void create() {
        City target = new City();
        City source = new City();
        target.setName("A");
        source.setName("B");
        Distance distance = new Distance(target,source,1);

        Assertions.assertDoesNotThrow(()->
            distanceServiceUnderTest.create(new DistanceDto(distance)));
    }

    @Test
    @DisplayName("3.0.2 Failed to Create new Distance entity with null City obj")
    void failedToCreateDistanceWithNullCity() {
        City target = new City();
        City source = null;
        target.setName("A");
        //source.setName("B");
        //TODO implement to throw AppException - currently NullPointerException
        Assertions.assertThrows(Exception.class,()-> new Distance(target,source,1));
    }

    @Test
    @DisplayName("3.0.3 Failed to Create new Distance entity with null Distance obj")
    void failedToCreateDistanceWithNullDistance() {
        Distance distance = null;
        //TODO implement to throw AppException - currently NullPointerException
        Assertions.assertThrows(Exception.class,()->
                distanceServiceUnderTest.create(new DistanceDto(distance)));
    }
}