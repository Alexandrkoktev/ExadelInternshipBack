package com.exadelinternship.carpool.repository;

import com.exadelinternship.carpool.entity.ActiveRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Repository
public interface ActiveRouteRepository extends JpaRepository<ActiveRoute,Long> {
    Set<ActiveRoute> getByUser_Id(Long id);
    List<ActiveRoute> getByRouteAfter(Timestamp datetime);
}
