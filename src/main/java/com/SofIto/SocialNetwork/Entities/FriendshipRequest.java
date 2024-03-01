package com.SofIto.SocialNetwork.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="friendship_request")
@Data
public class FriendshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long senderId;
    Long receiveId;
    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderid",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    User senderid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiveid",nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    User receiveid;
     */
}
