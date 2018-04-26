package com.scaffold.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {

    /* url: /home */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        String message = "This is message from controller";

        // Get view file from path webapp/WEB-INF/views/home.jsp
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("message", message);
        return mv;
    }

    /* url: /home/info */
    @RequestMapping(value = "/info", produces = "application/json")
    @ResponseBody
    public Map<String, String> getDbInfo() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "Sanish Maharjan");
        data.put("Address", "Panga, Kirtipur");
        data.put("Contact", "9841115968");
        return data;
    }
}
