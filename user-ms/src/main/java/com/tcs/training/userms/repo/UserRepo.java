package com.tcs.training.userms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.training.userms.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
