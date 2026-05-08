package jonathandev.dashboard_demo_backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jonathandev.dashboard_demo_backend.dto.request.CreateUserRequest;
import jonathandev.dashboard_demo_backend.dto.request.UpdateUserRequest;
import jonathandev.dashboard_demo_backend.entity.AppUser;
import jonathandev.dashboard_demo_backend.repository.AppUserRepository;

@Service
@Transactional
public class UserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser create(CreateUserRequest request) {
        if (userRepository.findByEmailIgnoreCase(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        AppUser user = new AppUser();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPasswordHash(passwordEncoder.encode(request.passwordHash()));
        user.setRole(request.role() != null ? request.role() : "USER");
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AppUser findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public AppUser update(Long id, UpdateUserRequest request) {
        AppUser user = findById(id);

        if (request.email() != null && !request.email().equalsIgnoreCase(user.getEmail())) {
            userRepository.findByEmailIgnoreCase(request.email()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
                }
            });
            user.setEmail(request.email());
        }

        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.passwordHash() != null) {
            user.setPasswordHash(passwordEncoder.encode(request.passwordHash()));
        }
        if (request.role() != null) {
            user.setRole(request.role());
        }

        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
    }
}