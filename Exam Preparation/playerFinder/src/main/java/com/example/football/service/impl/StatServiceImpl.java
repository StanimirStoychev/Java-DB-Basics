package com.example.football.service.impl;

import com.example.football.models.dto.stat.StatImportDTO;
import com.example.football.models.dto.stat.StatsWrapperDTO;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.validator.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static com.example.football.constants.Messages.*;
import static com.example.football.constants.Paths.STATS_PATH;

@Service
public class StatServiceImpl implements StatService {

    private final StatRepository statRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtils validator;
    private final Unmarshaller unmarshaller;

    @Autowired
    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, ValidationUtils validator) throws JAXBException {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(StatsWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(STATS_PATH);
    }

    @Override
    public String importStats() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        StatsWrapperDTO statsWrapperDTO = (StatsWrapperDTO) unmarshaller.unmarshal(
                new FileReader(STATS_PATH.toFile()));
        List<StatImportDTO> statImportDTOS = statsWrapperDTO.getStats();

        for (StatImportDTO statImportDTO : statImportDTOS) {
            boolean isValid = this.validator.isValid(statImportDTO);

            if (isValid) {
                Stat stat = this.modelMapper.map(statImportDTO, Stat.class);

                if (this.statRepository.findByPassingAndAndShootingAndEndurance(stat.getPassing(),
                        stat.getShooting(), stat.getEndurance()).isEmpty()) {

                    this.statRepository.saveAndFlush(stat);
                    sb.append(SUCCESSFUL + STAT + " " + stat).append(System.lineSeparator());
                } else {
                    sb.append(INVALID + STAT).append(System.lineSeparator());
                }

            } else {
                sb.append(INVALID + STAT).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
