package com.SofIto.SocialNetwork.Repositories;

import com.SofIto.SocialNetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
