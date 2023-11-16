package com.TddSportsApp.repositories;

import com.TddSportsApp.models.Inscription;
import com.TddSportsApp.models.InscriptionKey;
import org.springframework.data.repository.CrudRepository;

public interface InscriptionRepository extends CrudRepository<Inscription, InscriptionKey> {
}
