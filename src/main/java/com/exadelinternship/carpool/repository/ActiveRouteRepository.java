package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.ActiveRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveRouteRepository extends JpaRepository<ActiveRoute,Long> {
}
