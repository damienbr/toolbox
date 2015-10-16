package be.tee.toolbox.controller.toolbox.geometry.service.impl;

import be.tee.toolbox.controller.dao.SquareDao;
import be.tee.toolbox.controller.model.Square;
import be.tee.toolbox.controller.toolbox.geometry.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "squareService")
public class SquareServiceImpl implements SquareService {

    @Autowired
    private SquareDao squareDao;

    @Override
    public Square getSquare(Integer squareId) {
        return squareDao.findSquareById(squareId);
    }

    @Override
    public String getSquareName(Integer squareId) {
        return getSquare(squareId).getName();
    }

    @Override
    public boolean squareExist(String name) {
        return squareDao.findSquareByName(name) != null;
    }

    @Override
    public Integer createSquare(Integer size, String name) {
            Square square = new Square();
            square.setName(name);
            square.setSize(size);
            squareDao.persist(square);
            return square.getId();
        }
}


