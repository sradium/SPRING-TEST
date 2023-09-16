package com.catedra.test.domain.post.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.catedra.test.domain.user.entity.User;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Table("posts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    @Id
    private Long id;
    private String title;
    private String content;
    private User user;

    @Override
    public String toString() {
        return "post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user_email=" + user.getEmail() +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
