package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.utils.Create;
import ru.practicum.shareit.utils.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {
    long id;
    @NotNull(groups = {Create.class})
    String name;
    @NotNull(groups = {Create.class})
    @Email(groups = {Update.class, Create.class})
    String email;
}