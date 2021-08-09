package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@AllArgsConstructor
public class TeamController {

    private TeamService teamService;


    @GetMapping
    public List<TeamDTO> listAllTeams() {
        return teamService.listAllTeams();
    }


    @PostMapping
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command) {
        return teamService.createTeam(command);
    }


    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayer(@PathVariable("id") long id, @RequestBody CreatePlayerCommand command) {
        return teamService.addNewPlayer(id, command);
    }


    @PutMapping("/{id}/players")
    public TeamDTO addExistingPlayer(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command) {
        return teamService.addExistingPlayer(id, command);
    }
}
