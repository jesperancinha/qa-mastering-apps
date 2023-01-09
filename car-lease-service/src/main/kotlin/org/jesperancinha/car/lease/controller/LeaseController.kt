package org.jesperancinha.car.lease.controller;

import org.jesperancinha.car.lease.dto.LeaseDto;
import org.jesperancinha.car.lease.services.LeaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("leases")
public class LeaseController {

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping
    public List<LeaseDto> listLeases() {
        return leaseService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public LeaseDto createLease(@RequestBody
                                        LeaseDto leaseDto) {
        return leaseService.createLease(leaseDto);
    }
}
