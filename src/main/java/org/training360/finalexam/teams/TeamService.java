package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;
import org.training360.finalexam.players.PositionType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    private ModelMapper modelMapper;

    private TeamRepository teamRepository;

    private PlayerRepository playerRepository;

    public List<TeamDTO> listAllTeams() {
        return teamRepository.findAll().stream()
                .map(t -> modelMapper.map(t, TeamDTO.class))
                .collect(Collectors.toList());
    }


    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }


    public TeamDTO addNewPlayer(long id, CreatePlayerCommand command) {
        Team team = getTeamById(id);
        Player player = new Player(command.getName(), command.getBirthDate(), command.getPosition());
        team.addPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }


    private Team getTeamById(long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException());
    }


    @Transactional
    public TeamDTO addExistingPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find team"));
        Player player = playerRepository.getById(command.getId());
        if (player.getTeam() == null
            && teamHasFreeSamePosition(team, player.getPosition())) {
            team.addPlayer(player);
        }
        return modelMapper.map(team, TeamDTO.class);
    }


    private boolean teamHasFreeSamePosition(Team team, PositionType type) {
        return team.getPlayers().stream()
                .filter(player -> player.getPosition() == type)
                .count() < 2;
    }
}
