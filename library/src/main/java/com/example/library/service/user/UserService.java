package com.example.library.service.user;

import com.example.library.controller.user.dto.*;
import com.example.library.infrastructure.entity.AuthEntity;
import com.example.library.infrastructure.entity.UserEntity;
import com.example.library.infrastructure.repository.AuthRepository;
import com.example.library.infrastructure.repository.UserRepository;
import com.example.library.service.auth.OwnerService;
import com.example.library.service.book.error.BookNotFound;
import com.example.library.service.user.error.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends OwnerService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(AuthRepository authRepository, UserRepository userRepository) {
        super(authRepository);
        this.userRepository = userRepository;
    }
    private GetUserDto mapUser(UserEntity user) {
        return new GetUserDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }
    @PreAuthorize("hasRole('ADMIN')")
    public GetUsersPageResponseDto getAll(int page, int size) {
        Page<UserEntity> userPage;

        Pageable pageable = PageRequest.of(page, size);
        userPage = userRepository.findAll(pageable);

        List<GetUserDto> userDto = userPage.getContent().stream().map(this::mapUser).toList();
        return new GetUsersPageResponseDto(userDto, userPage.getNumber(), userPage.getTotalElements(), userPage.getTotalPages(), userPage.hasNext());
    }
    public GetUserDto getOneById(long id){
        AuthEntity auth = authRepository.findById(id).orElseThrow(() -> UserNotFound.createWithId(id));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }
    public GetUserDto getUserByUsername(String username) {
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(() -> UserNotFound.createWithUsername(username));
        UserEntity userEntity = authEntity.getUser();
        return new GetUserDto(userEntity.getId(), userEntity.getName(), userEntity.getLastName(), userEntity.getEmail());
    }
    @PreAuthorize("hasRole('ADMIN') or (isAuthenticated() and this.isOwner(authentication.name,  #id))")
    public PatchUserResponseDto update(long id, PatchUserDto dto) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> UserNotFound.createWithId(id));

        dto.getEmail().ifPresent(user::setEmail);
        dto.getName().ifPresent(user::setName);
        dto.getLastName().ifPresent(user::setLastName);

        userRepository.save(user);
        return new PatchUserResponseDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }

    public void delete(long id) {
        if (!(authRepository.existsById(id) && userRepository.existsById(id))) {
            throw BookNotFound.create(id);
        }
        authRepository.deleteById(id);
        userRepository.deleteById(id);
    }

}
