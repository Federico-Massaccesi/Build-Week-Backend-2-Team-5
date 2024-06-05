package it.epicode.ENello.Management.validators;

import it.epicode.ENello.Management.entities.RoleEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record LogValidation(
        @NotNull
        @Length(min = 8)
        String password,
        @NotNull
        String username
) {
}
