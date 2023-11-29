package com.TddSportsApp.service;

import com.TddSportsApp.exceptions.ForbiddenActionException;
import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.models.dto.CreateResultDto;
import com.TddSportsApp.repositories.ResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTests {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ResultService resultService;

    @Test
    public void whenCreateResult_thenReturnResult(){
        // given
        CreateResultDto createResultDto = new CreateResultDto();
        createResultDto.setEventId(1L);
        createResultDto.setUserId(1L);
        createResultDto.setOfficial(true);
        createResultDto.setTime(1L);
        createResultDto.setPosition(1);
        createResultDto.setAcceptedByAthlete(true);

        // when
        when(eventService.getEventById(any(Long.class))).thenReturn(new Event());
        when(userService.getUserById(any(Long.class))).thenReturn(new UserEntity());
        when(resultRepository.save(any(Result.class))).thenReturn(new Result());
        Result result = resultService.createResult(createResultDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Result.class);
        assertThat(result.getEvent()).isInstanceOf(Event.class);
        assertThat(result.getUser()).isInstanceOf(UserEntity.class);
        assertThat(result.getOfficial()).isEqualTo(createResultDto.getOfficial());
    }

    @Test
    public void whenGetResultById_thenReturnResult(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        Result resultById = resultService.getResultById(1L);

        // then
        assertThat(resultById).isNotNull();
        assertThat(resultById).isInstanceOf(Result.class);
        assertThat(resultById.getOfficial()).isEqualTo(result.getOfficial());
    }

    @Test
    public void whenDeleteResult_thenResultShouldBeDeleted(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        resultService.deleteResult(1L);
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        // then
        assertThat(resultRepository.findById(1L)).isEmpty();
    }

    @Test
    public void whenUpdateResult_thenReturnUpdatedResult(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        Result updatedResult = new Result();
        updatedResult.setId(1L);
        updatedResult.setOfficial(false);
        updatedResult.setTime(2L);
        updatedResult.setPosition(2);
        updatedResult.setAcceptedByAthlete(false);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        when(resultRepository.save(any(Result.class))).thenReturn(updatedResult);
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        Result resultById = resultService.updateResult(1L, updatedResult);

        // then
        assertThat(resultById).isNotNull();
        assertThat(resultById).isInstanceOf(Result.class);
        assertThat(resultById.getOfficial()).isEqualTo(updatedResult.getOfficial());
    }

    @Test
public void whenChangeAthleteResult_thenReturnUpdatedResult(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        when(resultRepository.save(any(Result.class))).thenReturn(result);
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        Result resultById = resultService.changeAthleteResult(1L, false);

        // then
        assertThat(resultById).isNotNull();
        assertThat(resultById).isInstanceOf(Result.class);
        assertThat(resultById.getAcceptedByAthlete()).isEqualTo(false);
    }

    @Test
    public void whenChangeOfficialResult_thenReturnUpdatedResult(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        when(resultRepository.save(any(Result.class))).thenReturn(result);
        when(userService.isLoggedUserAdmin()).thenReturn(true);
        Result resultById = resultService.changeOfficialResult(1L, false);

        // then
        assertThat(resultById).isNotNull();
        assertThat(resultById).isInstanceOf(Result.class);
        assertThat(resultById.getOfficial()).isEqualTo(false);
    }

    @Test
    public void whenValidateResultOwner_thenReturnException(){
        // given
        Result result = new Result();
        result.setId(1L);
        result.setOfficial(true);
        result.setTime(1L);
        result.setPosition(1);
        result.setAcceptedByAthlete(true);

        // when
        when(resultRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(result));
        when(userService.isLoggedUserAdmin()).thenReturn(false);
        when(userService.isLoggedUserOwnerOfResult(any(Long.class))).thenReturn(false);

        // then
        try {
            resultService.validateResultOwner(1L);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(ForbiddenActionException.class);
        }
        assertThat(resultRepository.findById(1L)).isNotEmpty();
    }

}
