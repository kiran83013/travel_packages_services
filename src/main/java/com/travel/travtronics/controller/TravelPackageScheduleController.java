package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.request.PackageScheduleRequest;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.service.TravelPackageConfigurationScheduleService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/travel-package-schedule")
public class TravelPackageScheduleController {

    @Autowired
    TravelPackageConfigurationScheduleService packageScheduleService;
    
    @PostMapping(value = "create", produces = "application/json", consumes = "application/json")
    public APIResponse createPackageSchedule(@RequestBody @Valid PackageScheduleRequest request) {
        return packageScheduleService.createPackageSchedule(request);
    }

    @PutMapping(value = "update", produces = "application/json", consumes = "application/json")
    public APIResponse updatePackageSchedule(@RequestBody PackageScheduleRequest request) throws Exception {
        return packageScheduleService.updatePackageSchedule(request);
    }

    @GetMapping(value = "find")
    public APIResponse findById(@RequestParam Long id) throws Exception {
        return packageScheduleService.findPackageSchedule(id);
    }

    @GetMapping(value = "list-by-package")
    public APIResponse findByPackageId(@RequestParam Long id) throws Exception {
        return packageScheduleService.getAllPackageSchedule(id);
    }

}
