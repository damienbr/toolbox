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
import org.testng.annotations.DataProvider;
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

    @Test(dataProvider = "squareNameScenario")
    public void getSquareName(Integer squareId, String expected, Square square) {
        Mockito.when(squareDao.findById(squareId)).thenReturn(square);
        String actual = squareService.getSquareName(squareId);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @DataProvider(name = "squareNameScenario")
    private Object[][] getSquareNameDataProvider() {
        return new Object[][] {
                {1, "name1", getSquare(1)},
                {2, "name2", getSquare(2)},
                {3, null, null},
                {null, null, null},
        };
    }

    @Test
    public void squareExist() {
        Mockito.when(squareDao.findSquareByName("prout")).thenReturn(getSquare(1));
        boolean actual = squareService.squareExist("prout");
        Assertions.assertThat(actual).isTrue();
        actual = squareService.squareExist("blabla");
        Assertions.assertThat(actual).isFalse();
    }


    @Test
    public void createSquare() {
        Integer size = 1;
        String name = "blabla";
        Square square = new Square();
        square.setName(name);
        square.setSize(size);
        squareService.createSquare(size, name);
        Mockito.verify(squareDao).save(square);
    }

    private Square getSquare(Integer id) {
        Square expected = new Square();
        expected.setName("name"+id);
        expected.setSize(id * 3);
        expected.setId(id);
        return expected;
    }
}