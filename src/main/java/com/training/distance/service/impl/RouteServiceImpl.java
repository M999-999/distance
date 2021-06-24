package com.training.distance.service.impl;

import com.training.distance.dto.DistanceDto;
import com.training.distance.dto.RouteDto;
import com.training.distance.exceptions.AppException;
import com.training.distance.exceptions.AppExceptionCodes;
import com.training.distance.repository.DistanceRepository;
import com.training.distance.service.RouteCalculator;
import com.training.distance.service.RouteService;
import com.training.distance.validator.PropertyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
//@EnableTransactionManagement
public class RouteServiceImpl implements RouteService {
    //@Autowired
    private PropertyValidator propertyValidator;
    //@Autowired
    private DistanceRepository distanceRepository;

    @Autowired
    public RouteServiceImpl(
            PropertyValidator propertyValidator, DistanceRepository distanceRepository) {
        this.propertyValidator = propertyValidator;
        this.distanceRepository = distanceRepository;
    }

    @Override
    @Transactional
    public List<RouteDto> getRoutes(String start, String finish) {
        validateSearchQueryParameters(start, finish);
        List<DistanceDto> allDistances = distanceRepository.getAll().stream().map(DistanceDto::new).collect(Collectors.toList());

        RouteCalculator calculator = new RouteCalculator(allDistances, start, finish);
        List<RouteDto> routes = calculator.calculate();

        if (routes.isEmpty()) {
            throw new AppException(
                    AppExceptionCodes.ROUTER_NOT_FOUND, start, finish);
        }

        return routes;
    }

    private void validateSearchQueryParameters(String start, String finish) {
        propertyValidator.validateDuplicatedPropertyValue("finish", start, finish);
        validateCityExist(start);
        validateCityExist(finish);
    }

    private void validateCityExist(String city) {
        if (distanceRepository.getByCity(city).isEmpty()) {
            throw new AppException(AppExceptionCodes.ENTITY_NOT_FOUND, city);
        }
    }
}