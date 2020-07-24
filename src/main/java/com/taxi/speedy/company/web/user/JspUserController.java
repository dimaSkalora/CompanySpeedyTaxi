package com.taxi.speedy.company.web.user;

import com.taxi.speedy.company.model.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @GetMapping(value = "/user")
    public String user(Model model){
        model.addAttribute("user", new User());
        return "user";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam(value = "name", required = true) String name,
                         @RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "phone") String phone,
                         @RequestParam(value = "address") String address){
        //createRequestParam?name=testname?email=testemail?password=testpassword?phone=testphone?address=testaddress

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

    @RequestMapping(value = "/createModelUser", method = RequestMethod.POST)
    public String createModelUser(@ModelAttribute("user")User user){
        super.create(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable int id){
        User user = super.get(id);
        ModelAndView model = new ModelAndView("user");
        model.addObject(user);
        return model;
        //return "redirect:/users";
        //return "redirect:/user";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute("user")User user){
        if(user.isNew())
            super.create(user);
        else
            super.update(user,user.getId());
        return "redirect:/users";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam int id){
        super.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ModelAndView getUser(@PathVariable int id){
        User userGet = super.get(id);
        return new ModelAndView("user", "user", userGet);
    }

    @GetMapping("/searchByEmail/{email}")
    public ModelAndView searchByEmail(String email){
        User user = super.getByEmail(email);
        return new ModelAndView("users","users",user);
    }

    @GetMapping("/searchByPhone/{phone}")
    public ModelAndView searchByPhone(String phone){
        return new ModelAndView("users","users",super.getByPhone(phone));
    }

  /*  @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/

}
