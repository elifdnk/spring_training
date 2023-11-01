package com.cydeo.dto;

import com.cydeo.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)  //it is good practice to put all DTO classes.
public class UserDTO {

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password; //user should be able to send password but shouldnt be able to get password
    private String username;
    private UserRole role;

    @JsonManagedReference  // This field is going to be serialized
    //show accountDTO in the UserDTO
    private AccountDTO account;

}
