package be.tee.toolbox.controller.toolbox.geometry.service;

import be.tee.toolbox.controller.model.Square;

public interface SquareService {

    Square getSquare(Integer squareId);

    String getSquareName(Integer squareId);

    boolean squareExist(String name);

    Integer createSquare(Integer size, String name);
}
