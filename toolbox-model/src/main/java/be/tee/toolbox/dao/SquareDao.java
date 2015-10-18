package be.tee.toolbox.dao;

import be.tee.toolbox.model.Square;

public interface SquareDao extends GenericDao<Square> {

    Square findSquareByName(String squareName);

}
