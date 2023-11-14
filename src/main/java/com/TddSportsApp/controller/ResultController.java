package com.TddSportsApp.controller;

import com.TddSportsApp.controller.dto.CreateResultDto;
import com.TddSportsApp.models.Result;
import com.TddSportsApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    ResultService resultService;

    @PostMapping("")
    public Result createResult(@RequestBody CreateResultDto resultDto){
        return resultService.createResult(resultDto);
    }

    @GetMapping("/{id}")
    public Result getResultById(@PathVariable Long id){
        return resultService.getResultById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id){
        resultService.deleteResult(id);
    }

    @PutMapping("/{id}")
    public Result updateResult(@PathVariable Long id, @RequestBody Result updatedResult){
        return resultService.updateResult(id, updatedResult);
    }

    @PutMapping("/{id}/accept")
    public Result acceptResult(@PathVariable Long id){
        return resultService.acceptResult(id);
    }

    @PutMapping("/{id}/reject")
    public Result rejectResult(@PathVariable Long id){
        return resultService.rejectResult(id);
    }

    @PutMapping("/{id}/official")
    public Result setOfficialResult(@PathVariable Long id){
        return resultService.setOfficialResult(id);
    }

    @PutMapping("/{id}/reject-official")
    public Result rejectOfficialResult(@PathVariable Long id){
        return resultService.rejectOfficialResult(id);
    }

}
