package com.TddSportsApp.service;

import com.TddSportsApp.models.dto.CreateResultDto;
import com.TddSportsApp.exceptions.ResultNotFoundException;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public Result createResult(CreateResultDto resultDto){
        Event event = eventService.getEventById(resultDto.getEventId());
        UserEntity user = userService.getUserById(resultDto.getUserId());
//        UserEntity user = userService.getLoggedUser();

        System.out.println("Result DTO: " + resultDto);

        Result result = Result.builder()
                .official(resultDto.getOfficial())
                .time(resultDto.getTime())
                .position(resultDto.getPosition())
                .acceptedByAthlete(resultDto.getAcceptedByAthlete())
                .event(event)
                .user(user)
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

    public void deleteResult(Long id){
        resultRepository.deleteById(id);
    }

    public Result updateResult(Long id, Result updatedResult){
        Result result = this.getResultById(id);

        result.setOfficial(updatedResult.getOfficial());
        result.setTime(updatedResult.getTime());
        result.setPosition(updatedResult.getPosition());
        result.setAcceptedByAthlete(updatedResult.getAcceptedByAthlete());

        return resultRepository.save(result);
    }

    public Result acceptResult(Long id) {
        Result result = this.getResultById(id);
        result.setAcceptedByAthlete(true);
        return resultRepository.save(result);
    }

    public Result rejectResult(Long id) {
        Result result = this.getResultById(id);
        result.setAcceptedByAthlete(false);
        return resultRepository.save(result);
    }

    public Result setOfficialResult(Long id) {
        Result result = this.getResultById(id);
        result.setOfficial(true);
        return resultRepository.save(result);
    }

    public Result rejectOfficialResult(Long id) {
        Result result = this.getResultById(id);
        result.setOfficial(false);
        return resultRepository.save(result);
    }
}
