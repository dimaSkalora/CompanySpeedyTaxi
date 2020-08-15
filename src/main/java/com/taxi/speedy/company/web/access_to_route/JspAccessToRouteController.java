package com.taxi.speedy.company.web.access_to_route;

import com.taxi.speedy.company.model.AccessToRoute;
import com.taxi.speedy.company.model.UserState;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.VehicleState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("accessToRoutes")
public class JspAccessToRouteController extends AbstractAccessToRouteController{

    @RequestMapping
    public String accessToRoutes(Model model){
        model.addAttribute("accessToRoutes", super.getAll());

        return "accessToRoutes";
    }

    @RequestMapping(value = "accessToRoute", method = RequestMethod.GET)
    public String accessToRoute(Model model){
        model.addAttribute("accessToRoute", new AccessToRoute());
        model.addAttribute("atrAllUserVehicles",super.getAllUserVehicles());
        model.addAttribute("atrAllUserStates",super.getAllUserStates());
        model.addAttribute("atrAllVehicleStates",super.getAllVehicleStates());

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

        return "redirect:accessToRoutes";
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

        return "redirect:accessToRoutes";
    }


}
