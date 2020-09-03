package com.taxi.speedy.company.web.taxi_dispatcher_order;

import com.taxi.speedy.company.model.TaxiDispatcher;
import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static com.taxi.speedy.company.util.DateTimeUtil.parseLocalDateTime;

@Controller
@RequestMapping("taxiDispatcherOrders")
public class JspTaxiDispatcherOrderController extends AbstractTaxiDispatcherOrderController{
    @GetMapping
    public String taxiDispatcherOrders(Model model){
        model.addAttribute("taxiDispatcherOrders", super.getAll());

        return "taxiDispatcherOrders";
    }

    @RequestMapping(value = "/taxiDispatcherOrder",method = RequestMethod.GET)
    public String taxiDispatcherOrder(Model model){
        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setDateTimeOrder(LocalDateTime.now());
        model.addAttribute("taxiDispatcherOrder",taxiDispatcherOrder);
        model.addAttribute("allTaxiDispatcher",super.getAllTaxiDispatcher());

        return "taxiDispatcherOrder";
    }

    @RequestMapping(value = "/taxiDispatcherOrderFilter",method = RequestMethod.GET)
    public String taxiDispatcherOrderFilter(Model model){
        model.addAttribute("taxiDispatcherOrderFilter",new TaxiDispatcherOrder());
        model.addAttribute("allTaxiDispatcher",super.getAllTaxiDispatcher());

        return "taxiDispatcherOrderFilter";
    }

    @PostMapping("/createRequestParam")
    public String createRequestParam(@RequestParam(required = true) LocalDateTime dateTimeOrder
                                    ,@RequestParam(required = true) int idTaxiDispatcher
                                    ,@RequestParam(required = true) String userName
                                    ,@RequestParam(required = true) String userPhone
                                    ,@RequestParam(required = true) String addressDeparture
                                    ,@RequestParam(required = true) String addressArrival
                                    ,@RequestParam(required = true) LocalDateTime startDate
                                    ,@RequestParam(required = false) LocalDateTime endDate
                                    ,@RequestParam(required = false) Integer fulfilled){

        //createRequestParam?dateTimeOrder=2020-08-24?idTaxiDispatcher=25?userName=TEST?userPhone=TEST?.....

        TaxiDispatcher taxiDispatcher = super.getTaxiDispatcher(idTaxiDispatcher);

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        taxiDispatcherOrder.setDateTimeOrder(dateTimeOrder);//Дата и время заказа
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        taxiDispatcherOrder.setUserName(userName);
        taxiDispatcherOrder.setUserPhone(userPhone);
        taxiDispatcherOrder.setAddressDeparture(addressDeparture);
        taxiDispatcherOrder.setAddressArrival(addressArrival);
        taxiDispatcherOrder.setStartDate(startDate);//Время отправление
        taxiDispatcherOrder.setEndDate(endDate);//Время прибытие
        taxiDispatcherOrder.setFulfilled(fulfilled);//Выполнен заказ: 1 - да, 0 - нет

        super.create(taxiDispatcherOrder);

        return "redirect:/taxiDispatcherOrders";
    }

    @PostMapping("/createOrUpdateHSR")
    public ModelAndView createOrUpdateHSR(HttpServletRequest request){

        RedirectView redirectView = new RedirectView("taxiDispatcherOrders");
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(redirectView);

        TaxiDispatcher taxiDispatcher = super.getTaxiDispatcher(Integer.parseInt(request.getParameter("idTaxiDispatcher")));

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        if (request.getParameter("dateTimeOrder") != "")
            taxiDispatcherOrder.setDateTimeOrder(LocalDateTime.parse(request.getParameter("dateTimeOrder")));//Дата и время заказа
        taxiDispatcherOrder.setIdTaxiDispatcher(taxiDispatcher);
        if (request.getParameter("userName") != "")
            taxiDispatcherOrder.setUserName(request.getParameter("userName"));
        if (request.getParameter("userPhone") != "")
            taxiDispatcherOrder.setUserPhone(request.getParameter("userPhone"));

        if (request.getParameter("addressDeparture") != "")
            taxiDispatcherOrder.setAddressDeparture(request.getParameter("addressDeparture"));
        if (request.getParameter("addressArrival") != "")
            taxiDispatcherOrder.setAddressArrival(request.getParameter("addressArrival"));
        if (request.getParameter("startDate") != "")
            taxiDispatcherOrder.setStartDate(LocalDateTime.parse(request.getParameter("startDate")));//Время отправление
        if (request.getParameter("endDate") != "")
            taxiDispatcherOrder.setEndDate(LocalDateTime.parse(request.getParameter("endDate")));//Время прибытие
        if (request.getParameter("fulfilled") != "")
            taxiDispatcherOrder.setFulfilled(Integer.parseInt(request.getParameter("fulfilled")));//Выполнен заказ: 1 - да, 0 - нет

        super.create(taxiDispatcherOrder);

        return modelAndView;
    }

