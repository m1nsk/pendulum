package com.minsk.pendulum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "devices")
public class Device extends AbstractNamedEntity {

    @Column(name = "serial", nullable = false)
    private int serial;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "channel_device",
            joinColumns = @JoinColumn(name = "device_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id"))
    private List<Channel> channels;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
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

