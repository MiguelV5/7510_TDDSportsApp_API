package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateResultDto;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result createResult(CreateResultDto resultDto){
        Result result = Result.builder()
                .official(resultDto.getOfficial())
                .time(resultDto.getTime())
                .position(resultDto.getPosition())
                .acceptedByAthlete(resultDto.getAcceptedByAthlete())
                .build();

        resultRepository.save(result);
        return result;
    }
}
