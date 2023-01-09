package org.jesperancinha.car.lease.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String street;

    @Column
    private Long houseNumber;

    @Column
    private String zipCode;

    @Column
    private String place;

    @Column
    private String email;

    @Column
    private String phoneNumber;

}
