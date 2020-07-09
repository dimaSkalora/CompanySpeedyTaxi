package com.taxi.speedy.company.web;

import com.taxi.speedy.company.web.user.AbstractUserController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController extends AbstractUserController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
