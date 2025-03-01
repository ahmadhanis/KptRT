package com.mymohe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mymohe.service.MigrationService;

import java.util.Map;

@RestController
@RequestMapping("/api/migrate")
public class MigrationController {

    @Autowired
    private MigrationService migrationService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> migrateData() {
        try {
            Map<String, Object> result = migrationService.migrateData();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
        }
    }
}