package com.taxi.speedy.company.web.taxi_order_acceptance;

import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("taxiOrderAcceptances")
public class JspTaxiOrderAcceptanceController extends AbstractTaxiOrderAcceptanceController{

    @RequestMapping
    public String taxiOrderAcceptances(Model model){
        model.addAttribute("taxiOrderAcceptances",super.getAll());
        return "taxiOrderAcceptances";
    }

    @RequestMapping(value = "/taxiOrderAcceptance",method = RequestMethod.GET)
    public ModelAndView taxiOrderAcceptance(){
        ModelAndView modelAndView = new ModelAndView("taxiOrderAcceptance");
        modelAndView.addObject("taxiOrderAcceptance", new TaxiOrderAcceptance());
        modelAndView.addObject("toaAllUserVehicles", super.getAllUserVehicles());
        modelAndView.addObject("toaAllTaxiDispatcherOrders", super.getAllTaxiDispatcherOrders());
        modelAndView.addObject("toaAllTaxiUserOrders", super.getAllTaxiUserOrders());

        return modelAndView;
    }

    @GetMapping("/filter")
    public String filterTaxiOrderAcceptance(Model model){
        model.addAttribute("taxiOrderAcceptance", new TaxiOrderAcceptance());
        model.addAttribute("toaAllUserVehicles", super.getAllUserVehicles());
        model.addAttribute("toaAllTaxiDispatcherOrders", super.getAllTaxiDispatcherOrders());
        model.addAttribute("toaAllTaxiUserOrders", super.getAllTaxiUserOrders());

        return "taxiOrderAcceptances";
    }

}
