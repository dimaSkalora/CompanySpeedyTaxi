package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.Vehicle;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class VehiclePropertyEditor extends PropertyEditorSupport {

    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        Vehicle vehicle = (Vehicle) getValue(); //getValue Получает значение свойства.

        if (vehicle != null)
            System.out.println("VehiclePropertyEditor getAsText "+vehicle.toString());

        return vehicle == null ? "" : String.valueOf(vehicle.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            Vehicle vehicle = new Vehicle();
            vehicle.setId(Integer.valueOf(text));
            System.out.println("VehiclePropertyEditor setAsText "+text);
            setValue(vehicle);
        }
    }
}
