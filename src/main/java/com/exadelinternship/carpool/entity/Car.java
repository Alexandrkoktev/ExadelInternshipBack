package com.exadelinternship.carpool.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk")
    private User user;

    @Column(name="carInformation")
    private String carInformation;

    @Column(name="deleted")
    private boolean deleted;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="car")
    private Set<ActiveRoute> activeRoutes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCarInformation() {
        return carInformation;
    }

    public void setCarInformation(String carInformation) {
        this.carInformation = carInformation;
    }

    public Set<ActiveRoute> getActiveRoutes() {
        return activeRoutes;
    }

    public void setActiveRoutes(Set<ActiveRoute> activeRoutes) {
        this.activeRoutes = activeRoutes;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
