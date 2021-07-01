package com.training.distance.controller;

import com.training.distance.dto.DistanceDto;
import com.training.distance.dto.RouteDto;
import com.training.distance.service.DistanceService;
import com.training.distance.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author klebcha
 */
@RestController
@RequestMapping(value = "/cities", produces = "application/json")
public class CityController {
    //@Autowired
    private DistanceService distanceService;
    private RouteService routeService;

   @Autowired
    public CityController(DistanceService distanceService, RouteService routeService) {
        this.distanceService = distanceService;
        this.routeService = routeService;
    }

    /**
     *
     * @param distanceDto
     * @return
     * @since
     * @see
     *
     *
     */
    @PostMapping("/distance2")
    public ResponseEntity<DistanceDto> create(@RequestBody @Valid DistanceDto distanceDto) {
        distanceService.create(distanceDto);
        return new ResponseEntity<>(distanceDto, HttpStatus.CREATED);
    }

    @GetMapping("/route2/{source}/{target}")
    public ResponseEntity<List<RouteDto>> getRoute(
            @PathVariable String source, @PathVariable String target) {
        return new ResponseEntity<>(routeService.getRoutes(source, target), HttpStatus.OK);
    }
}