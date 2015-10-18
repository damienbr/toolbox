package be.tee.toolbox.controller;

import be.tee.toolbox.controller.toolbox.geometry.service.SquareService;
import org.assertj.core.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SquareControllerTest {

    private static String EXPECTED_MESSAGE = "<br><div style='text-align:center;'><h3>Hello World, Spring MVC Tutorial</h3>Hello takeeateasy!</div><br><br>";

    @InjectMocks
    private SquareController controller = new SquareController();

    @Mock
    private SquareService squareService;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHelloWorld() throws Exception {
        ModelAndView modelAndView = controller.helloWorld();
        ModelAndViewAssert.assertModelAttributeValue(modelAndView, "message", EXPECTED_MESSAGE);
    }

    @Test
    public void testGetSquareName() throws Exception {
        Mockito.when(squareService.getSquareName(1)).thenReturn("myName");
        Mockito.when(squareService.getSquareName(2)).thenReturn("myName2");
        ResponseEntity<String> squareName = controller.getSquareName(1);
        String actual = squareName.getBody();
        Assertions.assertThat(actual).isEqualTo("myName");

        squareName = controller.getSquareName(null);
        actual = squareName.getBody();
        Assertions.assertThat(actual).isNull();

        squareName = controller.getSquareName(2);
        actual = squareName.getBody();
        Assertions.assertThat(actual).isEqualTo("myName2");
    }
}