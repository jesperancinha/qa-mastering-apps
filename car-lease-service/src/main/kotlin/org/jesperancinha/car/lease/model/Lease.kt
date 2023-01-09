package org.jesperancinha.car.lease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Car car;

    @OneToOne
    private Customer customer;

    @Column
    private Long duration;

    @Column
    private Long interestRate;

    @Column
    private Long netPrice;

   @Column
   private Double leaseRate;
}
