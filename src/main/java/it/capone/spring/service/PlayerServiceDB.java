package it.capone.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.capone.spring.model.Player;
import it.capone.spring.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value = "serviceLista")
@Transactional
public class PlayerServiceDB implements IPlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> players() {
	log.info("Classe serviceListaDB --> players");
	return playerRepository.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
	log.info("Classe serviceListaDB --> singolo player");
	return playerRepository.findById(id).orElse(null);

    }

    @Override
    public void addPlayer(Player player) {
	log.info("Classe serviceListaDB --> aggiunta del player {}", player);
	playerRepository.save(player);

    }

    @Override
    public void updatePlayer(Long id, Player player) {
	log.info("Classe serviceListaDB --> aggiornamento del player con id {}", id);
	Player findPlayer = this.getPlayer(id);
	if (findPlayer != null) {
	    // findPlayer.setId(player.getId());
	    findPlayer.setName(player.getName());
	    findPlayer.setLastName(player.getLastName());
	    findPlayer.setTeam(player.getTeam());
	    findPlayer.setPosition(player.getPosition());
	    playerRepository.save(findPlayer);
	    log.info("Player con id {} aggiornato.", id);
	} else
	    log.info("Player con id {} non trovato.", id);

    }

    @Override
    public void deletePlayer(Long id) {
	log.info("Classe serviceListaDB --> eliminazione del player con id {}", id);
	playerRepository.deleteById(id);
    }

    @Override
    public List<Player> searchPlayer(String keyword) {
	log.info("Parametro passato: {}", keyword);
	return playerRepository.search(keyword);
    }

}
