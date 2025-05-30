package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;


    // all users
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    //szukanie uzytkownikow
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleUser)
//                .map(userMapper::toSimpleDto)
                .toList();
    }

    //szukanie po id
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.getUser(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }
    // wyszukiwanie po imieniu
    @GetMapping("/search/name")
    public List<UserDto> getUsersByName(@RequestParam String name) {

        return userService.findByFirstName(name).stream()
                .map(userMapper::toDto)
                .toList();
    }

    // wyszukiwanie po nazwisku
    @GetMapping("/search/surname")
    public List<UserDto> getUsersBySurname(@RequestParam String surname) {
        return userService.findBySurname(surname).stream()
                .map(userMapper::toDto)
                .toList();
    }

    // wyszukiwanie po dacie urodzenia (yyyy-MM-dd)
    @GetMapping("/search/birthdate")
    public List<UserDto> getUsersByBirthdate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate) {
        return userService.findByBirthdate(birthdate).stream()
                .map(userMapper::toDto)
                .toList();
    }

    // wyszukiwanie po fragmencie e-maila
    @GetMapping("/search/email")
    public List<UserDto> getUsersByEmailFragment(@RequestParam String fragment) {
        return userService.findByEmailFragment(fragment).stream()
                .map(userMapper::toDto)
                .toList();
    }


    // szukanie uzytkownika po email
    @GetMapping("/email")
    public List<UserDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    //szuanie older than {date}
    @GetMapping("/older/{date}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate date) {
            return userService.findOlderThan(date)
                    .stream().map(userMapper::toDto)
                    .toList();
    }



    //add user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userService.crtUser(user));
    }


//



//    @GetMapping("/older/{date}")
//    public List<UserDto> findUsersOlderThan(@PathVariable LocalDate date) {
//        return userService.findAllUsers().stream()
//                .filter(user -> user.getBirthdate().isBefore(date))
//                .map(userMapper::toDto)
//                .toList();
//    }
    //upd
    //upd user
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setId(id);
        return userMapper.toDto(userService.updUser(user));
    }

    //dlt user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.dltUser(id);
    }

}