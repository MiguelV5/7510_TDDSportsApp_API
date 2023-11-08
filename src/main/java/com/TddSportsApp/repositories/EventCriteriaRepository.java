package com.TddSportsApp.repositories;

import com.TddSportsApp.models.EventSearchCriteria;
import com.TddSportsApp.models.Event;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EventCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Event> findAllWithFilters(EventSearchCriteria eventSearchCriteria){
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Predicate predicate = getPredicate(eventSearchCriteria, eventRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Event> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(EventSearchCriteria eventSearchCriteria, Root<Event> eventRoot){
        List<Predicate> predicates = new ArrayList<>();

        if (eventSearchCriteria.getName() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("name"), "%" + eventSearchCriteria.getName() + "%")
            );
        }
        if (eventSearchCriteria.getLocation() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("location"), "%" + eventSearchCriteria.getLocation() + "%")
            );
        }
        if (eventSearchCriteria.getCategory() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("category"), "%" + eventSearchCriteria.getCategory() + "%")
            );
        }
        if (eventSearchCriteria.getEdition() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("edition"), "%" + eventSearchCriteria.getEdition() + "%")
            );
        }
        if (eventSearchCriteria.getDistance() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("distance"), "%" + eventSearchCriteria.getDistance() + "%")
            );
        }
        if (eventSearchCriteria.getStartDate() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("startDate"), "%" + eventSearchCriteria.getStartDate() + "%")
            );
        }
        if (eventSearchCriteria.getEndDate() != null){
            predicates.add(
                    criteriaBuilder.like(eventRoot.get("endDate"), "%" + eventSearchCriteria.getEndDate() + "%")
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
