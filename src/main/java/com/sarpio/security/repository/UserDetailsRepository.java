package com.sarpio.security.repository;

import com.sarpio.security.model.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailEntity, Long> {

}
