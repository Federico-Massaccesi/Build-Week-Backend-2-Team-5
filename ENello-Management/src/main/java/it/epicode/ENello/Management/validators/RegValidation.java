package it.epicode.ENello.Management.validators;

import it.epicode.ENello.Management.entities.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


import java.util.List;

public record RegValidation(
        @NotNull
                @Email
        String email,
        @NotNull
                @Length(min = 8)
        String password,
        @NotNull
        String username,
         @NotNull
         List<RoleEntity> roles



) {
}
