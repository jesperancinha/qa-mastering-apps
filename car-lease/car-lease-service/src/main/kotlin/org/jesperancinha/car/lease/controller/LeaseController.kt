package org.jesperancinha.car.lease.controller

import org.jesperancinha.car.lease.dto.LeaseDto
import org.jesperancinha.car.lease.services.LeaseService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("leases")
class LeaseController(private val leaseService: LeaseService) {
    @GetMapping
    fun listLeases(): List<LeaseDto?>? {
        return leaseService.all()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createLease(@RequestBody leaseDto: LeaseDto): LeaseDto? {
        return leaseService.createLease(leaseDto)
    }
}