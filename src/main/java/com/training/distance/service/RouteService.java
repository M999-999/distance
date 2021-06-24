package com.training.distance.service;

import com.training.distance.dto.RouteDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RouteService {
    List<RouteDto> getRoutes(String sourceCityName, String targetCityName);
}