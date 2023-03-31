package com.ognjen.projekat.service;


import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.mapper.UserMapper;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.repository.UserRepository;
import com.ognjen.projekat.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<User> getAll() {
        return mapper.toDomainList(userRepository.findAll());
    }

    public User getById(Integer customerId) {
        return mapper.toDomain(getUserEntityById(customerId));
    }

    public User getByUsername(String username) {
        return mapper.toDomain(userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("User not found for username: " + username)
                ));
    }

    @Transactional
    public User create(User user) {

        var entity = mapper.toEntity(user);

        return mapper.toDomain(userRepository.save(entity));
    }

    @Transactional
    public void delete(Integer customerId) {
        userRepository.delete(getUserEntityById(customerId));
    }

    @Transactional
    public void update(User updatedUser) {

        var existingUserEntity = getUserEntityById(updatedUser.getId());

        mapper.update(existingUserEntity, updatedUser);
    }

    private UserEntity getUserEntityById(Integer customerId) {
        return userRepository.findById(customerId).orElseThrow(() ->
                new NotFoundException("User not found with id: " + customerId));
    }

}
