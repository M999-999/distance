package com.training.distance.service.impl;

import com.training.distance.domain.City;
import com.training.distance.domain.Distance;
import com.training.distance.dto.DistanceDto;
import com.training.distance.repository.DistanceRepository;
import com.training.distance.service.DistanceService;
import com.training.distance.validator.PropertyValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
//@EnableTransactionManagement
public class DistanceServiceImpl implements DistanceService {

    private DistanceRepository distanceRepository;
    private PropertyValidator propertyValidator;

    @Autowired
    public DistanceServiceImpl(
            DistanceRepository distanceRepository, PropertyValidator propertyValidator) {
        this.distanceRepository = distanceRepository;
        this.propertyValidator = propertyValidator;
    }
    @Override
    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void create(DistanceDto distanceDto) {
        log.info("Creating object: {}", distanceDto);
        propertyValidator.validateDuplicatedPropertyValue(
                "targetCity", distanceDto.getSourceCity(), distanceDto.getTargetCity());

        Distance newDistance =
                Distance.builder()
                        .sourceCity(City.builder().name(distanceDto.getSourceCity()).build())
                        .targetCity(City.builder().name(distanceDto.getTargetCity()).build())
                        .distance(distanceDto.getDistance())
                        .build();

        this.distanceRepository.insert(newDistance);
    }
}
