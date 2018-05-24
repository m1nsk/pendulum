package com.minsk.pendulum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "images")
public class ImageEntity extends AbstractBaseImageEntity {

    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "message_image",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<Message> messages;

    public void addMessage(Message message) {
        if (messages == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public ImageEntity() {
    }

    public ImageEntity(String hash, String filePath) {
        this.hash = hash;
        this.filePath = filePath;
    }

    public ImageEntity(Integer id, String hash, String filePath) {
        super(id);
        this.hash = hash;
        this.filePath = filePath;
    }
}

