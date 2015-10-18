package be.tee.toolbox.geometry.service.impl;

import be.tee.toolbox.dao.SquareDao;
import be.tee.toolbox.geometry.service.SquareService;
import be.tee.toolbox.model.Square;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SquareServiceImplTest {

    @Mock
    private SquareDao squareDao;

    @InjectMocks
    private SquareService squareService = new SquareServiceImpl();

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetSquare() throws Exception {
        int id = 1;
        Square expected = getSquare(id);
        Mockito.when(squareDao.findById(id)).thenReturn(expected);
        Square actual = squareService.getSquare(id);
        Assertions.assertThat(actual).isEqualTo(expected);

        Square expected2 = squareService.getSquare(2);
        Assertions.assertThat(expected2).isNull();

        Mockito.verify(squareDao).findById(id);
        Mockito.verify(squareDao).findById(2);
    }

    private Square getSquare(Integer id) {
        Square expected = new Square();
        expected.setSize(id * 3);
        expected.setId(id);
        return expected;
    }
}