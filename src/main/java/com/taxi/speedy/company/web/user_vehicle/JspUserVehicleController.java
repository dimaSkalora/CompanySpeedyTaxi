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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

/*
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
*/

    @InitBinder
    private void initBinder(WebDataBinder binder) {

        PropertyEditor editor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (!text.trim().isEmpty())
                    super.setValue(LocalDateTime.parse(text.trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            }
            @Override
            public String getAsText() {
                if (super.getValue() == null)
                    return null;
                LocalDateTime value = (LocalDateTime) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };
        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Vehicle.class, new VehiclePropertyEditor());
    }


}
