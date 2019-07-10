package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
