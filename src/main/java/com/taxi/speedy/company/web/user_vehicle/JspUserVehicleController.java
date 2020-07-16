package com.taxi.speedy.company.web.user_vehicle;

import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("userVehicles")
public class JspUserVehicleController extends AbstractUserVehicleController {

    @RequestMapping
    public String userVehicles(Model model){
        model.addAttribute("userVehicles",super.getAll());
        return "userVehicles";
    }

    @GetMapping("/userVehicle")
    public String userVehicle(Model model){
        model.addAttribute("userVehicle", new UserVehicle());
        model.addAttribute("uvAllUsers", super.getAllUsers());
        model.addAttribute("uvAllVehicles", super.getAllVehicles());
        return "userVehicle";
    }

    @PostMapping("/createModelUserVehicle")
    public String createModelUserVehicle(@ModelAttribute UserVehicle userVehicle){



        return "redirect:userVehicles";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute UserVehicle userVehicle){
        if (userVehicle.isNew())
            super.create(userVehicle);
        else
            super.update(userVehicle);

        return "redirect:userVehicles";
    }

}
