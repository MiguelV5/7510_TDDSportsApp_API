package com.TddSportsApp.controller;

import com.TddSportsApp.exceptions.EventNotFoundException;
import com.TddSportsApp.exceptions.ForbiddenActionException;
import com.TddSportsApp.exceptions.ResultNotFoundException;
import com.TddSportsApp.exceptions.UserNotFoundException;
import com.TddSportsApp.models.dto.CreateResultDto;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    @PostMapping("")
    public ResponseEntity<Result> createResult(@Valid @RequestBody CreateResultDto resultDto){
        try {
            return ResponseEntity.ok(resultService.createResult(resultDto));
        } catch (EventNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(resultService.getResultById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResult(@PathVariable Long id){
        try {
            resultService.deleteResult(id);
            return ResponseEntity.ok().build();
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @Valid @RequestBody Result updatedResult){
        try {
            return ResponseEntity.ok(resultService.updateResult(id, updatedResult));
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Result> acceptResult(@PathVariable Long id){
        try {
            return ResponseEntity.ok(resultService.changeAthleteResult(id, true));
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Result> rejectResult(@PathVariable Long id){
        try {
            return ResponseEntity.ok(resultService.changeAthleteResult(id, false));
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}/official")
    public ResponseEntity<Result> setOfficialResult(@PathVariable Long id){
        try {
            return ResponseEntity.ok(resultService.changeOfficialResult(id, true));
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PutMapping("/{id}/reject-official")
    public ResponseEntity<Result> rejectOfficialResult(@PathVariable Long id){
        try {
            return ResponseEntity.ok(resultService.changeOfficialResult(id, false));
        } catch (ResultNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ForbiddenActionException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
