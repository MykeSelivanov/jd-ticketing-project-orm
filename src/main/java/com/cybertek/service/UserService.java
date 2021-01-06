package com.cybertek.service;

import com.cybertek.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findByUserName(String username);

    List<UserDTO> listAllUsers();
    List<UserDTO> listAllByRole(String role);

    void save(UserDTO dto);

    UserDTO update(UserDTO dto);

    void delete(String username);
    void deleteByUserName(String username);


}
