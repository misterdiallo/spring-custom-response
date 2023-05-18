package com.misterdiallo.backend.springcustomresponse.repository;

import com.misterdiallo.backend.springcustomresponse.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
