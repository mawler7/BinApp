package com.management.warehouse.model.user;


import com.management.warehouse.exception.InvalidEmailException;
import com.management.warehouse.exception.InvalidUserOrPasswordException;
import com.management.warehouse.exception.UserAlreadyExistException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (!matcher.find()) {
            throw new InvalidEmailException("Invalid email provided: " + email);
        }
        if (userRepository.findByEmailAllIgnoreCase(email).isPresent()) {
            throw new UserAlreadyExistException("User with the following email already exists: " + email);
        }
    }

    private void validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (!matcher.find()) {
            throw new InvalidUserOrPasswordException("Invalid password: " + password + ". " +
                    "Password has to have at least one upper case English letter, at least one lower case English letter,"
                    + " at least one digit, at least one special character, minimum eight in length");
        }
    }

    public List<UserDto> getUserList() {
        List<User> userList = userRepository.findAll();
        return UserConverter.convertUserListToDtoList(userList);
    }

    public UserDto registerNewUserAccount(UserRegistrationDto userRegistrationDto) {
        validateEmail(userRegistrationDto.getEmail());
        validatePassword(userRegistrationDto.getPassword());

        User user = User.builder()
                .id(UUID.randomUUID())
                .firstName(userRegistrationDto.getFistName())
                .lastName(userRegistrationDto.getLastName())
                .active(true)
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .role(UserRole.ROLE_USER)
                .email(userRegistrationDto.getEmail())
                .build();
        return UserConverter.convertUserToDto(userRepository.save(user));

    }


}