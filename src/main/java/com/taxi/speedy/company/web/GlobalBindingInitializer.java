package com.taxi.speedy.company.web;

import com.taxi.speedy.company.model.User;
import com.taxi.speedy.company.model.Vehicle;
import com.taxi.speedy.company.model.propertyeditor.UserPropertyEditor;
import com.taxi.speedy.company.model.propertyeditor.VehiclePropertyEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//Глобальный клас который преабразовывает обьэкты в строки и наобород (при передачи параметров)
@ControllerAdvice
public class GlobalBindingInitializer {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(LocalDateTime.class, editor);
        binder.registerCustomEditor(User.class, new UserPropertyEditor());
        binder.registerCustomEditor(Vehicle.class, new VehiclePropertyEditor());
    }
}
