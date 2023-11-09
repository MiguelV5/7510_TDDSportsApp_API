package com.TddSportsApp.service;

import com.TddSportsApp.controller.dto.CreateResultDto;
import com.TddSportsApp.exceptions.ResultNotFoundException;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Result getResultById(Long id){
        Optional<Result> result = resultRepository.findById(id);
        if (result.isEmpty()){
            throw new ResultNotFoundException("Result not found with id: " + id);
        }
        return result.get();
    }

    public List<Result> findAll(){
        return (List<Result>) resultRepository.findAll();
    }

    public void deleteResult(Long id){
        resultRepository.deleteById(id);
    }

    public Result updateResult(Long id, Result updatedResult){
        Optional<Result> result = resultRepository.findById(id);

        if (result.isEmpty()){
            throw new ResultNotFoundException("Result not found with id: " + id);
        }

        updatedResult.setId(id);
        return resultRepository.save(updatedResult);
    }
}
