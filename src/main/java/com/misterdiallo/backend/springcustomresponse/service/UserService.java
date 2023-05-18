package com.misterdiallo.backend.springcustomresponse.service;

import com.misterdiallo.backend.springcustomresponse.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity Post(UserEntity params);

    List<UserEntity> Get();

    UserEntity Get(int id);

    UserEntity Update(UserEntity params, int id);

    String Delete(int id);
}