    @RequestMapping(value = "/createOrUpdate",method = RequestMethod.POST)
    public String createOrUpdate(@ModelAttribute TaxiDispatcherOrder taxiDispatcherOrder){
        if (taxiDispatcherOrder.isNew())
            super.create(taxiDispatcherOrder);
        else
            super.update(taxiDispatcherOrder);

        return "redirect:/taxiDispatcherOrders";
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable int id){
        TaxiDispatcherOrder taxiDispatcherOrder = super.get(id);

        ModelAndView modelAndView = new ModelAndView("taxiDispatcherOrder");
        modelAndView.addObject("taxiDispatcherOrder",taxiDispatcherOrder);
        modelAndView.addObject("allTaxiDispatcher",super.getAllTaxiDispatcher());

        return modelAndView;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String deleteTaxiDispatcherOrder(@RequestParam int id){
        //delete?id=5555

        super.delete(id);

        return "redirect:/taxiDispatcherOrders";
    }

    @GetMapping("/get/{id}")
    public ModelAndView getTDO(@PathVariable int id){
        TaxiDispatcherOrder taxiDispatcherOrder = super.get(id);

        ModelAndView modelAndView = new ModelAndView("taxiDispatcherOrder");
        modelAndView.addObject("taxiDispatcherOrder",taxiDispatcherOrder);
        //modelAndView.addObject("allTaxiDispatcher",super.getAllTaxiDispatcher());

        return modelAndView;
    }

    @GetMapping("/getByIdTaxiDispatcherList/(idTaxiDispatcher)")
    public ModelAndView getByIdTaxiDispatcherList(@PathVariable int getByIdTaxiDispatcher){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByIdTaxiDispatcher(getByIdTaxiDispatcher));
    }

