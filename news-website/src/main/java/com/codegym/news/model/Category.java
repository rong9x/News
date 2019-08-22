package com.codegym.news.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false, updatable = false)
    private String creator = "admin";

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date lastEditDate;

    public Category() {
    }

    public Category(String categoryName, List<Post> posts, String creator, Date dateCreate, Date lastEditDate) {
        this.categoryName = categoryName;
//        this.posts = posts;
        this.creator = creator;
        this.dateCreate = dateCreate;
        this.lastEditDate = lastEditDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    /*public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }*/
}
