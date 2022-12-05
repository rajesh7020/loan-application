package com.brainerhub.loan.repository;

import com.brainerhub.loan.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByIdIn(List<Long> idList);

    Role findByRole(String role);
}
