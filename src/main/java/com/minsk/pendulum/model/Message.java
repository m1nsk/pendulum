package com.minsk.pendulum.model;

import com.minsk.pendulum.model.User;

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
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDateTime date;

    @ManyToMany(mappedBy = "devices")
    private Set<Channel> channels;

    public Message(String message, @NotNull Channel channel, @NotNull User user,
                   @NotNull LocalDateTime date, Set<Channel> channels) {
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.date = date;
        this.channels = channels;
    }

    public Message(Integer id, String message, @NotNull Channel channel,
                   @NotNull User user, @NotNull LocalDateTime date, Set<Channel> channels) {
        super(id);
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.date = date;
        this.channels = channels;
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

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", channel=" + channel +
                ", user=" + user +
                ", date=" + date +
                ", channels=" + channels +
                ", id=" + id +
                '}';
    }
}
