package com.cybertek.implementation;

import com.cybertek.dto.UserDTO;
import com.cybertek.entity.User;
import com.cybertek.mapper.UserMapper;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userEntityList = userRepository.findAll(Sort.by("firstName"));
        return userEntityList.stream().map(userEntity -> {return userMapper.convertToDto(userEntity);}).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User userEntity = userRepository.findByUserName(username);
        return userMapper.convertToDto(userEntity);
    }

    @Override
    public void save(UserDTO dto) {
        User user = userMapper.convertToEntity(dto);
        userRepository.save(user);
    }

    @Override
    public UserDTO update(UserDTO dto) {
        // Find current user from database
        User userEntity = userRepository.findByUserName(dto.getUserName());
        // Map update user dto to entity object
        User convertedUser = userMapper.convertToEntity(dto);
        // Set id to the converted object
        convertedUser.setId(userEntity.getId());
        // Save updated user
        userRepository.save(convertedUser);

        return findByUserName(dto.getUserName());
    }

    @Override
    public void delete(String username) {
        User userEntity = userRepository.findByUserName(username);
        userEntity.setIsDeleted(true);
        userRepository.save(userEntity);
    }

    // hard delete (not a good practice, no one deletes data from the database)
    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

}
