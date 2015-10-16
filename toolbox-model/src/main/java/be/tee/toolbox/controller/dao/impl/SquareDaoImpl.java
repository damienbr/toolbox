package be.tee.toolbox.controller.dao.impl;

import be.tee.toolbox.controller.dao.SquareDao;
import be.tee.toolbox.controller.model.Square;
import org.springframework.stereotype.Repository;

@Repository(value = "squareDao")
public class SquareDaoImpl implements SquareDao {

    @Override
    public Square findSquareById(Integer squareId) {
        Square square = new Square();
        square.setId(squareId);
        square.setName("myName" + squareId);
        return square;
    }

    @Override
    public Square findSquareByName(String squareName) {
        Square square = new Square();
        square.setId(1);
        square.setName(squareName);
        return square;
    }

    @Override
    public void persist(Square square) {

    }
}
