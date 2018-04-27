package com.minsk.pendulum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

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

    @OneToMany(mappedBy = "channel")
    private Set<Message> messages;

    @ManyToMany
    @JoinTable(name = "channel_device",
            joinColumns = @JoinColumn(name = "channel_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id"))
    private Set<Device> devices;

    public Channel() {
    }

    public Channel(Integer id) {
        super(id);
    }

    public Channel(Message message, @NotNull User user, Set<Message> messages, Set<Device> devices) {
        this.message = message;
        this.user = user;
        this.messages = messages;
        this.devices = devices;
    }

    public Channel(Integer id, Message message, @NotNull User user, Set<Message> messages, Set<Device> devices) {
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

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "message=" + message +
                ", user=" + user +
                ", messages=" + messages +
                ", devices=" + devices +
                ", id=" + id +
                '}';
    }
}
