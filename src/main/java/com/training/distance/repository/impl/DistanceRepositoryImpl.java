package com.training.distance.repository.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.repository.DistanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

//import static org.hibernate.cinternal.util.collections.CollectionHelper.isNotEmpty;
@Repository
@Profile("dev")
@Slf4j
public class DistanceRepositoryImpl implements DistanceRepository {
    //@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public DistanceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Distance> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Distance> query = session.createQuery("from  Distance");
        query.setCacheable(true);

        return query.getResultList();
    }

    @Override
    public List<Distance> getByCity(String city) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Distance> query =
                session
                        .createNamedQuery("findDistancesByCityTitle", Distance.class)
                        .setParameter("name", city.toLowerCase());
        query.setCacheable(true);

        return query.getResultList();
    }

    @Override
    //@Transactional
    public void insert(Distance newDistance) {
        Session session = this.sessionFactory.getCurrentSession();
        City city1 = getOrCreateCity(newDistance.getSourceCity().getName());
        City city2 = getOrCreateCity(newDistance.getTargetCity().getName());

        Optional<Distance> distanceOpt = getDistanceForCities(city1.getId(), city2.getId());

        Distance distance = distanceOpt.orElse(new Distance(city1, city2, 0));
        distance.setDistance(newDistance.getDistance());
        session.saveOrUpdate(distance);
        //session.flush();//3006
    }

    private City getOrCreateCity(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<City> query =
                session
                        .createNamedQuery("getCityByTitle", City.class)
                        .setParameter("name", title.toLowerCase());
        query.setCacheable(true);

        List<City> cities = query.list();

//        if (isNotEmpty(cities)) {
        if (!isEmpty(cities)) {
            return cities.get(0);
        }

        City city = City.builder().name(title).build();
        session.save(city);
        //session.flush();
        return city;
    }

    private Optional<Distance> getDistanceForCities(long city1Id, long city2Id) {
        Session session = this.sessionFactory.getCurrentSession();
        Query<Distance> query =
                session
                        .createNamedQuery("findDistanceByCityId", Distance.class)
                        .setParameter("id1", city1Id)
                        .setParameter("id2", city2Id);
        query.setCacheable(true);

        List<Distance> distances = query.list();
        //return isNotEmpty(distances) ? Optional.of(distances.get(0)) : Optional.empty();
        return !isEmpty(distances) ? Optional.of(distances.get(0)) : Optional.empty();
    }
}