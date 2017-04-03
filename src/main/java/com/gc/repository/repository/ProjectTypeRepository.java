package com.gc.repository.repository;

import com.gc.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {

    List<ProjectType> findByOpen(Boolean open);
}
