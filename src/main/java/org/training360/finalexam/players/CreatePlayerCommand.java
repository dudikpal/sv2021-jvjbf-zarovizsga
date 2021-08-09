package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreatePlayerCommand {

    private String name;

    private LocalDate birthDate;

    private PositionType position;

}
