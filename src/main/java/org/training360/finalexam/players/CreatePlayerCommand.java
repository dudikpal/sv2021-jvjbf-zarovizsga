package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreatePlayerCommand {

    @NotBlank
    private String name;

    private LocalDate birthDate;

    private PositionType position;

}
