package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> findDistilleriesWith12YearOldWhisky(int ageToFind) {
        List<Distillery> result = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria criteria = session.createCriteria(Distillery.class);
            criteria.createAlias("whiskies", "whiskyAlias");
            criteria.add(Restrictions.eq("whiskyAlias.age", ageToFind));
            result = criteria.list();
        } catch (HibernateException exception) {
            exception.printStackTrace();
        }
        return result;
    }

}
