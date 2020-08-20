package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.UserVehicle;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class UserVehiclePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        UserVehicle userVehicle = (UserVehicle) getValue(); //getValue Получает значение свойства.

        if (userVehicle != null)
            System.out.println("UserVehiclePropertyEditor getAsText "+userVehicle.toString());

        return userVehicle == null ? "" : String.valueOf(userVehicle.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            UserVehicle userVehicle = new UserVehicle();
            userVehicle.setId(Integer.valueOf(text));
            System.out.println("UserVehiclePropertyEditor setAsText "+text);
            setValue(userVehicle);
        }
    }
}
