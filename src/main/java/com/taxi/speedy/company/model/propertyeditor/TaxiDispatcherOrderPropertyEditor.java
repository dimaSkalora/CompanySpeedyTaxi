package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.TaxiDispatcherOrder;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class TaxiDispatcherOrderPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        TaxiDispatcherOrder taxiDispatcherOrder = (TaxiDispatcherOrder) getValue(); //getValue Получает значение свойства.

        if (taxiDispatcherOrder != null)
            System.out.println("TaxiDispatcherOrderPropertyEditor getAsText "+taxiDispatcherOrder.toString());

        return taxiDispatcherOrder == null ? "" : String.valueOf(taxiDispatcherOrder.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            TaxiDispatcherOrder taxiDispatcherOrder = new TaxiDispatcherOrder();
            taxiDispatcherOrder.setId(Integer.valueOf(text));
            System.out.println("TaxiDispatcherOrderPropertyEditor setAsText "+text);
            setValue(taxiDispatcherOrder);
        }
    }
}
