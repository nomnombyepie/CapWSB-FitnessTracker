package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    List<User> findByFirstNameIgnoreCase(String firstName);


    // older than
    default List<User> findOlderThan(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .collect(Collectors.toList());
    }

    // szukanie po fragmencie nazwy
    default List<User> findByPartEmail(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null && user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }

    default List<User> findByFirstAndLastName(String firstName, String lastName) {
        return findAll().stream()
                .filter(u -> u.getFirstName().equalsIgnoreCase(firstName)
                        && u.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    default List<User> findByBirthdate(LocalDate birthdate) {
        return findAll().stream()
                .filter(u -> u.getBirthdate().isEqual(birthdate))
                .collect(Collectors.toList());
    }

    default List<User> findByEmailContainingIgnoreCase(String fragment) {
        return null;
    }


}
