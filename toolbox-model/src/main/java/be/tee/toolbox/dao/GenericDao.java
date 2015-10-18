package be.tee.toolbox.dao;

import java.util.List;

import org.hibernate.Criteria;

/**
 * User: damien
 * Date: 20/12/13
 * Time: 8:25
 */
public interface GenericDao<T> {
	
    public void save(T object);

    void update(T object);

    void save(List<T> objects);

    List<T> findAll();

    Criteria getCriteria();

    Long getCount(Criteria criteria);

    List<T> findAll(int first, int max, String sortingColumn, boolean ascending);

    Criteria getDefaultSorting(Criteria criteria, boolean ascending);

    Criteria getSorting(Criteria criteria, String propertyName, boolean ascending);

    long getTotalCount();

    T findById(Object id);

    void remove(Long id);
}
