package com.minsk.pendulum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class ImageEntity extends AbstractBaseImageEntity {

    @Column(name = "hash", nullable = false)
    private int hash;

    @Column(name = "file_path", nullable = false)
    private String filePath;

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ImageEntity() {
    }

    public ImageEntity(int hash, String filePath) {
        this.hash = hash;
        this.filePath = filePath;
    }

    public ImageEntity(Integer id, int hash, String filePath) {
        super(id);
        this.hash = hash;
        this.filePath = filePath;
    }
}

