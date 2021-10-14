package com.energy.usage.domain.energyusage;

import java.time.*;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "energy_usage")
@Getter
@Setter
@NoArgsConstructor
public class EnergyUsage {

    @Basic
    @Column(name = "hours", nullable = true)
    private Integer hours;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "source_type", nullable = true)
    private String sourceType;

    @Basic
    @Column(name = "timestamp", nullable = true)
    private LocalDateTime timestamp;

    @Basic
    @Column(name = "unit_cost", nullable = true)
    private Integer unitCost;

    @Basic
    @Column(name = "units", nullable = true)
    private Integer units;
}
