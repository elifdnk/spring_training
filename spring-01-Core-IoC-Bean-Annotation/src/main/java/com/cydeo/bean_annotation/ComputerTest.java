package com.cydeo.bean_annotation;

import com.cydeo.bean_annotation.casefactory.Case;
import com.cydeo.bean_annotation.config.ComputerConfig;
import com.cydeo.bean_annotation.monitorfactory.Monitor;
import com.cydeo.bean_annotation.motherboardfactory.Motherboard;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComputerTest {
    public static void main(String[] args) {


        System.out.println("Creating Container");

        //Creating container by using Application Context
       ApplicationContext container = new AnnotationConfigApplicationContext(ComputerConfig.class);

       Case thaCase = container.getBean(Case.class);
       Monitor theMonitor = container.getBean(Monitor.class);
       Motherboard theMotherboard = container.getBean(Motherboard.class);

       PC myPc = new PC(thaCase,theMonitor,theMotherboard);
       myPc.powerUp();













    }
}
