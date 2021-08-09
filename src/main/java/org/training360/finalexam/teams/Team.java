package org.training360.finalexam.teams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private List<Player> players;

    public Team(String name) {
        this.name = name;
    }


    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}
