package com.example.migrationservice.controller;

import com.example.migrationservice.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @GetMapping("/migrate")
    public String migrateData() {
        return migrationService.migrateData();
    }
}
