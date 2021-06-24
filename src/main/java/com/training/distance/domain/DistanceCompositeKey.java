package com.training.distance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistanceCompositeKey implements Serializable {

    @Column(name = "source_city_id") private Long sourceCityId;
    @Column(name = "target_city_id") private Long targetCityId;
//    @Column
//    private Long sourceCityId;
//    @Column
//    private Long targetCityId;

}