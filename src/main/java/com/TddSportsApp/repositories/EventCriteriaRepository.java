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

    public List<Event> findAllWithFilters(EventSearchCriteria eventSearchCriteria, Long currentUserId) {
        CriteriaQuery<Event> criteriaQuery = criteriaBuilder.createQuery(Event.class);
        Root<Event> eventRoot = criteriaQuery.from(Event.class);
        Predicate predicate = getPredicate(eventSearchCriteria, eventRoot, currentUserId);
        criteriaQuery.where(predicate);

        TypedQuery<Event> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(EventSearchCriteria eventSearchCriteria, Root<Event> eventRoot, Long currentUserId){
        if(eventSearchCriteria == null){
            System.out.println("eventSearchCriteria is null");
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
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
        if (eventSearchCriteria.getStartDistance() != null && eventSearchCriteria.getEndDistance() != null){
            predicates.add(
                    criteriaBuilder.between(eventRoot.get("distance"), eventSearchCriteria.getStartDistance(), eventSearchCriteria.getEndDistance())
            );
        }
        if (eventSearchCriteria.getStartDate() != null && eventSearchCriteria.getEndDate() != null){
            predicates.add(
                    criteriaBuilder.between(eventRoot.get("date"), eventSearchCriteria.getStartDate(), eventSearchCriteria.getEndDate())
            );
        } else if (eventSearchCriteria.getStartDate() != null){
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(eventRoot.get("date"), eventSearchCriteria.getStartDate())
            );
        } else if (eventSearchCriteria.getEndDate() != null){
            predicates.add(
                    criteriaBuilder.lessThanOrEqualTo(eventRoot.get("date"), eventSearchCriteria.getEndDate())
            );
        }
        if (eventSearchCriteria.getEnrolled() != null){
            // join table Events with table Inscriptions ON Events.id = Inscriptions.event_id and get all rows where Inscriptions.user_id = currentUserId
            // SELECT * FROM events JOIN inscriptions ON events.id = inscriptions.event_id WHERE inscriptions.user_id = currentUserId
            predicates.add(
                    criteriaBuilder.equal(eventRoot.join("inscriptions").get("id"), currentUserId)
            );
        }


        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
