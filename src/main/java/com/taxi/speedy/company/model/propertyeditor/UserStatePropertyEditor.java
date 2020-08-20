package com.taxi.speedy.company.model.propertyeditor;

import com.taxi.speedy.company.model.UserState;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#validation
//https://www.baeldung.com/spring-mvc-custom-property-editor
public class UserStatePropertyEditor extends PropertyEditorSupport {
    //Метод getAsText () вызывается при сериализации объекта в String
    @Override
    public String getAsText() {
        UserState userState = (UserState) getValue(); //getValue Получает значение свойства.

        if (userState != null)
            System.out.println("UserStatePropertyEditor getAsText "+userState.toString());

        return userState == null ? "" : String.valueOf(userState.getId());
    }

    //Метод setAsText () используется для преобразования String в другой объект
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text))
            setValue(null);             //setValue - Установливает (или изменяет) объект, который должен быть отредактирован.
        else{
            UserState userState = new UserState();
            userState.setId(Integer.valueOf(text));
            System.out.println("UserStatePropertyEditor setAsText "+text);
            setValue(userState);
        }
    }
}
