package be.tee.toolbox.controller;

import be.tee.toolbox.geometry.service.SquareService;
import org.apache.commons.lang.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// http://blog.trifork.com/2012/12/11/properly-testing-spring-mvc-controllers/
public class SquareControllerTest {

    private static String EXPECTED_MESSAGE = "<br><div style='text-align:center;'><h3>Hello World, Spring MVC Tutorial</h3>Hello takeeateasy!</div><br><br>";

    @InjectMocks
    private SquareController squareController;

    @Mock
    private SquareService squareService;

    @Mock
    private View mockView;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(squareController).setSingleView(mockView).build();
    }

    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/welcome"))
                .andExpect(MockMvcResultMatchers.view().name("welcome"))
                .andExpect(MockMvcResultMatchers.model().attribute("message", EXPECTED_MESSAGE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetSquareName() throws Exception {
        Mockito.when(squareService.getSquareName(1)).thenReturn("myName1");
        mockMvc.perform(MockMvcRequestBuilders.get("/square/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("myName1"));

        mockMvc.perform(MockMvcRequestBuilders.get("/square/2"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(StringUtils.EMPTY));

        mockMvc.perform(MockMvcRequestBuilders.get("/square/"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/square/blabla"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(StringUtils.EMPTY));

        Mockito.verify(squareService).getSquareName(1);
        Mockito.verify(squareService).getSquareName(2);
        Mockito.verify(squareService, Mockito.times(2)).getSquareName(Mockito.anyInt());
    }
}