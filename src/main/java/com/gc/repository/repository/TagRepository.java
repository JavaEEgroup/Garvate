package com.gc.repository.repository;

import com.gc.model.Tag;
import com.gc.repository.customerrespository.TagCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long>, TagCustomRepository {

}
