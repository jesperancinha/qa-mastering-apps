package org.jesperancinha.car.lease.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.io.Serializable

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
class User : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = null

    @Column
    private val username: String? = null

    @Column
    private val password: String? = null

    @Column
    private val firstName: String? = null

    @Column
    private val lastName: String? = null

    @Column
    private val email: String? = null
}