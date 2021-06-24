package com.training.distance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {
    private List<DistanceDto> distanceDtos;
    @Builder.Default private int fullDistance = 0;
}
