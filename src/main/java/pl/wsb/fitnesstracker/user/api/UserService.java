package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User crtUser(User user);

    void dltUser(Long userId);

    User updUser(User user);

    List<User> findByFirstName(String firstName);

    List<User> findBySurname(String surname);

    List<User> findByBirthdate(LocalDate birthdate);
}
