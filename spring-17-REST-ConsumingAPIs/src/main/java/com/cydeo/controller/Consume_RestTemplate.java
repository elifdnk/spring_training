package com.cydeo.controller;

import com.cydeo.dto.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/cydeo")
public class Consume_RestTemplate {

    private final String URI = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    public Consume_RestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<User[]> readAllUsers() {  //JSON represent an array.because of this we write User[]
        return restTemplate.getForEntity(URI, User[].class);
        //Which DTO you want to convert?
        //restTemplate always return responseEntity
        //getForEntity : give me the URI and show me the DTO you have,so I can get that consuming API Json, I can comverted to your user and then I can show you that one as an output.
    }

    @GetMapping("{id}")
    public Object readUser(@PathVariable("id") Integer id) {
        String URL = URI + "/{id}"; // I only show certain user.
        return restTemplate.getForObject(URL, Object.class, id);

        //getForObject : we dont need any DTO ,give me URL  whatever is over there, and provide us.
        //when we use GetForObject we can not manipulate the data.(ex: @JsonIgnore annotation is doesnt work)
    }

    @GetMapping("/test")  //we want in test endpoint consume this "https://dummyapi.io/data/v1/user?limit=10" Api
    public ResponseEntity<Object> consumePostFromDummyApi() {

        HttpHeaders headers = new HttpHeaders(); //but this Api request a header. Otherwise it doesnt show the data
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id", "6298ebfecd0551211fce37a6"); // we set the header

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("https://dummyapi.io/data/v1/user?limit=10", HttpMethod.GET, entity, Object.class);
        // url: accept parameters, method: what method executed , headers: we set line48 as entity, responseType: object.class because we must the give obj
    }






}