    @RequestMapping(value = "/getByAddressDepartureList/(idTaxiDispatcher)",method = RequestMethod.GET)
    public ModelAndView getByAddressDepartureList(@PathVariable String addressDeparture){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByAddressDeparture(addressDeparture));
    }

    @RequestMapping(value = "/getByAddressArrivalList",method = RequestMethod.GET)
    public ModelAndView getByAddressArrivalList(@RequestParam String addressArrival){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByAddressArrival(addressArrival));
    }

    @GetMapping("/getByUserNameList/(userName)")
    public ModelAndView getByUserNameList(@PathVariable String userName){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByUserName(userName));
    }

    @GetMapping("/getByUserPhoneList/(userPhone)")
    public ModelAndView getByUserPhoneList(@PathVariable String userPhone){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByUserPhone(userPhone));
    }

    @GetMapping("/getByFulfilledList/(userPhone)")
    public ModelAndView getByFulfilledList(@PathVariable int fulfilled){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByFulfilled(fulfilled));
    }

    @RequestMapping("/getByBetweenStartDateList/(startDateTime)/{endDateTime}")
    public ModelAndView getByBetweenStartDateList(@PathVariable LocalDateTime startDateTime, @PathVariable LocalDateTime endDateTime){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByBetweenStartDate(startDateTime,endDateTime));
    }

    @RequestMapping(value = "/getByBetweenEndDateList/(startDateTime)/{endDateTime}",method = RequestMethod.GET)
    public ModelAndView getByBetweenEndDateList(@PathVariable LocalDateTime startDateTime, @PathVariable LocalDateTime endDateTime){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getByBetweenEndDate(startDateTime,endDateTime));
    }

    @PostMapping("/filter")
    public ModelAndView getFilterTaxiDispatcherOrderList(@ModelAttribute TaxiDispatcherOrder taxiDispatcherOrder){
        return new ModelAndView("taxiDispatcherOrders","taxiDispatcherOrders"
                ,super.getFilterTaxiDispatcherOrder(taxiDispatcherOrder));
    }

    @PostMapping("/filterCondition")
    public String getFilterTaxiDispatcherOrderConditionList(HttpServletRequest request, Model model){
        StringBuilder sqlCondition = new StringBuilder();
        //filter by TaxiDispatcherOrder
        int id = 0;
        if(request.getParameter("id") != "")
          id = Integer.parseInt(request.getParameter("id"));
        LocalDateTime dateTimeOrder = parseLocalDateTime(request.getParameter("dateTimeOrder")); //Дата и время заказа
        int idTD =  Integer.parseInt(request.getParameter("idTaxiDispatcher"));
        TaxiDispatcher idTaxiDispatcher = null;
        if(idTD > 0)
            idTaxiDispatcher = super.getTaxiDispatcher(idTD);
        String userName = request.getParameter("userName");
        String userPhone = request.getParameter("userName");
        String addressDeparture = request.getParameter("userName");   //Адрес отправление
        String addressArrival = request.getParameter("userName");    //Адрес прибытие
        LocalDateTime startDate = parseLocalDateTime(request.getParameter("startDate")); //Время отправление
        LocalDateTime endDate = parseLocalDateTime(request.getParameter("endDate"));  //Время прибытие
        Integer fulfilled = Integer.parseInt(request.getParameter("fulfilled"));  //Выполнен заказ: 1 - да, 0 - нет
        //fliter by sql Condition
        LocalDateTime dateTimeOrderFrom = parseLocalDateTime(request.getParameter("dateTimeOrderFrom"));
        LocalDateTime dateTimeOrderTo = parseLocalDateTime(request.getParameter("dateTimeOrderTo"));
        LocalDateTime startDateFrom = parseLocalDateTime(request.getParameter("startDateFrom"));
        LocalDateTime startDateTo = parseLocalDateTime(request.getParameter("startDateTo"));
        LocalDateTime endDateFrom = parseLocalDateTime(request.getParameter("endDateFrom"));
        LocalDateTime endDateTo = parseLocalDateTime(request.getParameter("endDateTo"));

        TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
        if (id > 0)
            taxiDispatcherOrder.setId(id);
        if (dateTimeOrder != null)
            taxiDispatcherOrder.setDateTimeOrder(dateTimeOrder);
        if (idTaxiDispatcher != null)
            taxiDispatcherOrder.setIdTaxiDispatcher(idTaxiDispatcher);
        if (userName != null)
            taxiDispatcherOrder.setUserName(userName);
        if (userPhone != null)
            taxiDispatcherOrder.setUserPhone(userPhone);
        if (addressDeparture != null)
            taxiDispatcherOrder.setAddressDeparture(addressDeparture);
        if (addressArrival != null)
            taxiDispatcherOrder.setAddressArrival(addressArrival);
        if (startDate != null)
            taxiDispatcherOrder.setStartDate(startDate);
        if (endDate != null)
            taxiDispatcherOrder.setEndDate(endDate);
        if (fulfilled != null)
            taxiDispatcherOrder.setFulfilled(fulfilled);

        if((sqlCondition.length()>0) && (dateTimeOrderFrom != null) && (dateTimeOrderTo != null))
            sqlCondition.append(" and tdo.date_time_order between "+dateTimeOrderFrom+" and "+dateTimeOrderTo);
        if((sqlCondition.length()<=0) && (dateTimeOrderFrom != null) && (dateTimeOrderTo != null))
            sqlCondition.append(" tdo.date_time_order between "+dateTimeOrderFrom+" and "+dateTimeOrderTo);
        if ((sqlCondition.length()>0) && (startDateFrom != null) && (startDateTo != null))
            sqlCondition.append(" and tdo.start_date between "+startDateFrom+" and "+startDateTo);
        if ((sqlCondition.length()<=0) && (startDateFrom != null) && (startDateTo != null))
            sqlCondition.append(" tdo.start_date between "+startDateFrom+" and "+startDateTo);
        if ((sqlCondition.length()>0) && (endDateFrom != null) && (endDateTo != null))
            sqlCondition.append(" and tdo.end_date between "+endDateFrom+" and "+endDateTo);
        if ((sqlCondition.length()<=0) && (endDateFrom != null) && (endDateTo != null))
            sqlCondition.append(" tdo.end_date between "+endDateFrom+" and "+endDateTo);

        model.addAttribute("taxiDispatcherOrders", super.getFilterTaxiDispatcherOrder(taxiDispatcherOrder, String.valueOf(sqlCondition)));

        return "taxiDispatcherOrders";
    }





}
