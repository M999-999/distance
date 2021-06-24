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
@Table
public class Edge {
    @Id
    @SequenceGenerator(
            name = "edge_sequence",
            sequenceName = "edge_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "edge_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn
    private Vertex source;
    @OneToOne
    @JoinColumn
    private Vertex destination;
    private int weight;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
