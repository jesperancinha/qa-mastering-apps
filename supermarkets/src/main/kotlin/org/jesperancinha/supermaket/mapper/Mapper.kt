package org.jesperancinha.supermaket.mapper

import org.jesperancinha.supermaket.domain.Delivery
import org.jesperancinha.supermaket.dto.DeliveryRequestDto
import org.jesperancinha.supermaket.dto.DeliveryResponseDto

object DeliveryMapper {
    fun fromDto(dto: DeliveryRequestDto): Delivery {
        return Delivery(
            vehicleId = dto.vehicleId,
            address = dto.address,
            startedAt = dto.startedAt,
            finishedAt = dto.finishedAt,
            status = dto.status
        )
    }

    fun toDto(entity: Delivery): DeliveryResponseDto {
        return DeliveryResponseDto(
            id = entity.id,
            vehicleId = entity.vehicleId,
            address = entity.address,
            startedAt = entity.startedAt,
            finishedAt = entity.finishedAt,
            status = entity.status
        )
    }
}