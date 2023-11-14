package com.TddSportsApp.service;

import com.TddSportsApp.models.Event;
import com.TddSportsApp.models.Inscription;
import com.TddSportsApp.models.InscriptionKey;
import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.InscriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Transactional
    public Inscription createInscription(Event event, UserEntity user){
        Inscription inscription = Inscription.builder()
                .id(new InscriptionKey(event.getId(), user.getId()))
                .event(event)
                .user(user)
                .inscriptionDate(new Date())
                .build();

        inscriptionRepository.save(inscription);
        return inscription;
    }
}
