package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    //dodanie
    @Override
    public User crtUser(final User user) {log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("Can't create");
        }
        return userRepository.save(user);
    }

    //aktualizacja
    @Override
    public User updUser(final User user) {log.info("Updating User {}", user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("Can't update");
        }
        return userRepository.save(user);
    }

    //usuniecie
    @Override
    public void dltUser(final Long userId) {log.info("Deleting User ID {}", userId); userRepository.deleteById(userId);
    }

    @Override
    public List<User> findByEmailFragment(String fragment) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(fragment.toLowerCase()))
                .toList();
    }



    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // older than
    @Override
    public List<User> findOlderThan(LocalDate date) {return userRepository.findOlderThan(date);}

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }

    @Override
    public List<User> findByName(String firstName) {
        String lowerFirstName = firstName.toLowerCase();
        return userRepository.findAll().stream()
                .filter(user -> user.getFirstName() != null && user.getFirstName().toLowerCase().contains(lowerFirstName))
                .toList();
    }

    @Override
    public List<User> findBySurname(String surname) {
        return userRepository.findAll().stream()
                .filter(user -> user.getLastName().equalsIgnoreCase(surname))
                .toList();
    }
    @Override
    public List<User> findByBirthdate(LocalDate birthdate) {
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate().equals(birthdate))
                .toList();
    }


}