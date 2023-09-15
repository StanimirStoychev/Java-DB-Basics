package bg.softuni.nextleveltech.services;

import bg.softuni.nextleveltech.models.dtos.user.LoginDTO;
import bg.softuni.nextleveltech.models.dtos.user.RegisterDTO;
import bg.softuni.nextleveltech.models.entities.User;
import bg.softuni.nextleveltech.repositories.UserRepository;
import bg.softuni.nextleveltech.utils.validator.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validator;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, ValidationUtils validator) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    public Optional<User> login(LoginDTO loginDTO) {
        return this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
    }

    public boolean register(RegisterDTO registerDTO) {
        Optional<User> optionalUser = this.userRepository.findByUsername(registerDTO.getUsername());

        if (optionalUser.isEmpty() && validator.isValid(registerDTO) && !registerDTO.getPassword().equals("")
                && registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {

            User user = this.modelMapper.map(registerDTO, User.class);
            this.userRepository.saveAndFlush(user);

            return true;
        }

        return false;
    }
}
