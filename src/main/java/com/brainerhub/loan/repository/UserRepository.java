package com.brainerhub.loan.repository;

import com.brainerhub.loan.entity.User;
import com.brainerhub.loan.responseDto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<UserDto> findByEmail(String email);

    User findUserByEmail(String email);
}
