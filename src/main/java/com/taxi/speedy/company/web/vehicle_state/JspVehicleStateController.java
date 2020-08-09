package com.taxi.speedy.company.web.vehicle_state;

import com.taxi.speedy.company.model.VehicleState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("vehicleStates")
public class JspVehicleStateController extends AbstractVehicleStateController{
    @RequestMapping
    public ModelAndView vehicleStates(){
        return new ModelAndView("vehicleStates", "vehicleStates", super.getAll());
    }

    @RequestMapping(value = "vehicleState", method = RequestMethod.GET)
    public String vehicleState(Model model){
        model.addAttribute("vehicleState", new VehicleState());

        return "vehicleState";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam(value = "nameVS", required = true) String nameVS){
        //createRequestParam?nameVS=nameVSTest

        VehicleState vehicleState = new VehicleState();
        vehicleState.setNameVS(nameVS);
        super.create(vehicleState);

        return "redirect:/vehicleStates";
    }

    @RequestMapping(value = "/createModelUserState", method = RequestMethod.POST)
    public String createModelUserState(@ModelAttribute VehicleState vehicleState){
        super.create(vehicleState);
        return "redirect:/vehicleStates";
    }

    @RequestMapping(value = "/createHSR", method = RequestMethod.POST)
    public String createHSR(HttpServletRequest httpServletRequest){
        VehicleState vehicleState = new VehicleState();
        vehicleState.setNameVS(httpServletRequest.getParameter("nameVS"));
        super.create(vehicleState);

        return "redirect:/vehicleStates";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute VehicleState vehicleState){
        if(vehicleState.isNew())
            super.create(vehicleState);
        else
            super.update(vehicleState);

        return "redirect:/vehicleStates";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id){
        VehicleState vehicleState = super.get(id);

        ModelAndView modelAndView = new ModelAndView("vehicleState");
        modelAndView.addObject("vehicleState",vehicleState);
        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteVS(@RequestParam int id){
        //delete?id=id
        super.delete(id);
        return "redirect:/vehicleStates";
    }

    @GetMapping("/searchByNameVS")
    public ModelAndView searchByNameVS(String nameVS){
        return new ModelAndView("vehicleStates", "vehicleStates",super.getByNameVS(nameVS));
    }

    @GetMapping("get/{id}")
    public String getVehicleState(@PathVariable int id, Model model){
        VehicleState vehicleState = super.get(id);
        model.addAttribute("vehicleState",vehicleState);
        return "vehicleState";
    }
}
