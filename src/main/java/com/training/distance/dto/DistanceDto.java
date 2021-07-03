package com.training.distance.dto;

import com.training.distance.domain.Distance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDto {

    @Pattern(regexp = "\\p{Alnum}+", message = "{sourceCity_only_letters_and_numbers}")
    private String sourceCity;

    @Pattern(regexp = "\\p{Alnum}+", message = "{targetCity_only_letters_and_numbers}")
    private String targetCity;

    @NotNull(message = "{distance_not_null}")
    @Positive(message = "{distance_not_negative}")
    private int distance;

    public DistanceDto(Distance distance) {
        this.sourceCity = distance.getSourceCity().getName();
        this.targetCity = distance.getTargetCity().getName();
        this.distance = distance.getDistance();
    }
}