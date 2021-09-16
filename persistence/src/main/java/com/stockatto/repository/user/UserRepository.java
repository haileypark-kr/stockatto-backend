package com.stockatto.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stockatto.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
