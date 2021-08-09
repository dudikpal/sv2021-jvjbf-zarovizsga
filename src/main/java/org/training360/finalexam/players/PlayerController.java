package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
public class PlayerController {

    private PlayerService playerService;


    @GetMapping
    public List<PlayerDTO> listAllPlayers() {
        return playerService.listAllPlayers();
    }


    @GetMapping("/{id}")
    public PlayerDTO findPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }


    @PostMapping
    public PlayerDTO createPlayer(@Valid @RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }


    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable long id) {
        playerService.deletePlayer(id);
    }
}
