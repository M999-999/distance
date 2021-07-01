package com.training.distance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Data
@Entity
@Table
//@Table(name = "cities_distance")
//@Table(name = "distance")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
        @NamedQuery(
                name = "findDistanceByCityId",
                query =
                        "select d from Distance d where (d.sourceCity in(select c from City c where c.id = :id1) "
                                + "and d.targetCity in(select c from City c where c.id = :id2)) or "
                                + "d.sourceCity in(select c from City c where c.id = :id2) "
                                + "and d.targetCity in(select c from City c where c.id = :id1)"),
        @NamedQuery(
                name = "findDistancesByCityTitle",
                query =
                        "select d from Distance d where d.sourceCity in(select c from City c " +
                                "where lower(c.name) = :name) or d.targetCity in(select c from City c " +
                                "where lower(c.name) = :name)")
})
public class Distance {
    @EmbeddedId
    private DistanceCompositeKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "source_city_id" , insertable = false, updatable = false)
    private City sourceCity;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "target_city_id" , insertable = false, updatable = false)
    private City targetCity;

    @Column
    @Range(min = 0, message = "Please select positive numbers Only")
    private int distance;

    public Distance(City sourceCity, City targetCity, int distance) {
        this.id = DistanceCompositeKey.builder().sourceCityId(sourceCity.getId()).
                targetCityId(targetCity.getId()).build();
        this.sourceCity = sourceCity;
        this.targetCity = targetCity;
        this.distance = distance;
    }
}
