package com.thedevjournal.mystocks.model;

/*
 * @author Kshitij
 * @created 19-Aug-2024
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sectors", 
        uniqueConstraints = @UniqueConstraint(name = "uk_sector_name", columnNames = {"name"}))
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_seq")
    @SequenceGenerator(name = "sector_seq", sequenceName = "sector_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
