package it.capone.spring.service;

import java.util.List;

import it.capone.spring.model.Player;

public interface IPlayerService {

    List<Player> players();

    Player getPlayer(Long id);

    void addPlayer(Player player);

    void updatePlayer(Long id, Player player);

    void deletePlayer(Long id);

    List<Player> searchPlayer(String keyword);

}
