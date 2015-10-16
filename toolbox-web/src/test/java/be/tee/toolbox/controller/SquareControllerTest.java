package be.tee.toolbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@WebAppConfiguration
@ContextConfiguration("classpath:/application-test-context.xml")
public class SquareControllerTest extends AbstractTestNGSpringContextTests {

    private static String EXPECTED_MESSAGE = "<br><div style='text-align:center;'><h3>Hello World, Spring MVC Tutorial</h3>Hello takeeateasy!</div><br><br>";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testHelloWorld() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/square"))
                .andExpect(MockMvcResultMatchers.view().name("welcome"))
                .andExpect(MockMvcResultMatchers.model().attribute("message", EXPECTED_MESSAGE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetSquareName() throws Exception {

    }
}