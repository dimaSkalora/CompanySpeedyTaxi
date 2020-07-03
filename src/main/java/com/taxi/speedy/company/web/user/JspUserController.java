package com.taxi.speedy.company.web.user;

import com.taxi.speedy.company.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("users")
public class JspUserController extends AbstractUserController {

    @GetMapping
    public String users(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("users", super.getAll());
        return "users";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "phone") String phone,
                         @RequestParam(value = "address") String address){
        User userNew = new User();
        userNew.setName(name);
        userNew.setEmail(email);
        userNew.setPassword(password);
        userNew.setPhone(phone);
        userNew.setAddress(address);
        userNew.setRegistered(new Date());
        userNew.setEnabled(true);

        super.create(userNew);

        return "redirect:/users";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("user")User user){
        super.update(user, user.getId());
        return "redirect:/users";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute("user")User user){
        if(user.isNew())
            super.create(user);
        else
            super.update(user,user.getId());
        return "redirect:/users";
    }

    @RequestMapping(value = "/get{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable int id){
        User userGet = super.get(id);
        return new ModelAndView("user", "user", userGet);
    }

}
