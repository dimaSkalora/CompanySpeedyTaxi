package com.taxi.speedy.company.web.taxi_user_order;

import com.taxi.speedy.company.model.TaxiUserOrder;
import com.taxi.speedy.company.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

import static com.taxi.speedy.company.util.DateTimeUtil.parseLocalDateTime;
import static com.taxi.speedy.company.util.ParseUtil.parseInteger;
import static com.taxi.speedy.company.util.ParseUtil.parseString;

@Controller
@RequestMapping("taxiUserOrders")
public class JspTaxiUserOrderController extends AbstractTaxiUserOrderController{

    @RequestMapping
    public String taxiUserOrders(Model model){
        model.addAttribute("taxiUserOrders",super.getAll());

        return "taxiUserOrders";
    }

    @RequestMapping(value = "/taxiUserOrder", method = RequestMethod.GET)
    public String taxiUserOrder(Model model){
        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setDateTimeOrder(LocalDateTime.now());
        model.addAttribute("taxiUserOrder",taxiUserOrder);
        model.addAttribute("allUsers", super.getAllUsers());

        return "taxiUserOrder";
    }

    @GetMapping("/filter")
    public String filterTaxiUserOrder(Model model){
        model.addAttribute("taxiUserOrder", new TaxiUserOrder());
        model.addAttribute("allUsers",super.getAllUsers());

        return "taxiUserOrderFilter";
    }

    @RequestMapping(value = "createRequestParam",method = RequestMethod.POST)
    public String createRequestParam(@RequestParam(required = true) LocalDateTime dateTimeOrder
                                    ,@RequestParam(required = true) int idUser
                                    ,@RequestParam(required = true) String addressDeparture
                                    ,@RequestParam(required = true) String addressArrival
                                    ,@RequestParam(required = true) LocalDateTime startDate
                                    ,@RequestParam(required = false) LocalDateTime endDate
                                    ,@RequestParam(required = false) Integer fulfilled){

        //createRequestParam?dateTimeOrder=2020-08-24?idUser=25?addressDeparture=TEST?addressArrival=TEST?.....

        User user = super.getUser(idUser);

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        taxiUserOrder.setDateTimeOrder(dateTimeOrder);//Дата и время заказа
        taxiUserOrder.setIdUser(user);
        taxiUserOrder.setAddressDeparture(addressDeparture);
        taxiUserOrder.setAddressArrival(addressArrival);
        taxiUserOrder.setStartDate(startDate);//Время отправление
        taxiUserOrder.setEndDate(endDate);//Время прибытие
        taxiUserOrder.setFulfilled(fulfilled);//Выполнен заказ: 1 - да, 0 - нет

        super.create(taxiUserOrder);

        return "redirect:/taxiUserOrders";
    }

    @PostMapping("/createOrUpdateHSR")
    public ModelAndView createOrUpdateHSR(HttpServletRequest request){
        RedirectView redirectView = new RedirectView("taxiUserOrders");
        ModelAndView modelAndView = new ModelAndView(redirectView);

        User user = super.getUser(parseInteger(request.getParameter("idUser")));

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        if (request.getParameter("dateTimeOrder") != "")
            taxiUserOrder.setDateTimeOrder(LocalDateTime.parse(request.getParameter("dateTimeOrder")));//Дата и время заказа
        taxiUserOrder.setIdUser(user);
        if (request.getParameter("addressDeparture") != "")
            taxiUserOrder.setAddressDeparture(request.getParameter("addressDeparture"));
        if (request.getParameter("addressArrival") != "")
            taxiUserOrder.setAddressArrival(request.getParameter("addressArrival"));
        if (request.getParameter("startDate") != "")
            taxiUserOrder.setStartDate(LocalDateTime.parse(request.getParameter("startDate")));//Время отправление
        if (request.getParameter("endDate") != "")
            taxiUserOrder.setEndDate(LocalDateTime.parse(request.getParameter("endDate")));//Время прибытие
        if (request.getParameter("fulfilled") != "")
            taxiUserOrder.setFulfilled(parseInteger(request.getParameter("fulfilled")));//Выполнен заказ: 1 - да, 0 - нет

        super.create(taxiUserOrder);

        return modelAndView;
    }

