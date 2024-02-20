package com.sumit.electronic.store.ElectronicStore.services;

import com.sumit.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.sumit.electronic.store.ElectronicStore.dtos.UserDto;
import com.sumit.electronic.store.ElectronicStore.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto,String userId);

    //delete
    void deleteUser(String userId);

    //get Single User
    UserDto getUserById(String userId);

    //get single user by email
    UserDto getUserByEmail(String email);

    //get All User
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //serach User
    List<UserDto> searchUser(String keyword);


    Optional<User> findUserByEmailOptional(String email);
}
