package com.taxi.speedy.company.web.taxi_order_acceptance;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import com.taxi.speedy.company.model.TaxiOrderAcceptance;
import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.UserVehicle;
import com.taxi.speedy.company.model.propertyeditor.TaxiDispatcherOrderPropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.TaxiUserOrderPropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.UserVehiclePropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.taxi.speedy.company.util.DateTimeUtil.parseLocalDateTime;
import static com.taxi.speedy.company.util.ParseUtil.parseInteger;
import static com.taxi.speedy.company.util.ParseUtil.parseString;

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

    @GetMapping("/taxiOrderAcceptanceFilter")
    public String filterTaxiOrderAcceptance(Model model){
        System.out.println(model);
        model.addAttribute("taxiOrderAcceptanceFilter", new TaxiOrderAcceptance());
        model.addAttribute("toaAllUserVehicles", super.getAllUserVehicles());
        model.addAttribute("toaAllTaxiDispatcherOrders", super.getAllTaxiDispatcherOrders());
        model.addAttribute("toaAllTaxiUserOrders", super.getAllTaxiUserOrders());

        System.out.println(model);

        return "taxiOrderAcceptanceFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam(required = true) int idUserVehicle,
                                     @RequestParam(required = false) int idTaxiDispatcherOrder,
                                     @RequestParam(required = false) int idTaxiUserOrder,
                                     @RequestParam(required = true) int executionStatus,
                                     @RequestParam(required = true) int adoptionStatus){

        //createRequestParam?idUserVehicle=11111?idTaxiDispatcherOrder=25?idTaxiUserOrder=525454.....

        UserVehicle userVehicle = super.getUserVehicle(idUserVehicle);
        TaxiDispatcherOrder taxiDispatcherOrder=null;
        TaxiUserOrder taxiUserOrder=null;

        if (idTaxiDispatcherOrder>0)
            taxiDispatcherOrder=super.getTaxiDispatcherOrder(idTaxiDispatcherOrder);
        if (idTaxiUserOrder>0)
            taxiUserOrder=super.getTaxiUserOrder(idTaxiUserOrder);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setIdTaxiUserOrder(taxiUserOrder);
        taxiOrderAcceptance.setExecutionStatus(executionStatus);
        taxiOrderAcceptance.setAdoptionStatus(adoptionStatus);

        super.create(taxiOrderAcceptance);

        return "redirect:/taxiOrderAcceptances";
    }

    @RequestMapping(value = "/createOrUpdateHSR",method = RequestMethod.POST)
    public String createOrUpdateHSR(HttpServletRequest httpServletRequest){

        UserVehicle userVehicle = super.getUserVehicle(parseInteger(httpServletRequest.getParameter("idUserVehicle")));
        TaxiDispatcherOrder taxiDispatcherOrder = super.getTaxiDispatcherOrder(parseInteger(httpServletRequest.getParameter("idTaxiDispatcherOrder")));
        TaxiUserOrder taxiUserOrder = super.getTaxiUserOrder(parseInteger(httpServletRequest.getParameter("idTaxiUserOrder")));

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        taxiOrderAcceptance.setIdTaxiUserOrder(taxiUserOrder);
        taxiOrderAcceptance.setExecutionStatus(parseInteger(httpServletRequest.getParameter("executionStatus")));
        taxiOrderAcceptance.setAdoptionStatus(parseInteger(httpServletRequest.getParameter("adoptionStatus")));

        super.create(taxiOrderAcceptance);

        return "redirect:/taxiOrderAcceptances";
    }

    @PostMapping("/createOrUpdate")
    public String createOrUpdate(@ModelAttribute TaxiOrderAcceptance taxiOrderAcceptance){
        if (taxiOrderAcceptance.isNew())
            super.create(taxiOrderAcceptance);
        else
            super.update(taxiOrderAcceptance);

        return "redirect:/taxiOrderAcceptances";
    }

    @GetMapping("/update")
    public ModelAndView update(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("taxiOrderAcceptance");
        TaxiOrderAcceptance taxiOrderAcceptance = super.get(id);
        modelAndView.addObject("taxiOrderAcceptance",taxiOrderAcceptance);
        modelAndView.addObject("toaAllUserVehicles", super.getAllUserVehicles());
        modelAndView.addObject("toaAllTaxiDispatcherOrders", super.getAllTaxiDispatcherOrders());
        modelAndView.addObject("toaAllTaxiUserOrders", super.getAllTaxiUserOrders());

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTOA(@PathVariable int id){
        //delete/5555
        super.delete(id);

        return "redirect:/taxiOrderAcceptances";
    }

    @GetMapping("/get")
    public String getTOA(@RequestParam int id, Model model){
        //delete?id=5555
        System.out.println(model);

        TaxiOrderAcceptance taxiOrderAcceptance = super.get(id);
        model.addAttribute("taxiOrderAcceptance",taxiOrderAcceptance);

        return "taxiOrderAcceptance";
    }

    @RequestMapping(value = "/getTaxiOrderAcceptanceData/{id}",method = RequestMethod.GET)
    public ModelAndView getTaxiOrderAcceptanceData(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("taxiOrderAcceptanceData");
        TaxiOrderAcceptance taxiOrderAcceptance = super.get(id);
        modelAndView.addObject("taxiOrderAcceptance",taxiOrderAcceptance);
        modelAndView.addObject("toaUserVehicle",super.getUserVehicle(taxiOrderAcceptance.getIdUserVehicle().getId()));
        modelAndView.addObject("toaTaxiDispatcherOrder",super.getTaxiDispatcherOrder(taxiOrderAcceptance.getIdTaxiDispatcherOrder().getId()));
        modelAndView.addObject("toaTaxiUserOrder",super.getTaxiUserOrder(taxiOrderAcceptance.getIdTaxiUserOrder().getId()));

        return modelAndView;
    }

    @PostMapping("/filterTOA")
    public String filterTaxiUserOrderList(TaxiOrderAcceptance taxiOrderAcceptance, Model model){
        List<TaxiOrderAcceptance> list = super.filterTaxiUserOrder(taxiOrderAcceptance);
        model.addAttribute("taxiOrderAcceptances",list);

        return "taxiOrderAcceptances";
    }

    @PostMapping("/filterCondition")
    public ModelAndView filterTaxiUserOrderSqlCondition(HttpServletRequest request){
        StringBuilder sqlCondition = new StringBuilder();
        //filter by TaxiUserOrder
        var id = parseInteger(request.getParameter("id"));
        var idUserVehicle = parseInteger(request.getParameter("idUserVehicle"));
        var idTaxiDispatcherOrder = parseInteger(request.getParameter("idTaxiDispatcherOrder"));
        var idTaxiUserOrder = parseInteger(request.getParameter("idTaxiUserOrder"));
        var executionStatus = parseInteger(request.getParameter("executionStatus"));//Статус выполнение: 1-выполнено, 0- не выполнено
        var adoptionStatus = parseInteger(request.getParameter("adoptionStatus"));//Статус принятие: 1-принятый заказ, 0- не принятый заказ
        //fliter by sql Condition
        var uvStartDateFrom = parseLocalDateTime(request.getParameter("uvStartDateFrom"));//Дата когда пользователь взял машину
        var uvStartDateTo = parseLocalDateTime(request.getParameter("uvStartDateTo"));
        var uvEndDateFrom = parseLocalDateTime(request.getParameter("uvEndDateFrom"));//Дата когда пользователь вернул машину
        var uvEndDateTo = parseLocalDateTime(request.getParameter("uvEndDateTo"));

        UserVehicle userVehicle = null;
        TaxiDispatcherOrder taxiDispatcherOrder = null;
        TaxiUserOrder taxiUserOrder = null;

        if (idUserVehicle != null)
            userVehicle = super.getUserVehicle(idUserVehicle);
        if (idTaxiDispatcherOrder != null)
            taxiDispatcherOrder = super.getTaxiDispatcherOrder(idTaxiDispatcherOrder);
        if (idTaxiUserOrder != null)
            taxiUserOrder = super.getTaxiUserOrder(idTaxiUserOrder);

        TaxiOrderAcceptance taxiOrderAcceptance = new TaxiOrderAcceptance();
        if (id != null)
            taxiOrderAcceptance.setId(id);
        if (userVehicle != null)
            taxiOrderAcceptance.setIdUserVehicle(userVehicle);
        if (taxiDispatcherOrder != null)
            taxiOrderAcceptance.setIdTaxiDispatcherOrder(taxiDispatcherOrder);
        if (taxiUserOrder != null)
            taxiOrderAcceptance.setIdTaxiUserOrder(taxiUserOrder);
        if (executionStatus != null)
            taxiOrderAcceptance.setExecutionStatus(executionStatus);
        if (adoptionStatus != null)
            taxiOrderAcceptance.setAdoptionStatus(adoptionStatus);
        if (id != null)
            taxiOrderAcceptance.setId(id);


        if (sqlCondition.length()<=0){
            if ((uvStartDateFrom != null) && (uvStartDateTo != null))
                sqlCondition.append(" uv.start_date between "+uvStartDateFrom+" and "+uvStartDateTo);
            if ((uvEndDateFrom != null) && (uvEndDateTo != null))
                sqlCondition.append(" uv.end_date between "+uvEndDateFrom+" and "+uvEndDateTo);
        }else {
            if ((uvStartDateFrom != null) && (uvStartDateTo != null))
                sqlCondition.append(" and uv.start_date between "+uvStartDateFrom+" and "+uvStartDateTo);
            if ((uvEndDateFrom != null) && (uvEndDateTo != null))
                sqlCondition.append(" and uv.end_date between "+uvEndDateFrom+" and "+uvEndDateTo);
        }

        List<TaxiOrderAcceptance> list = super.filterTaxiUserOrderSqlCondition(taxiOrderAcceptance,String.valueOf(sqlCondition));

        ModelAndView modelAndView = new ModelAndView("taxiOrderAcceptances");
        System.out.println(modelAndView);
        modelAndView.addObject("taxiOrderAcceptances",list);

        System.out.println(modelAndView);
        return modelAndView;
    }

    //Обявил глобально (GlobalBindingInitializer)
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
        binder.registerCustomEditor(UserVehicle.class, new UserVehiclePropertyEditor());
        binder.registerCustomEditor(TaxiDispatcherOrder.class, new TaxiDispatcherOrderPropertyEditor());
        binder.registerCustomEditor(TaxiUserOrder.class, new TaxiUserOrderPropertyEditor());
    }
}
