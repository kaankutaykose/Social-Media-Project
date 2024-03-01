package com.SofIto.SocialNetwork.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action=OnDeleteAction.CASCADE)
    User user;
    String title;
    @Lob
    @Column(columnDefinition = "text")
    String text;

    @Lob
    @Column(columnDefinition = "text")
    String image_url;

    @Lob
    @Column(columnDefinition = "text")
    String video_url;

}
