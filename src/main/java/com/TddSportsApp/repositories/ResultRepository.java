package com.TddSportsApp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.TddSportsApp.models.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {
}
