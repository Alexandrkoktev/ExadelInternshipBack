package com.exadelinternship.carpool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exadelinternship.carpool.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
