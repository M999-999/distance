package com.training.distance.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "vertex",
        uniqueConstraints = {
                @UniqueConstraint(name = "vertex_name_unique", columnNames = "name")
        }
)
public class Vertex {
    @Id
    @SequenceGenerator(
            name = "vertex_sequence",
            sequenceName = "vertex_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "vertex_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;


    public Vertex(String name) {
        this.name = name;
    }
}