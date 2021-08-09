package org.training360.finalexam.players;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping
    public List<PlayerDTO> listAllPlayers() {
        return playerService.listAllPlayers();
    }


    @GetMapping("/{id}")
    public PlayerDTO findPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }


    @PostMapping
    public PlayerDTO createPlayer(@RequestBody CreatePlayerCommand command) {
        return playerService.createPlayer(command);
    }


    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") long id) {
        playerService.deletePlayer(id);
    }
}
