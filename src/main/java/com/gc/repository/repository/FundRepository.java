package com.gc.repository.repository;


import com.gc.model.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund,Long> {
}
