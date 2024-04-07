package com.example.library.controller.user;

import com.example.library.controller.user.dto.*;
import com.example.library.service.user.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Identification success"),
            @ApiResponse(responseCode = "404", description = "Identification fail", content = @Content())})
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search success"),
            @ApiResponse(responseCode = "404", description = "Search fail", content = @Content())})
    public ResponseEntity<GetUserDto> getOneById(@PathVariable long id){
        GetUserDto dto = userService.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200")
    public ResponseEntity<GetUsersPageResponseDto> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        GetUsersPageResponseDto dto = userService.getAll(page, size);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @ApiResponse(responseCode = "200", description ="Deletion success")
    public ResponseEntity<PatchUserResponseDto> update(@PathVariable long id, @RequestBody PatchUserDto dto) {
        PatchUserResponseDto responseDto = userService.update(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
