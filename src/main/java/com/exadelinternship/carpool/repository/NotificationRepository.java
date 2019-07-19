package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    Set<Notification> findAllByUser_Id(long id);
}