    @RequestMapping(value = "/createOrUpdate", method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute TaxiUserOrder taxiUserOrder){
        if (taxiUserOrder.isNew())
            super.create(taxiUserOrder);
        else
            super.update(taxiUserOrder);

        return "redirect:/taxiUserOrders";
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable int id){
        TaxiUserOrder taxiUserOrder = super.get(id);

        ModelAndView modelAndView = new ModelAndView("taxiUserOrder");
        modelAndView.addObject("taxiUserOrder",taxiUserOrder);
        modelAndView.addObject("allUsers", super.getAllUsers());

        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteTUO(@RequestParam int id){
        //delete?id=5555
        super.delete(id);

        return "redirect:/taxiUserOrders";
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getTUO(@PathVariable int id, Model model){
        TaxiUserOrder taxiUserOrder = super.get(id);
        model.addAttribute("taxiUserOrder",taxiUserOrder);

        return "taxiUserOrders";
    }

    @GetMapping("/getByIdUser/{idUser}")
    public ModelAndView getByIdUserList(@PathVariable int idUser){
        List<TaxiUserOrder> list = super.getByIdUser(idUser);

        ModelAndView modelAndView = new ModelAndView("taxiUserOrders");
        modelAndView.addObject("taxiUserOrders",list);

        return modelAndView;
    }

    @PostMapping("/filter")
    public ModelAndView filterTaxiUserOrderList(TaxiUserOrder taxiUserOrder){
        List<TaxiUserOrder> list = super.filterTaxiUserOrder(taxiUserOrder);

        return new ModelAndView("taxiUserOrders","taxiUserOrders",list);
    }

    @PostMapping("/filterCondition")
    public ModelAndView filterCondition(HttpServletRequest request){
        StringBuilder sqlCondition = new StringBuilder();
        //filter by TaxiUserOrder
        var id = parseInteger(request.getParameter("id"));
        var dateTimeOrder = parseLocalDateTime(request.getParameter("dateTimeOrder"));//Дата и время заказа
        var idUser = parseInteger(request.getParameter("idUser"));
        var addressDeparture = parseString(request.getParameter("addressDeparture"));//Адрес отправление
        var addressArrival = parseString(request.getParameter("addressArrival")); //Адрес прибытие
        var startDate = parseLocalDateTime(request.getParameter("startDate"));//Время отправление
        var endDate = parseLocalDateTime(request.getParameter("endDate"));//Время прибытие
        var fulfilled = parseInteger(request.getParameter("fulfilled"));//Выполнен заказ: 1 - да, 0 - нет
        //fliter by sql Condition
        var dateTimeOrderFrom = parseLocalDateTime(request.getParameter("dateTimeOrderFrom"));
        var dateTimeOrderTo = parseLocalDateTime(request.getParameter("dateTimeOrderTo"));
        var startDateFrom = parseLocalDateTime(request.getParameter("startDateFrom"));
        var startDateTo = parseLocalDateTime(request.getParameter("startDateTo"));
        var endDateFrom = parseLocalDateTime(request.getParameter("endDateFrom"));
        var endDateTo = parseLocalDateTime(request.getParameter("endDateTo"));

        User user=null;
        if (idUser != null)
         user = super.getUser(idUser);

        TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
        if (id != null)
            taxiUserOrder.setId(id);
        if (dateTimeOrder != null)
            taxiUserOrder.setDateTimeOrder(dateTimeOrder);
        if (user != null)
            taxiUserOrder.setIdUser(user);
        if (addressDeparture != null)
            taxiUserOrder.setAddressDeparture(addressDeparture);
        if (addressArrival != null)
            taxiUserOrder.setAddressArrival(addressArrival);
        if (startDate != null)
            taxiUserOrder.setStartDate(startDate);
        if (endDate != null)
            taxiUserOrder.setEndDate(endDate);
        if (fulfilled != null)
            taxiUserOrder.setFulfilled(fulfilled);

        if((sqlCondition.length()>0) && (dateTimeOrderFrom != null) && (dateTimeOrderTo != null))
            sqlCondition.append(" and tuo.date_time_order between "+dateTimeOrderFrom+" and "+dateTimeOrderTo);
        if((sqlCondition.length()<=0) && (dateTimeOrderFrom != null) && (dateTimeOrderTo != null))
            sqlCondition.append(" tuo.date_time_order between "+dateTimeOrderFrom+" and "+dateTimeOrderTo);
        if ((sqlCondition.length()>0) && (startDateFrom != null) && (startDateTo != null))
            sqlCondition.append(" and tuo.start_date between "+startDateFrom+" and "+startDateTo);
        if ((sqlCondition.length()<=0) && (startDateFrom != null) && (startDateTo != null))
            sqlCondition.append(" tuo.start_date between "+startDateFrom+" and "+startDateTo);
        if ((sqlCondition.length()>0) && (endDateFrom != null) && (endDateTo != null))
            sqlCondition.append(" and tuo.end_date between "+endDateFrom+" and "+endDateTo);
        if ((sqlCondition.length()<=0) && (endDateFrom != null) && (endDateTo != null))
            sqlCondition.append(" tuo.end_date between "+endDateFrom+" and "+endDateTo);

        List<TaxiUserOrder> list = super.filterTaxiUserOrderSqlCondition(taxiUserOrder,String.valueOf(sqlCondition));
        return new ModelAndView("taxiUserOrders","taxiUserOrders",list);
    }

    @RequestMapping(value = "/getTaxiUserOrderData/{id}", method = RequestMethod.GET)
    public String getTaxiUserOrderData(@PathVariable int id, Model model){
        TaxiUserOrder taxiUserOrder = super.get(id);
        User user = super.getUser(taxiUserOrder.getIdUser().getId());

        model.addAttribute("taxiUserOrderData",taxiUserOrder);
        model.addAttribute("tuoUserData",user);

        return "taxiUserOrderData";
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
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
    }*/

}
