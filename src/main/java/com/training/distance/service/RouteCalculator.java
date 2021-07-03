package com.training.distance.service;

import com.training.distance.dto.DistanceDto;
import com.training.distance.dto.RouteDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RouteCalculator {

    private List<DistanceDto> allDistances;
    private String start;
    private String finish;
    private List<RouteDto> result;

    public RouteCalculator(List<DistanceDto> allDistances, String start, String finish) {
        this.allDistances = allDistances;
        this.start = start;
        this.finish = finish;
        this.result = new ArrayList<>();
    }

    public List<RouteDto> calculate() {
        List<DistanceDto> dtos = getConnectedNotCheckedCities(start, Collections.emptyList());
        List<String> visitedCities = new ArrayList<>();
        visitedCities.add(start.toUpperCase());
        dtos.forEach(
                distanceDto ->
                        buildRoutes(
                                Collections.singletonList(distanceDto),
                                getNextCityToCheck(distanceDto, start),
                                visitedCities));

        return result;
    }

    private List<DistanceDto> getConnectedNotCheckedCities(String city, List<String> checkedCities) {
        return allDistances.stream()
                .filter(
                        distanceDto ->
                                (distanceDto.getSourceCity().equalsIgnoreCase(city)
                                        || distanceDto.getTargetCity().equalsIgnoreCase(city))
                                        && isNotVisited(checkedCities, distanceDto))
                .collect(Collectors.toList());
    }

    private boolean isNotVisited(List<String> visitedCities, DistanceDto distanceDto) {
        return !(visitedCities.contains(distanceDto.getSourceCity().toUpperCase())
                || visitedCities.contains(distanceDto.getTargetCity().toUpperCase()));
    }

    private void buildRoutes(
            List<DistanceDto> previousDistances, String currentCity, List<String> checkedCities) {
        if (currentCity.equalsIgnoreCase(finish)) {
            addRouteToResult(previousDistances);
            return;
        }

        List<DistanceDto> dtos = getConnectedNotCheckedCities(currentCity, checkedCities);
        if (dtos.isEmpty()) {
            return;
        }

        dtos.forEach(
                currentDto ->
                        continueCheckingCities(previousDistances, currentDto, currentCity, checkedCities));
    }

    private void addRouteToResult(List<DistanceDto> distances) {
        RouteDto dto = RouteDto.builder().distanceDtos(distances).build();
        dto.setFullDistance(distances.stream().mapToInt(DistanceDto::getDistance).sum());
        result.add(dto);
    }

    private void continueCheckingCities(
            List<DistanceDto> previousDistances,
            DistanceDto currentDto,
            String currentCity,
            List<String> checkedCities) {
        List<DistanceDto> distances = new ArrayList<>(previousDistances);
        distances.add(currentDto);
        buildRoutes(
                distances,
                getNextCityToCheck(currentDto, currentCity),
                markCityAsChecked(currentCity.toUpperCase(), checkedCities));
    }

    private String getNextCityToCheck(DistanceDto distanceDto, String start) {
        return start.equalsIgnoreCase(distanceDto.getSourceCity())
                ? distanceDto.getTargetCity()
                : distanceDto.getSourceCity();
    }

    private List<String> markCityAsChecked(String city, List<String> checkedCities) {
        List<String> cities = new ArrayList<>(checkedCities);
        cities.add(city);
        return cities;
    }
}
