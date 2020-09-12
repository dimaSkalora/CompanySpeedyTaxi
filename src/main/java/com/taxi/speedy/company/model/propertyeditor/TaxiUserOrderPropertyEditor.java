package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.TaxiUserOrder;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class TaxiUserOrderPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        TaxiUserOrder taxiUserOrder = (TaxiUserOrder) getValue(); //getValue Получает значение свойства.

        if (taxiUserOrder != null)
            System.out.println("TaxiUserOrderPropertyEditor getAsText "+taxiUserOrder.toString());

        return taxiUserOrder == null ? "" : String.valueOf(taxiUserOrder.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            TaxiUserOrder taxiUserOrder = new TaxiUserOrder();
            taxiUserOrder.setId(Integer.valueOf(text));
            System.out.println("TaxiUserOrderPropertyEditor setAsText "+text);
            setValue(taxiUserOrder);
        }
    }
}

