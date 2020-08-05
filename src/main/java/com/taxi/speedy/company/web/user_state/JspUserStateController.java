package com.taxi.speedy.company.web.user_state;

import com.taxi.speedy.company.model.UserState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "userStates")
public class JspUserStateController extends AbstractUserStateController{

    @RequestMapping
    public ModelAndView userStates(){
        return new ModelAndView("userStates","userStates",super.getAll());
    }

    @GetMapping("userState")
    public String userState(Model model){
        model.addAttribute("userState", new UserState());

        return "userState";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam(value = "nameUS", required = true) String nameUS){
        //createRequestParam?nameUS=nameUSTest

        UserState userState = new UserState();
        userState.setNameUS(nameUS);
        super.create(userState);

        return "redirect:/userStates";
    }

    @RequestMapping(value = "/createModelUserState", method = RequestMethod.POST)
    public String createModelUserState(@ModelAttribute UserState userState){
        super.create(userState);
        return "redirect:/userStates";
    }

    @PostMapping("/createHSR")
    public String createHSR(HttpServletRequest request){
        UserState userState = new UserState();
        userState.setNameUS(request.getParameter("nameUS"));

        return "redirect:/userStates";
    }

    @PostMapping(value = "/createOrUpdate")
    public String createOrUpdate(@ModelAttribute UserState userState){
        if (userState.isNew())
            super.create(userState);
        else
            super.update(userState);

        return "redirect:/userStates";
    }

    @RequestMapping(value = "/update/{id}" , method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id){
        UserState userState = super.get(id);
        ModelAndView modelAndView = new ModelAndView("userState");
        modelAndView.addObject(userState);

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteUserSates(@RequestParam int id){
        //delete?id=id
        super.delete(id);
        return "redirect:/userStates";
    }

    @GetMapping("/searchByNameUS/{nameUS}")
    public ModelAndView searchByNameUS(String nameUS){
            return new ModelAndView("userStates","userStates",super.getByNameUS(nameUS));
    }

}
