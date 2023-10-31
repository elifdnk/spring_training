package com.cydeo.client;

import com.cydeo.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "https://jsonplaceholder.typicode.com",name="USER-CLIENT")  //name represent the url we can think like that.
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers();//whenever call this getUsers method, it will hit this endpoint and it will add /users too. (this is controller structure) this endpoint will execute will getMapping. when the execute endpoint JSON is coming and Json assign to DTO (List<User>)

}
