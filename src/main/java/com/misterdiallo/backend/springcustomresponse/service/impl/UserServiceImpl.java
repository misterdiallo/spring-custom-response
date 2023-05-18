package com.misterdiallo.backend.springcustomresponse.service.impl;

import com.misterdiallo.backend.springcustomresponse.entity.UserEntity;
import com.misterdiallo.backend.springcustomresponse.repository.UserRepository;
import com.misterdiallo.backend.springcustomresponse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity Post(UserEntity params) {
        userRepository.save(params);
        return params;
    }

    @Override
    public List<UserEntity> Get() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity Get(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserEntity Update(UserEntity params, int id) {

        UserEntity user =  userRepository.findById(id).get();
        user.setName(params.getName());
        user.setEmail(params.getEmail());

        return userRepository.save(user);

    }

    @Override
    public String Delete(int id) {
        userRepository.deleteById(id);
        return "User(" + id + ")" + " has been deleted!";
    }
}