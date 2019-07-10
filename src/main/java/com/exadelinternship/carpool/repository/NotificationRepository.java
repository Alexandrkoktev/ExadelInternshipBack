package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
