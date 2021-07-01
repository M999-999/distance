package com.training.distance.repository.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.repository.DistanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Repository
@Profile("dev")
@Slf4j
public class DistanceRepositoryImpl implements DistanceRepository {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Autowired
    public DistanceRepositoryImpl(EntityManager entityManager) {

            this.entityManager = entityManager;
    }

    @Override
    public List<Distance> getAll() {
        Query query = entityManager.createQuery("select d from Distance d");
        return query.getResultList();
    }

    @Override
    public List<Distance> getByCity(String city) {
        TypedQuery<Distance> query =
                entityManager
                        .createNamedQuery("findDistancesByCityTitle", Distance.class)
                        .setParameter("name", city.toLowerCase());

        return query.getResultList();
    }

    @Override
    @Transactional
    public void insert(Distance newDistance) {
        City city1 = getOrCreateCity(newDistance.getSourceCity().getName());
        City city2 = getOrCreateCity(newDistance.getTargetCity().getName());

        Optional<Distance> distanceOpt = getDistanceForCities(city1.getId(), city2.getId());

        Distance distance = distanceOpt.orElse(new Distance(city1, city2, 0));
        distance.setDistance(newDistance.getDistance());
        entityManager.persist(distance);
    }

    private City getOrCreateCity(String title) {
        TypedQuery<City> query =
                entityManager
                        .createNamedQuery("getCityByTitle", City.class)
                        .setParameter("name", title.toLowerCase());

        List<City> cities = query.getResultList();
        if (!isEmpty(cities)) {
            return cities.get(0);
        }

        City city = City.builder().name(title).build();
        entityManager.persist(city);
        return city;
    }

    private Optional<Distance> getDistanceForCities(long city1Id, long city2Id) {
        TypedQuery<Distance> query =
                entityManager
                        .createNamedQuery("findDistanceByCityId", Distance.class)
                        .setParameter("id1", city1Id)
                        .setParameter("id2", city2Id);

        List<Distance> distances = query.getResultList();
        return !isEmpty(distances) ? Optional.of(distances.get(0)) : Optional.empty();
    }
}