package be.tee.toolbox.controller.dao;

import be.tee.toolbox.controller.model.Square;

public interface SquareDao {

    Square findSquareById(Integer squareId);

    Square findSquareByName(String squareName);

    void persist(Square square);
}
