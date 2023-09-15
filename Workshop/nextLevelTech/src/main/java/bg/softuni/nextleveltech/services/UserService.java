package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.models.dtos.user.LoginDTO;
import bg.softuni.nextleveltech.models.entities.User;
import bg.softuni.nextleveltech.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(LoginDTO loginDTO) {
        return this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
