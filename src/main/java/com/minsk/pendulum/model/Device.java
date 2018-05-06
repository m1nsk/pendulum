package com.minsk.pendulum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "devices")
public class Device extends AbstractNamedEntity {

    @Column(name = "serial", nullable = false)
    private int serial;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Device() {
    }

    public Device(Integer id, String name) {
        super(id, name);
    }

    public Device(int serial, @NotNull User user) {
        this.serial = serial;
        this.user = user;
    }

    public Device(Integer id, String name, int serial, @NotNull User user) {
        super(id, name);
        this.serial = serial;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Device{" +
                "serial=" + serial +
                ", user=" + user +
                ", id=" + id +
                '}';
    }
}

