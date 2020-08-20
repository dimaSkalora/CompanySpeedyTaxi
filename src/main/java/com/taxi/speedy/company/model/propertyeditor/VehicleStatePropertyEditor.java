package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.VehicleState;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class VehicleStatePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        VehicleState vehicleState = (VehicleState) getValue(); //getValue Получает значение свойства.

        if (vehicleState != null)
            System.out.println("VehicleStatePropertyEditor getAsText "+vehicleState.toString());

        return vehicleState == null ? "" : String.valueOf(vehicleState.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            VehicleState vehicleState = new VehicleState();
            vehicleState.setId(Integer.valueOf(text));
            System.out.println("VehicleStatePropertyEditor setAsText "+text);
            setValue(vehicleState);
        }
    }
}
