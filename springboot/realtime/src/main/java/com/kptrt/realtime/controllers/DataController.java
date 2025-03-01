package com.kptrt.realtime.controllers;

import com.kptrt.realtime.services.DataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private DataMigrationService dataService;

    @GetMapping("/migrate")
    public String migrateData() {
        dataService.migrateData();
        return "Data migration completed!";
    }
}
