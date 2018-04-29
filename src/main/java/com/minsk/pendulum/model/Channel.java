package com.minsk.pendulum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "channels")
public class Channel extends AbstractBaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_message")
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "channel", fetch = FetchType.LAZY)
    private List<Message> messages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "channel_device",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private List<Device> devices;

    public Channel() {
    }

    public Channel(Integer id) {
        super(id);
    }

    public Channel(@NotNull User user) {
        this.user = user;
    }

    public Channel(Integer id, Message message, @NotNull User user, List<Message> messages, List<Device> devices) {
        super(id);
        this.message = message;
        this.user = user;
        this.messages = messages;
        this.devices = devices;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Channel{" +
                ", user=" + user +
                ", devices=" + devices +
                ", id=" + id +
                '}';
    }
}
