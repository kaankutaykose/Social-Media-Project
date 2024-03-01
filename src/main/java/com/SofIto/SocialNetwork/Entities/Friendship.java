package com.SofIto.SocialNetwork.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="friendship")
@Data
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    Long senderId;
    Long receiveId;

}
