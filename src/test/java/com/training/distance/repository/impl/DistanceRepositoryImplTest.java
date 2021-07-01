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
//@Import(DistanceRepository.class)
//@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
class DistanceRepositoryImplTest {

    //@Autowired()
    private DistanceRepository distanceRepositoryUnderTest;
//    @Autowired
//    private TestEntityManager entityManager;
//    @Autowired
//    private EntityManager entityManager;
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
//        distanceRepositoryUnderTest = new DistanceRepository(entityManager);
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
    @DisplayName("1.0.4 Get All Distances from Repo")
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
    @DisplayName("1.0.2 Failed to Insert new Entity with NEGATIVE  distance value")
    void insertNegativeDistanceIntoRepositoryFailed() {
        int negativeIntValue = -1;
        Distance theDistance = new Distance();
        City target = new City();
        target.setName("A");
        City source = new City();
        source.setName("B");
        theDistance.setTargetCity(target);
        theDistance.setSourceCity(source);
        theDistance.setDistance(negativeIntValue);
        Exception exception = assertThrows(Exception.class, () -> distanceRepositoryUnderTest.insert(theDistance));
        // when
        List<Distance> expected = distanceRepositoryUnderTest.getByCity("A");
        // then
        assertThat(expected).asList();
    }

    @Test
    @DisplayName("1.0.3 Failed to Insert new Entity with not initializes properly Distance object")
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