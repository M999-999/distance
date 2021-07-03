package com.training.distance.domain;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    private List<Distance> distances;
    private Integer fullDistance;
}