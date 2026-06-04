package com.smarterrecipe.data.repository.admin;

import com.smarterrecipe.data.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogJpaRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findTop50ByOrderByCreatedAtDesc();
}
