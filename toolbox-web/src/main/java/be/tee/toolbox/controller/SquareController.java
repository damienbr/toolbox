package be.tee.toolbox.controller;

import be.tee.toolbox.geometry.service.SquareService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SquareController {

    public static final String HELLO_WORLD_MESSAGE = "<br><div style='text-align:center;'>"
            + "<h3>Hello World, Spring MVC Tutorial</h3>Hello takeeateasy!</div><br><br>";

    @Autowired
    private SquareService squareService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        String message = HELLO_WORLD_MESSAGE;
        return new ModelAndView("welcome", "message", message);
    }

    @RequestMapping(value = "/square/{squareId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> getSquareName(@PathVariable Integer squareId) {
        String squareName = squareService.getSquareName(squareId);
        if (StringUtils.isEmpty(squareName)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(squareName, HttpStatus.OK);
        }
    }

    @RequestMapping(value ="/createsquare")
    public @ResponseBody ResponseEntity<Integer> createSquare(@RequestParam Integer squareSize, @RequestParam String squareName) {
        Integer squareId = squareService.createSquare(squareSize, squareName);
        return new ResponseEntity<>(squareId, HttpStatus.OK);
    }
}
