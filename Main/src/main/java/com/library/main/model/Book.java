package com.library.main.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Blob;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "ISSN", nullable = false)
    private long ISSN;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writers", nullable = false)
    private String writers;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User publisher;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Lob
    @Column(name = "photo")
    private Blob photo;

    @Column(name = "keywords")
    private String keywords;

    @CreationTimestamp
    @Column(name = "createDateTime")
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;

    public Book(long ISSN, String title, String writers, User publisher, LocalDateTime date, Blob photo, String keywords) {
        this.ISSN = ISSN;
        this.title = title;
        this.writers = writers;
        this.publisher = publisher;
        this.date = date;
        this.photo = photo;
        this.keywords = keywords;
    }

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getISSN() {
        return ISSN;
    }

    public void setISSN(long ISSN) {
        this.ISSN = ISSN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
