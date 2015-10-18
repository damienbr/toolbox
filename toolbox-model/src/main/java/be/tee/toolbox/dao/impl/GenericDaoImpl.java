package be.tee.toolbox.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import be.tee.toolbox.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private final Class<T> persistentClass;

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl() {
        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T object) {
        getCurrentSession().save(object);
    }

    @Override
    public void update(T object) {
        getCurrentSession().update(object);
    }

    @Override
    public void save(List<T> objects) {
        if (objects == null) return;
        for (T object : objects) {
            save(object);
        }
    }

    @Override
    public List<T> findAll() {
        List result = getCriteria().list();
        return result == null ? Collections.emptyList() : result;
    }

    @Override
    public Criteria getCriteria() {
        return getCurrentSession().createCriteria(persistentClass);
    }

    @Override
    public Long getCount(Criteria criteria) {
        Number result = (Number) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return result.longValue();
    }

    @Override
    public List<T> findAll(int first, int max, String sortingColumn, boolean ascending) {
        Criteria criteria = getDefaultSorting(getCriteria(), ascending);
        if (sortingColumn != null) {
            criteria = getSorting(criteria, sortingColumn, ascending);
        }

        return criteria.setMaxResults(max).setFirstResult(first).list();
    }


    @Override
    public Criteria getDefaultSorting(Criteria criteria, boolean ascending) {
        if (ascending) {
            return criteria.addOrder(Order.asc("id"));
        } else {
            return criteria.addOrder(Order.desc("id"));
        }

    }

    @Override
    public Criteria getSorting(Criteria criteria, String propertyName, boolean ascending) {
        if (ascending) {
            return criteria.addOrder(Order.asc(propertyName));
        } else {
            return criteria.addOrder(Order.desc(propertyName));
        }

    }

    @Override
    public long getTotalCount() {
        return getCount(getCriteria());
    }

    @Override
    public T findById(Object id) {
        return (T) getCriteria().add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public void remove(Long id) {
        getCurrentSession().delete(id);
    }

    protected T getUniqueResult(Criteria criteria) {
        return (T) criteria.uniqueResult();
    }
}
