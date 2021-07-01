package com.training.distance.repository;

import com.training.distance.domain.Distance;

import java.util.List;
//@Repository
public interface DistanceRepository {

    List<Distance> getAll();

    List<Distance> getByCity(String city);

    void insert(Distance distanceDto);
}