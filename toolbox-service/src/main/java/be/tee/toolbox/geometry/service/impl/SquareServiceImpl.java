package be.tee.toolbox.geometry.service.impl;

import javax.transaction.Transactional;

import be.tee.toolbox.dao.SquareDao;
import be.tee.toolbox.geometry.service.SquareService;
import be.tee.toolbox.model.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "squareService")
public class SquareServiceImpl implements SquareService {

    @Autowired
    private SquareDao squareDao;

    @Transactional
    @Override
    public Square getSquare(Integer squareId) {
        return squareDao.findById(squareId);
    }

    @Transactional
    @Override
    public String getSquareName(Integer squareId) {
        Square square = getSquare(squareId);
        if(square == null) {
            return null;
        }

        return square.getName();
    }

    @Transactional
    @Override
    public boolean squareExist(String name) {
        return squareDao.findSquareByName(name) != null;
    }

    @Transactional
    @Override
    public Integer createSquare(Integer size, String name) {
        Square square = new Square();
        square.setName(name);
        square.setSize(size);
        squareDao.save(square);
        return square.getId();
    }
}


