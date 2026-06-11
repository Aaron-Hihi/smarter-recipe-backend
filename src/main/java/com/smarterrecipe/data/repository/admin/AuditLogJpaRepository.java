package com.smarterrecipe.data.repository.admin;

import com.smarterrecipe.data.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogJpaRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findTop50ByOrderByCreatedAtDesc();
    List<AuditLog> findAllByActorIdOrderByCreatedAtDesc(Long actorId);
    List<AuditLog> findAllByTargetTypeAndTargetIdOrderByCreatedAtDesc(String targetType, Long targetId);
    List<AuditLog> findAllByActionOrderByCreatedAtDesc(String action);
}
