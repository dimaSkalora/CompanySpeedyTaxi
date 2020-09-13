package com.taxi.speedy.company.web.user;

import com.taxi.speedy.company.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping(AdminRestUserController.REST_URL)
public class AdminRestUserController extends AbstractUserController{

    static final String REST_URL = "/rest/admin/users";
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //produces - Воспроизводимые типы носителей запрошенного запроса, сужающие первичное отображение.
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable - Аннотации, указывающие, что параметр метода должен быть привязан к переменной шаблона UR
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    //consumes - Типы расходных материалов отображаемого запроса, сужающие первичное отображение.
    //RequestBody - Аннотации, указывающие параметр метода, должны быть привязаны к телу веб-запроса.
    // Тело запроса передается через HttpMessageConverter для разрешения аргумента метода в зависимости
    // от типа содержимого запроса. Необязательно, автоматическая проверка может быть применена
    // путем аннотации аргумента с помощью @Valid.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        User created = super.create(user);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(uriOfNewResource);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        //created - Создайте новый построитель с статусом CREATED и заголовком местоположения, заданным для данного URI.
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        super.update(user, id);
    }

    @Override
    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@RequestParam("email") String email) {
        return super.getByEmail(email);
    }

    @GetMapping(value = "/text")
    public String testUTF() {
        return "Русский текст";
    }

    @GetMapping(value = "/text2",produces = MediaType.APPLICATION_JSON_VALUE)
    public String testUTF2() {
        return "Русский текст";
    }
}
