package be.tee.toolbox.controller.toolbox.geometry.service.impl;

import be.tee.toolbox.controller.model.Square;
import be.tee.toolbox.controller.toolbox.geometry.service.SquareArithmeticsService;
import be.tee.toolbox.controller.toolbox.geometry.service.SquareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "squareArithmeticsService")
public class SquareArithmeticsServiceImpl implements SquareArithmeticsService {

    @Autowired
    private SquareService squareService;

    @Override
    public Integer evaluateExistingSquarePerimetter(Integer squareId) {
        Square square = squareService.getSquare(squareId);
        return evaluatePerimetter(square.getSize());
    }

    @Override
    public Integer evaluatePerimetter(Integer squareSize) {
        return (squareSize * 4);
    }
}
