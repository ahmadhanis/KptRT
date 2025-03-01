package com.kptrt.realtime.repositories;

import com.kptrt.realtime.models.StudentRealtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRealtimeRepository extends JpaRepository<StudentRealtime, Long> {
}