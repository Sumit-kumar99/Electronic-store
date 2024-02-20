package com.sumit.electronic.store.ElectronicStore.services.impl;

import com.sumit.electronic.store.ElectronicStore.dtos.PageableResponse;
import com.sumit.electronic.store.ElectronicStore.dtos.UserDto;
import com.sumit.electronic.store.ElectronicStore.entities.Role;
import com.sumit.electronic.store.ElectronicStore.entities.User;
import com.sumit.electronic.store.ElectronicStore.exceptions.ResourceNotFoundException;
import com.sumit.electronic.store.ElectronicStore.repositories.RoleRepository;
import com.sumit.electronic.store.ElectronicStore.repositories.UserRepository;
import com.sumit.electronic.store.ElectronicStore.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${user.profile.image.path}")
    private String imagePath;

    @Autowired
    private ModelMapper mapper;

    @Value("${normal.role.id}")
    private String normalRoleId;

    @Autowired
    private RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());
//        String userId1 = UUID.randomUUID().toString();
//        System.out.println(userId1);
//        userDto.setUserId(userId1);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User saveuser =  dtoToEntity(userDto);
        //fetch role of normal and set it to user
        Role role = roleRepository.findById(normalRoleId).get();
        saveuser.getRoles().add(role);
        User save = userRepository.save(saveuser);
        UserDto newDto = entityToDto(save);
        return userDto;

    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id not Found"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User save = userRepository.save(user);
        UserDto updatedDto = entityToDto(save);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id not Found"));
        String fullPath = imagePath + user.getImageName();

        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException ex) {
            logger.info("User image not found in folder");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        userRepository.delete(user);

    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id not Found"));
        return entityToDto(user);
    }


    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {


        Sort sort =  (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) :(Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> page = userRepository.findAll(pageable);


        List<User> all = page.getContent();
        List<UserDto> collect = all.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        PageableResponse<UserDto> response = new PageableResponse<>();
        response.setContent(collect);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLastPage(page.isLast());
        return response;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("email Id not Found!!"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> byNameContaining = userRepository.findByNameContaining(keyword);
        List<UserDto> collect = byNameContaining.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Optional<User> findUserByEmailOptional(String email) {
        return userRepository.findByEmail(email);
    }


    private UserDto entityToDto(User user) {

//        UserDto build = UserDto.builder()
//                .userId(user.getUserId())
//                .name(user.getName())
//                .about(user.getAbout())
//                .password(user.getPassword())
//                .gender(user.getGender())
//                .email(user.getEmail())
//                .imageName(user.getImageName()).build();
//        return build;

        return mapper.map(user, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {

//        User build = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .about(userDto.getAbout())
//                .password(userDto.getPassword())
//                .gender(userDto.getPassword())
//                .imageName(userDto.getImageName()).build();
//        return build;
        return mapper.map(userDto, User.class);
    }
}
