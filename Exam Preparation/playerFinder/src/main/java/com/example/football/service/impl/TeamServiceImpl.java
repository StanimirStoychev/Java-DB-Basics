package com.example.football.service.impl;

import com.example.football.models.dto.team.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.example.football.util.validator.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.TEAMS_PATH;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TownRepository townRepository;

    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtils validator;

    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validator) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(TEAMS_PATH);
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder sb = new StringBuilder();

        TeamImportDTO[] teamImportDTOS = gson.fromJson(readTeamsFileContent(), TeamImportDTO[].class);

        for (TeamImportDTO teamImportDTO : teamImportDTOS) {
            boolean isValid = validator.isValid(teamImportDTO);

            if (isValid) {
                Team team = this.modelMapper.map(teamImportDTO, Team.class);

                if (this.teamRepository.findByName(team.getName()).isEmpty()) {
                    Optional<Town> town = this.townRepository.findByName(teamImportDTO.getTownName());
                    team.setTown(town.get());
                    this.teamRepository.saveAndFlush(team);
                    sb.append(SUCCESSFUL + TEAM + " " + team.getName() + " - " + team.getFanBase())
                            .append(System.lineSeparator());
                } else {
                    sb.append(INVALID + TEAM).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + TEAM).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
