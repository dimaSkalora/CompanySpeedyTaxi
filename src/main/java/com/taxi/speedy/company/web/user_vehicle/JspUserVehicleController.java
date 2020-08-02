package com.taxi.speedy.company.web.user_vehicle;

import com.taxi.speedy.company.dto.UserVehicleFull;
import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.model.propertyeditor.UserPropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.VehiclePropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("userVehicles")
public class JspUserVehicleController extends AbstractUserVehicleController {

    @RequestMapping
    public String userVehicles(Model model){
        model.addAttribute("uvModal", new UserVehicle());
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

    @GetMapping("/userVehicleHSR")
    public String userVehicleHSR(Model model){
        model.addAttribute("userVehicle", new UserVehicle());
        model.addAttribute("uvAllUsers", super.getAllUsers());
        model.addAttribute("uvAllVehicles", super.getAllVehicles());

        return "userVehicleHSR";
    }

    @GetMapping("/userVehicleFull")
    public String userVehicleFull(Model model){
        model.addAttribute("userVehicleFull", new UserVehicleFull());
        model.addAttribute("uvAllUsers", super.getAllUsers());
        model.addAttribute("uvAllVehicles", super.getAllVehicles());
        super.getAllUsers().forEach(System.out::println);
        System.out.println();
        super.getAllVehicles().forEach(System.out::println);

        return "userVehicleFull";
    }


    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute UserVehicle userVehicle){
        if (userVehicle.isNew())
            super.create(userVehicle);
        else
            super.update(userVehicle);

        return "redirect:/userVehicles";
    }

    @PostMapping("/createOrUpdateHSR")
    public String createOrUpdateHSR(HttpServletRequest request){
        User user = super.getUser(Integer.parseInt(request.getParameter("idUser")));
        Vehicle vehicle = super.getVehicle(Integer.parseInt(request.getParameter("idVehicle")));

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        if (request.getParameter("startDate") != "")
            startDate = LocalDateTime.parse(request.getParameter("startDate"));
        if (!request.getParameter("endDate").equals(""))
            endDate = LocalDateTime.parse(request.getParameter("endDate"));

        UserVehicle userVehicle = new UserVehicle();
        if (startDate != null)
            userVehicle.setStartDate(startDate);
        if (endDate != null)
            userVehicle.setEndDate(endDate);
        userVehicle.setIdUser(user);
        userVehicle.setIdVehicle(vehicle);
        //По умолчанию (1 - есть машина)
        userVehicle.setIsCurrentUserMachine(1);

        super.create(userVehicle);

        return "redirect:/userVehicles";
    }

    @PostMapping("/createOrUpdateUVFull")
    public String createOrUpdateUVFull(@ModelAttribute UserVehicleFull userVehicleFull){
        if (userVehicleFull.isNew())
            super.createUVFull(userVehicleFull);
        else
            super.updateUVFull(userVehicleFull);

        return "redirect:/userVehicles";
    }

    @GetMapping("/userVehicleFull/{id}")
    public ModelAndView getUVFull(@PathVariable int id){
        UserVehicleFull userVehicleFullGet = super.getUSFull(id);
        return new ModelAndView("userVehicle", "userVehicleFull", userVehicleFullGet);
    }

    @RequestMapping(value ="/update/{id}")
    public ModelAndView update(@PathVariable int id, Model model){
        UserVehicle userVehicle = super.get(id);
        System.out.println(userVehicle.toString());
        ModelAndView modelAndView = new ModelAndView("userVehicle");
        modelAndView.addObject(userVehicle);
        model.addAttribute("uvAllUsers", super.getAllUsers());
        model.addAttribute("uvAllVehicles", super.getAllVehicles());

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteUserVehicle(@RequestParam int id){
        super.delete(id);
        return "redirect:/userVehicles";
    }

    @GetMapping(value = "/get/{id}")
    public ModelAndView getUserVehicle(@PathVariable int id){
        UserVehicle userVehicle = super.get(id);
        System.out.println(userVehicle.toString());

        return new ModelAndView("userVehicle", "userVehicle", userVehicle);
    }

    @GetMapping("/getByStartDate/{startDate}/{endDate}")
    public ModelAndView getByStartDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<UserVehicle> userVehicles = super.getStartDateBetween(startDate,endDate);

        return new ModelAndView("userVehicles", "userVehicles",userVehicles);
    }

    @GetMapping("/getByEndDate/{startDate}/{endDate}")
    public ModelAndView getByEndDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<UserVehicle> userVehicles = super.getEndDateBetween(startDate,endDate);

        return new ModelAndView("userVehicles", "userVehicles",userVehicles);
    }

    @GetMapping("/getByUser/{id}")
    public ModelAndView getByUser(@PathVariable int id){
        List<UserVehicle> userVehicles = super.getAllByUser(id);

        return new ModelAndView("userVehicles","userVehicles",userVehicles);
    }

    @GetMapping("/getByVehicle")
    public ModelAndView getByVehicle(@RequestParam int id){
        List<UserVehicle> userVehicles = super.getAllByVehicle(id);

        return new ModelAndView("userVehicles","userVehicles", userVehicles);
    }

    @GetMapping(value = "/getUserVehicleData/{id}")
    public ModelAndView getUserVehicleData(@PathVariable int id){
        UserVehicle userVehicle = super.get(id);

        User user = super.getUser(userVehicle.getIdUser().getId());
        Vehicle vehicle = super.getVehicle(userVehicle.getIdVehicle().getId());
        ModelAndView modelAndView = new ModelAndView("userVehicleData");
        modelAndView.addObject("uvData", userVehicle);
        modelAndView.addObject("uvUser", user);
        modelAndView.addObject("uvVehicle", vehicle);

        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
/*    @InitBinder
    private void initBinder(WebDataBinder binder) {

        PropertyEditor editor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!text.trim().isEmpty())
                    super.setValue(LocalDateTime.parse(text.trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
            @Override
            public String getAsTex  t() {
                if (super.getValue() == null)
                    return null;
                LocalDateTime value = (LocalDateTime) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };
        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Vehicle.class, new VehiclePropertyEditor());
    }*/


}
