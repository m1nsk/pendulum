package com.minsk.pendulum.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "messages")
public class Message extends AbstractBaseMessageEntity {
    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    @NotNull
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private Date date = new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "message_image",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private List<ImageEntity> imageEntities;

    public Message() {
    }

    public Message(Integer id) {
        super(id);
    }

    public Message(String message, @NotNull Channel channel, @NotNull User user) {
        this.message = message;
        this.channel = channel;
        this.user = user;
        this.date = date;
    }

    public Message(Integer id, String message, @NotNull Channel channel, @NotNull User user, Date date) {
        super(id);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }
}
