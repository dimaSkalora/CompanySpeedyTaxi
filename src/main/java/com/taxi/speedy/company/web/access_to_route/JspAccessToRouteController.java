package com.taxi.speedy.company.web.access_to_route;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import com.taxi.speedy.company.model.propertyeditor.UserStatePropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.UserVehiclePropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.VehicleStatePropertyEditor;
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

@Controller
@RequestMapping("accessToRoutes")
public class JspAccessToRouteController extends AbstractAccessToRouteController{

    @RequestMapping
    public String accessToRoutes(Model model){
        model.addAttribute("accessToRoutes", super.getAll());

        return "accessToRoutes";
    }

    @RequestMapping(value = "/accessToRoute", method = RequestMethod.GET)
    public String accessToRoute(Model model){
        model.addAttribute("accessToRoute", new AccessToRoute());
        model.addAttribute("allUserVehicles",super.getAllUserVehicles());
        model.addAttribute("allUserStates",super.getAllUserStates());
        model.addAttribute("allVehicleStates",super.getAllVehicleStates());

        return "accessToRoute";
    }

    @RequestMapping(value = "createRequestParam", method = RequestMethod.POST)
    public String createRequestParam(@RequestParam LocalDateTime checksDateTime,
                                     @RequestParam(required = true) int idUserVehicle,
                                     @RequestParam(required = true) int idUserState,
                                     @RequestParam(required = true) int idVehicleState,
                                     @RequestParam(required = true) int isAccess){
        //createRequestParam?checksDateTime=testchecksDateTime?idUserVehicle=1?idUserState=?idVehicleState=?isAccess=

        UserVehicle userVehicle = super.getUserVehicle(idUserVehicle);
        UserState userState = super.getUserState(idUserState);
        VehicleState vehicleState = super.getVehicleState(idVehicleState);

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(checksDateTime);
        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRoute.setIdUserState(userState);
        accessToRoute.setIdVehicleState(vehicleState);
        accessToRoute.setIsAccess(isAccess);             //Допуск: 1 - yes, 0 - no

        super.create(accessToRoute);

        return "redirect:/accessToRoutes";
    }

    @PostMapping("/createOrUpdateHSR")
    public String createOrUpdateHSR(HttpServletRequest request){
        UserVehicle userVehicle = super.getUserVehicle(Integer.parseInt(request.getParameter("idUserVehicle")));
        UserState userState = super.getUserState(Integer.parseInt(request.getParameter("idUserState")));
        VehicleState vehicleState = super.getVehicleState(Integer.parseInt(request.getParameter("idVehicleState")));

        LocalDateTime checksDateTime = null;

        if (request.getParameter("checksDateTime") != "")
            checksDateTime = LocalDateTime.parse(request.getParameter("checksDateTime"));

        AccessToRoute accessToRoute = new AccessToRoute();
        accessToRoute.setChecksDateTime(checksDateTime);
        accessToRoute.setIdUserVehicle(userVehicle);
        accessToRoute.setIdUserState(userState);
        accessToRoute.setIdVehicleState(vehicleState);
        accessToRoute.setIsAccess(Integer.parseInt(request.getParameter("isAccess")));             //Допуск: 1 - yes, 0 - no

        super.create(accessToRoute);

        return "redirect:/accessToRoutes";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute AccessToRoute accessToRoute){
        if(accessToRoute.isNew())
            super.create(accessToRoute);
        else
            super.update(accessToRoute);

        return "redirect:/accessToRoutes";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        AccessToRoute accessToRoute = super.get(id);
        ModelAndView modelAndView = new ModelAndView("accessToRoute");
        modelAndView.addObject("accessToRoute",accessToRoute);
        modelAndView.addObject("allUserVehicles",super.getAllUserVehicles());
        modelAndView.addObject("allUserStates",super.getAllUserStates());
        modelAndView.addObject("allVehicleStates",super.getAllVehicleStates());

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAccessToRoute(@RequestParam int id){
        //delete?id=
        super.delete(id);

        return "redirect:/accessToRoutes";
    }

    @RequestMapping("/get/{id}")
    public ModelAndView getAccessToRoute(@PathVariable int id){
        return new ModelAndView("accessToRoute","accessToRoute", super.get(id));
    }

    @GetMapping("/getAccessToRouteDate/{id}")
    public ModelAndView getAccessToRouteDate(@PathVariable int id, Model model){
        AccessToRoute accessToRoute = super.get(id);
        ModelAndView modelAndView = new ModelAndView("accessToRouteData");
        modelAndView.addObject("accessToRouteData",accessToRoute);
        model.addAttribute("atrUserVehicleData",super.getUserVehicle(accessToRoute.getIdUserVehicle().getId()));
        model.addAttribute("atrUserStateData",super.getUserState(accessToRoute.getIdUserState().getId()));
        model.addAttribute("atrVehicleStateData",super.getVehicleState(accessToRoute.getIdVehicleState().getId()));

        return modelAndView;
    }

    @RequestMapping(value = "/checksDateTime/{startDate}/{endDate}", method = RequestMethod.GET)
    public ModelAndView getChecksDateTime(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){

        return new ModelAndView("accessToRoutes","accessToRoutes",super.getByChecksDateTime(startDate,endDate));
    }

    @GetMapping("/getByIdUserVehicles/{id}")
    public ModelAndView getByIdUserVehicleList(int id){
        return new ModelAndView("accessToRoutes","getByIdUserVehicles",super.getByIdUserVehicles(id));
    }

    @RequestMapping("/getByIdUserStates/{id}")
    public String getByIdUserStateList(int id, Model model){
        model.addAttribute("accessToRoutes", super.getByIdUserStates(id));

        return "accessToRoutes";
    }

    @RequestMapping(value = "/getByIdVehicleStates/{id}", method = RequestMethod.GET)
    public ModelAndView getByIdVehicleStateList(int id){
        return new ModelAndView("accessToRoutes","accessToRoutes",super.getByIdVehicleStates(id));
    }

    @GetMapping(value = "/getByIsAccess/{id}")
    public ModelAndView getByIsAccessList(int isAccess){
        return new ModelAndView("accessToRoutes","accessToRoutes",super.getByIsAccess(isAccess));
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
            public String getAsText() {
                if (super.getValue() == null)
                    return null;
                LocalDateTime value = (LocalDateTime) super.getValue();
                return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        };
        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(UserVehicle.class, new UserVehiclePropertyEditor());
        binder.registerCustomEditor(UserState.class, new UserStatePropertyEditor());
        binder.registerCustomEditor(VehicleState.class, new VehicleStatePropertyEditor());
    }*/
}
