package be.tee.toolbox.dao.impl;

import be.tee.toolbox.dao.SquareDao;
import be.tee.toolbox.model.Square;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository(value = "squareDao")
public class SquareDaoImpl extends GenericDaoImpl<Square> implements SquareDao {


    @Override
    public Square findSquareByName(String squareName) {
        Criteria criteria = getCriteria().add(Restrictions.eq("name", squareName));
        return getUniqueResult(criteria);
    }

}
