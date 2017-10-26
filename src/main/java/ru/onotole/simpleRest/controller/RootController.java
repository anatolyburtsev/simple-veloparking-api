package ru.onotole.simpleRest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api("HealthChech and swaggerRedirect controller")
public class RootController {

    @GetMapping(value = "/health")
    @ApiOperation(value = "dummy controller to check alive")
    public String health() {
        return "I'm ok";
    }

    @GetMapping(value = "/")
    @ApiOperation(value = "swagger to work with api by browser")
    public ModelAndView root(HttpServletResponse httpServletResponse) {
        return new ModelAndView("redirect:" + "/swagger-ui.html");
    }
}
