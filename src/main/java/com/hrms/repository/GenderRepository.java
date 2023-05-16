package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.GenderEntity;

public interface GenderRepository extends JpaRepository<GenderEntity, Integer> {

}
