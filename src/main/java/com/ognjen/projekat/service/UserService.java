package com.ognjen.projekat.service;


import com.ognjen.projekat.exception.AuthorizationFailedException;
import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.exception.UsedAttributeException;
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

    public User getById(Integer userId) {
        return mapper.toDomain(getUserEntityById(userId));
    }

    public User getByUsername(String username) {
        return mapper.toDomain(userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("User not found for username: " + username)
                ));
    }

    @Transactional
    public User create(User user) {

        validateUsernameIsFree(user.getUsername());

        var entity = mapper.toEntity(user);

        return mapper.toDomain(userRepository.save(entity));
    }

    @Transactional
    public void delete(Integer userId, Integer loggedUserId) {
        validateUserExists(userId);

        authorizeUserById(userId, loggedUserId);

        userRepository.deleteById(userId);
    }


    @Transactional
    public void update(User updatedUser, Integer loggedUserId) {

        validateUserExists(updatedUser.getId());

        authorizeUserById(updatedUser.getId(), loggedUserId);

        var existingUserEntity = getUserEntityById(updatedUser.getId());

        mapper.update(existingUserEntity, updatedUser);
    }

    private UserEntity getUserEntityById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("User not found with id: " + userId));
    }

    private void validateUsernameIsFree(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UsedAttributeException("User already exists with username: " + username);
        }
    }

    private void validateUserExists(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    private static void authorizeUserById(Integer userId, Integer loggedUserId) {
        if (!loggedUserId.equals(userId)) {
            throw new AuthorizationFailedException("Authorization has failed");
        }
    }
}
