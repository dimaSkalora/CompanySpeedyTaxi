package com.taxi.speedy.company.web.taxi_dispatcher;

import com.taxi.speedy.company.model.TaxiDispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("taxiDispatchers")
public class JspTaxiDispatcherController extends AbstractTaxiDispatcherController {

    @RequestMapping
    public String taxiDispatchers(Model model){
        model.addAttribute("taxiDispatchers",super.getAll());

        return "taxiDispatchers";
    }

    @GetMapping("/taxiDispatcher")
    public ModelAndView taxiDispatcher(){
        ModelAndView modelAndView = new ModelAndView("taxiDispatcher");
        modelAndView.addObject("taxiDispatcher", new TaxiDispatcher());
        modelAndView.addObject("allUsers",super.getAllUser());

        return modelAndView;
    }

    @PostMapping("createOrUpdate")
    public String createOrUpdate(@ModelAttribute TaxiDispatcher taxiDispatcher){
        if (taxiDispatcher.isNew())
            super.create(taxiDispatcher);
        else
            super.update(taxiDispatcher);

        return "redirect:/taxiDispatchers";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id){
        TaxiDispatcher taxiDispatcher = super.get(id);
        ModelAndView modelAndView = new ModelAndView("taxiDispatcher");
        modelAndView.addObject("taxiDispatcher",taxiDispatcher);
        modelAndView.addObject("allUsers",super.getAllUser());

        return modelAndView;
    }

    @RequestMapping("/delete")
    public String deleteTD(@RequestParam int id){
        //delete?id=
        super.delete(id);
        return "redirect:/taxiDispatchers";
    }

    @GetMapping("/get/{id}")
    public String getTD(@PathVariable int id, Model model){
        model.addAttribute("taxiDispatcher", super.get(id));

        return "taxiDispatcher";
    }

    @RequestMapping(value = "/getTaxiDispatcherData/{id}", method = RequestMethod.GET)
    public ModelAndView getTaxiDispatcherData(@PathVariable int id){
        TaxiDispatcher taxiDispatcher = super.get(id);
        ModelAndView modelAndView = new ModelAndView("taxiDispatcherData");
        modelAndView.addObject("taxiDispatcherData",taxiDispatcher);
        modelAndView.addObject("tdUser",super.getUser(taxiDispatcher.getIdUser().getId()));

        return modelAndView;
    }


}
