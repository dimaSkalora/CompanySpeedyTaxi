package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.TaxiDispatcher;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class TaxiDispatcherPropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        TaxiDispatcher taxiDispatcher = (TaxiDispatcher) getValue(); //getValue Получает значение свойства.

        if (taxiDispatcher != null)
            System.out.println("TaxiDispatcherPropertyEditor getAsText "+taxiDispatcher.toString());

        return taxiDispatcher == null ? "" : String.valueOf(taxiDispatcher.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            TaxiDispatcher taxiDispatcher = new TaxiDispatcher();
            taxiDispatcher.setId(Integer.valueOf(text));
            System.out.println("TaxiDispatcherPropertyEditor setAsText "+text);
            setValue(taxiDispatcher);
        }
    }
}
