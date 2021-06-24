package com.training.distance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Data
@Entity
@Table
//@Table(name = "cities")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name = "getCityByTitle", query = "select c from City c where lower(c.name) = :name")
public class City {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column
    private String name;
}