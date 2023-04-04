package com.ognjen.projekat.service;

import com.ognjen.projekat.exception.NotFoundException;
import com.ognjen.projekat.exception.UsedAttributeException;
import com.ognjen.projekat.mapper.UserMapper;
import com.ognjen.projekat.model.User;
import com.ognjen.projekat.repository.UserRepository;
import com.ognjen.projekat.repository.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ognjen.projekat.EntityBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Spy
    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void getAll() {
        List<UserEntity> userEntities = Collections.singletonList(userEntity());

        when(userRepository.findAll()).thenReturn(userEntities);

        List<User> users = userService.getAll();

        assertEquals(USER_ID, users.get(0).getId());

    }

    @Test
    void getById() {

        when(userRepository.findById(1)).thenReturn(
                Optional.of(userEntity()));

        User user = userService.getById(1);

        assertEquals(USER_ID, user.getId());

    }

    @Test
    void getByIdException() {

        when(userRepository.findById(10)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> userService.getById(10));

    }

    @Test
    void getByUsername() {
        when(userRepository.findByUsername("test")).thenReturn(
                Optional.of(userEntity()));

        User user = userService.getByUsername("test");

        assertEquals(USER_ID, user.getId());
    }

    @Test
    void getByUsernameException() {
        when(userRepository.findByUsername("test")).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> userService.getByUsername("test"));

    }

    @Test
    void create() {

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity());

        User user = userService.create(user());

        assertEquals(USER_ID, user.getId());
    }

    @Test
    void createException() {

        when(userRepository.save(any(UserEntity.class))).thenThrow(UsedAttributeException.class);

        assertThrows(UsedAttributeException.class, () -> userService.create(user()));

    }

    @Test
    void delete() {
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        userService.delete(USER_ID);
        verify(userRepository).deleteById(USER_ID);
    }

    @Test
    void deleteException() {
        doThrow(NotFoundException.class).when(userRepository).existsById(USER_ID);

        assertThrows(NotFoundException.class, () -> userService.delete(USER_ID));
    }

    @Test
    void update() {


    }
}