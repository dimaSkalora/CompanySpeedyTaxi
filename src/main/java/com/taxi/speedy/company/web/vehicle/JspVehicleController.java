package com.taxi.speedy.company.web.vehicle;

import com.taxi.speedy.company.model.Vehicle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("vehicles")
public class JspVehicleController extends AbstractVehicleController{
    @RequestMapping
    public String vehicles(Model model){
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("vehicles",super.getAll());
        return "vehicles";
    }

    @RequestMapping(value = "/createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam(value = "nameCar", required = true) String nameCar,
                                     @RequestParam(value = "vehicleNumber", required = true)String vehicleNumber,
                                     @RequestParam(value = "yearIssue")int yearIssue,
                                     @RequestParam(value = "category") String category,
                                     @RequestParam(value = "color") String color,
                                     @RequestParam(value = "fuelConsumption") int fuelConsumption){
        //createRequestParam?nameCar=nameCarTest?vehicleNumber=vehicleNumberTest?yearIssue=yearIssueTest?category=categoryTest?color=colorTest?fuelConsumption=Test

        Vehicle vehicle = new Vehicle();
        vehicle.setNameCar(nameCar);
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setYearIssue(yearIssue);
        vehicle.setCategory(category);
        vehicle.setColor(color);
        vehicle.setFuelConsumption(fuelConsumption);

        super.create(vehicle);

        return "redirect:vehicles";
    }

    @RequestMapping(value = "/createModelVehicle", method = RequestMethod.POST)
    public String createModelVehicle(@ModelAttribute Vehicle vehicle){
        super.create(vehicle);
        return "redirect:vehicles";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable int id){
        Vehicle vehicle = super.get(id);
        ModelAndView modelAndView = new ModelAndView("vehicle");
        modelAndView.addObject(vehicle);
        return modelAndView;
    }

    @PostMapping(value = "/createOrUpdate")
    public String createOrUpdate(@ModelAttribute("vehicle") Vehicle vehicle){
        if(vehicle.isNew())
            super.create(vehicle);
        else if(!vehicle.isNew())
            super.update(vehicle);
        return "redirect:vehicles";
    }

    @GetMapping(value = "/delete")
    public String deleteVehicle(@RequestParam int id){
        super.delete(id);
        return "redirect:vehicles";
    }

    @GetMapping(value = "/get/{id}")
    public ModelAndView getVehicle(@PathVariable int id){
        Vehicle vehicle = super.get(id);
        return new ModelAndView("vehicle","vehicle",vehicle);
    }

    @GetMapping(value = "/searchByNameCar/{nameCar}")
    public ModelAndView searchByNameCar(@PathVariable String nameCar){
        List<Vehicle> vehicles = super.getByNameCar(nameCar);
        return new ModelAndView("vehicles", "vehicles", vehicles);
    }

    @GetMapping(value = "/searchByVehicleNumber/{vehicleNumber}")
    public ModelAndView searchByVehicleNumber(String vehicleNumber){
        return new ModelAndView("vehicles","vehicles", super.getByVehicleNumber(vehicleNumber));
    }
}
