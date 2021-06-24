package com.training.distance.service;

import com.training.distance.dto.DistanceDto;
import org.springframework.stereotype.Component;

@Component
public interface DistanceService {
    void create(DistanceDto distanceDto);
}