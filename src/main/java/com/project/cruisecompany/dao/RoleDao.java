package com.project.cruisecompany.dao;

import com.project.cruisecompany.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
