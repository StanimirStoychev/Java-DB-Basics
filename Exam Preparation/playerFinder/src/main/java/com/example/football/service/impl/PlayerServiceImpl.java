package com.example.football.service.impl;

import com.example.football.models.dto.player.PlayerImportDTO;
import com.example.football.models.dto.player.PlayersWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import com.example.football.util.validator.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.PLAYERS_PATH;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtils validator;
    private final Unmarshaller unmarshaller;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository,
                             TownRepository townRepository, StatRepository statRepository,
                             ModelMapper modelMapper, ValidationUtils validator) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(PlayersWrapperDTO.class);
        unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(PLAYERS_PATH);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        StringBuilder sb = new StringBuilder();

        PlayersWrapperDTO playersWrapperDTO = (PlayersWrapperDTO)
                unmarshaller.unmarshal(new FileReader(PLAYERS_PATH.toFile()));

        List<PlayerImportDTO> importDTOS = playersWrapperDTO.getPlayers();

        for (PlayerImportDTO importDTO : importDTOS) {
            boolean isValid = this.validator.isValid(importDTO);

            if (isValid) {

                Player player = this.modelMapper.map(importDTO, Player.class);

                if (this.playerRepository.findByEmail(player.getEmail()).isEmpty()) {

                    Optional<Town> town = this.townRepository.findByName(player.getTown().getName());
                    Optional<Team> team = this.teamRepository.findByName(player.getTeam().getName());
                    Optional<Stat> stat = this.statRepository.findById(player.getStat().getId());

                    player.setTown(town.get());
                    player.setTeam(team.get());
                    player.setStat(stat.get());

                    this.playerRepository.saveAndFlush(player);
                    sb.append(SUCCESSFUL + PLAYER + " ").append(player.getFirstName()).append(" ")
                            .append(player.getLastName()).append(" - ").append(player.getPosition());

                } else {
                    sb.append(INVALID + PLAYER).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + PLAYER).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String exportBestPlayers() {
        return null;
    }
}
