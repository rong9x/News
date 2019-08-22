package com.codegym.news.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false, columnDefinition = "TEXT")
    private String tittle;

    @NotEmpty
    @Column(nullable = false, columnDefinition = "TEXT")
    private String introduce;

    @NotEmpty
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date postDate;

    @Column(updatable = false)
    private String image = "https://cdn.wpforms.com/wp-content/uploads/2016/10/How-to-Allow-Users-to-Submit-Blog-Posts-on-Your-WordPress-Site.png";

    @Column(updatable = false)
    private String author = "admin";

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastEditDate;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Post() {
    }

    public Post(String tittle, String introduce, String content, Date postDate, String image, String author, Date lastEditDate) {
        this.tittle = tittle;
        this.introduce = introduce;
        this.content = content;
        this.postDate = postDate;
        this.image = image;
        this.author = author;
        this.lastEditDate = lastEditDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
