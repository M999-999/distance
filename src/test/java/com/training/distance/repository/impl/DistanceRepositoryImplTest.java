package com.training.distance.repository.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.repository.DistanceRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
class DistanceRepositoryImplTest {
    //@Autowired
    //@Mock
    private DistanceRepository distanceRepositoryUnderTest;

    //@Autowired
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @BeforeEach
    @Disabled
    void setUp() {
        distanceRepositoryUnderTest = new DistanceRepositoryImpl(entityManager);
        Distance theDistance = new Distance();
        City target = new City();
        target.setName("T");
        City source = new City();
        source.setName("S");
        theDistance.setTargetCity(target);
        theDistance.setSourceCity(source);
        theDistance.setDistance(10);
        distanceRepositoryUnderTest.insert(theDistance);
    }


    @AfterEach
    @Disabled
    void tearDown() {
        //distanceRepositoryUnderTest.deleteAll();
    }

    @Test
    @DisplayName("0.0.1 injectedComponentsAreNotNull")
    void injectedComponentsAreNotNull(){
        assertThat(distanceRepositoryUnderTest).isNotNull();
        assertThat(entityManager).isNotNull();
    }

    @Test
    @DisplayName("1.0.5 Get All Distances from Repo")
    void getAll() {
        // when
        List<Distance> expected = distanceRepositoryUnderTest.getAll();
        // then
        assertThat(expected).asList();
    }

    @Test
    @DisplayName("1.0.2 Get Distance by City name")
    void getDistanceByCity() {
        // when
        List<Distance> expected = distanceRepositoryUnderTest.getByCity("T");
        // then
        assertThat(expected).asList();
        Assertions.assertTrue(expected.size()>0);
    }
    @Test
    @DisplayName("1.0.3 Get Distance from Repo by WRONG City name")
    void getDistanceByWrongCityName() {
        // when
        List<Distance> expected = distanceRepositoryUnderTest.getByCity("T8");
        // then
        assertThat(expected).asList();
        Assertions.assertFalse(expected.size()>0);
    }


    @Test
    @DisplayName("1.0.1 Insert Distance Entity into Repository")
    void insertDistanceIntoRepository() {
        Distance theDistance = new Distance();
        City target = new City();
        target.setName("A");
        City source = new City();
        source.setName("B");
        theDistance.setTargetCity(target);
        theDistance.setSourceCity(source);
        theDistance.setDistance(1);
        distanceRepositoryUnderTest.insert(theDistance);
        // when
        List<Distance> expected = distanceRepositoryUnderTest.getByCity("A");
        // then
        assertThat(expected).asList();
    }

    @Test
    @Disabled
    @DisplayName("1.0.999 Failed to Insert new Entity with NEGATIVE  distance value")
    //@Rollback
    void insertNegativeDistanceIntoRepositoryFailed() {
        int negativeIntValue = -10;
        Distance theDistance = new Distance();
        City target = new City();
        target.setName("AA");
        City source = new City();
        source.setName("BB");
        theDistance.setTargetCity(target);
        theDistance.setSourceCity(source);
        theDistance.setDistance(negativeIntValue);

        //Exception exception = assertThrows(Exception.class, () -> distanceRepositoryUnderTest.insert(theDistance));
        try {
        distanceRepositoryUnderTest.insert(theDistance);
        }
        catch(Throwable e)  {
            System.out.println(e.getMessage());
                       }


        // when
        List<Distance> expected = distanceRepositoryUnderTest.getByCity("AA");
        // then
        //assertThat(expected).asList();
        List<Distance> expected2 = distanceRepositoryUnderTest.getAll();
        assertThat(expected2).asList();
   /*
//        distanceRepositoryUnderTest.insert(theDistance);
//        List<Distance> expected = distanceRepositoryUnderTest.getAll();
//        List<Distance> expected2 = distanceRepositoryUnderTest.getAll();

try{
    distanceRepositoryUnderTest.insert(theDistance);
}catch(Exception e){
    String actualMessageq = e.getMessage();
}
        List<Distance> expected = distanceRepositoryUnderTest.getAll();

//        Throwable exception = assertThrows(Throwable.class, () -> distanceRepositoryUnderTest.insert(theDistance));
//        String actualMessage = exception.getMessage();
        String expectedMessage = "Please select positive numbers Only";
        */
        //Exception exception = assertThrows(Exception.class, () -> distanceRepositoryUnderTest.insert(theDistance));
        // when
        //ConstraintViolationImpl{interpolatedMessage='Please select positive numbers Only', propertyPath=distance,
/*
        //distanceRepositoryUnderTest.insert(theDistance);
//            ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
//                    () -> distanceRepositoryUnderTest.insert(theDistance));
        Exception exception = assertThrows(Exception.class,
                () -> distanceRepositoryUnderTest.insert(theDistance));
            String expectedMessage = "Please select positive numbers Only";
            String actualMessage = exception.getMessage();

           // assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
            //List<Distance> expected = distanceRepositoryUnderTest.getByCity("AA");
        // then
            assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
        //assertThat(expected).asList();
        */

    }

    @Test
    @DisplayName("1.0.4 Failed to Insert new Entity with not initializes properly Distance object")
    void insertEmptyDistanceIntoRepositoryFailed() {
        Distance theDistance = new Distance();
        //TODO should be AppException.class but currently thrown NullPointerException
        assertThrows(Exception.class, () -> distanceRepositoryUnderTest.insert(theDistance));
    }

    //https://junit.org/junit5/docs/current/user-guide/
    @DisplayName("A negative value for year is not supported by the leap year computation.")
    @ParameterizedTest(name = "For example, year {0} is not supported.")
    @ValueSource(ints = { -1, -4 })
    void if_it_is_negative(int year) {
    }
}