package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    //dodanie wyszukiwanie użytkowników po wieku starszym niż zdefiniowany
    List<User> findUsersOlderThan(LocalDate date);

    //wyszukiwanie użytkowników po e-mailu, bez rozróżniania wielkości liter
    List<User> findUsersByEmailWoCase(String emailFragment);

    // wyszukiwanie uzytkowników po fragmencie nazwy
    List<User> findUserByEmailFragment(String emailFragment);
}
