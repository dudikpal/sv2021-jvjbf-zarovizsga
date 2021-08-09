package org.training360.finalexam.players;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {

    private ModelMapper modelMapper;

    private PlayerRepository playerRepository;

    public List<PlayerDTO> listAllPlayers() {
        return playerRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PlayerDTO.class))
                .collect(Collectors.toList());
    }

    public PlayerDTO findPlayerById(Long id) {
        return modelMapper.map(playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("cannot find player")),
                PlayerDTO.class);
    }


    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }
}
