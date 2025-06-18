package org.jesperancinha.rockstarts.rockstarsmanager.configuration

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.lang.Nullable
import java.sql.Timestamp

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
class User {
    @Id
    @Column(unique = true)
    private val email: String? = null

    @Column
    private val name: String? = null

    @Column
    private val password: String? = null

    @Column
    private val role: String? = null

    @Column
    @Nullable
    private val date: Timestamp? = null
}