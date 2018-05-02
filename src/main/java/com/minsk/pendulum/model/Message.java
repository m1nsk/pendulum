package com.minsk.pendulum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "messages")
public class Message extends AbstractBaseMessageEntity {
    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    @NotNull
    @JsonIgnore
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    public Message() {
    }

    public Message(Integer id) {
        super(id);
    }

    public Message(String message, @NotNull Channel channel, @NotNull User user) {
        this.message = message;
        this.channel = channel;
        this.user = user;
    }

    public Message(Integer id, String message, @NotNull Channel channel,
                   @NotNull User user) {
        super(id);
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", channel=" + channel +
                ", user=" + user +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
