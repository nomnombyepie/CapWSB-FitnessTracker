package pl.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

record UserEmailDto(@Nullable Long Id, String firstName, String lastName,
                    @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                    String email) {

}
