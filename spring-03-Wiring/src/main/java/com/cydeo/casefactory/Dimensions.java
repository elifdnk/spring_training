package com.cydeo.casefactory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Dimensions {
    private  int width;
    private int height;
    private int depth;
   ;

    public Dimensions(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void pressPowerButton(){
        System.out.println("Power button pressed");
    }
}
