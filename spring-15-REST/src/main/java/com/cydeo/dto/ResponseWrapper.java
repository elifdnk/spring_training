package com.cydeo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
public class ResponseWrapper {   //Whatever I am gonna put this class , you gonna see in the API body as a JSON

    private boolean success;
    private String message;
    private Integer code;
    private Object data; // I put Object, because it can be CourseDTO or AccountDTO or something else DTO

    public ResponseWrapper(String message,Object data){
        this.message = message;
        this.data =data;
        this.code= HttpStatus.OK.value();
        this.success =true;
    }

    public ResponseWrapper(String message){
        this.message=message;
        this.code=HttpStatus.OK.value();
        this.success=true;
    }

    //Why we create two constructor?
    //if we delete something use second constructor, otherwise use first constructor.


}
