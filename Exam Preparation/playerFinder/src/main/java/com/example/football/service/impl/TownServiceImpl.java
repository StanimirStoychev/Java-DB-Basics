package com.example.football.service.impl;

import com.example.football.models.dto.town.TownImportDTO;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
import com.example.football.service.TownService;
import com.example.football.util.validator.ValidationUtils;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.TOWNS_PATH;


@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtils validator;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, ValidationUtils validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWNS_PATH);
    }

    @Override
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        TownImportDTO[] townImportDTOS = gson.fromJson(readTownsFileContent(), TownImportDTO[].class);

        for (TownImportDTO townImportDTO : townImportDTOS) {
            boolean isValid = this.validator.isValid(townImportDTO);

            if (isValid) {
                Town town = modelMapper.map(townImportDTO, Town.class);

                if (townRepository.findByName(town.getName()).isEmpty()) {
                    this.townRepository.saveAndFlush(town);
                    sb.append(SUCCESSFUL + TOWN + " " + town.getName() + " - " + town.getPopulation())
                            .append(System.lineSeparator());
                } else {
                    sb.append(INVALID + TOWN).append(System.lineSeparator());
                }
            } else {
                sb.append(INVALID + TOWN).append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
