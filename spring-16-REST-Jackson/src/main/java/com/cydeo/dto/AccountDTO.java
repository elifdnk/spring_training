package com.cydeo.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"address", "country"}, ignoreUnknown = true)  //when we ignore one than more field
@JsonIgnoreProperties(ignoreUnknown = true) // when we use this annotation, fields not known  by our application these not getting to our app.
public class AccountDTO {

    @JsonIgnore
    private String name;
    private String address;
    private String country;
    private String state;
    private String city;
    private Integer age;
    private String postalCode;

    @JsonBackReference  // This field is not going to be serialized
    //we dont see UserDTO in the AccountDTO .
    private UserDTO user;

}
