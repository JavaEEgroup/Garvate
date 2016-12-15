package com.gc.repository.repository;

import com.gc.model.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditStatusRepository extends JpaRepository<CreditStatus,Long> {
    public CreditStatus findByDescription(String description);
}
