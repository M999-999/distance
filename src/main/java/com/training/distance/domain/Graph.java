package com.training.distance.domain;
import java.util.List;
public class Graph {
    private final List<City> cities;
    private final List<Distance> distances;

    public Graph(List<City> cities, List<Distance> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Distance> getDistances() {
        return distances;
    }


}

