package com.cydeo.controller;

import com.cydeo.dto.StudentDTO;
import com.cydeo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mvc;

    //StudentController depended on Student class.
    @MockBean //it is able to find the students servers class bean.
            StudentService studentService;


    @Test
    void getStudent_Test() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  //status coming from MockMvcResultMatchers
                .andExpect(content().json("{\"firstName\": \"Mike\",\"lastName\": \"Smith\",\"age\": 20}"))  //we provide json obj  but it is hard coded
                .andDo(print()) //print() coming from MockMvcResultHandlers. If we want to see on console some result we use "andDo"
                .andReturn();

    }

    @Test
    void jsonAssert_Test() throws Exception {

        String expected = "{\"firstName\": \"Mike\",\"lastName\": \"Smith\",\"age\": 20}";
        String actual = mvc.perform(MockMvcRequestBuilders.get("/student")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();


        // expected -> {"firstName": "Mike", "lastName":  "Smith", "age": 20}
        // actual   -> {"firstName": "Mike", "lastName":  "Smith", "age": 20}

        JSONAssert.assertEquals(expected, actual, true); //this true = want to exactly matching

    }


    @Test
    void getStudents_Test() throws Exception {

        when(studentService.getStudents()).thenReturn(Arrays.asList(
                new StudentDTO("John", "Doe", 20),
                new StudentDTO("Tom", "Hanks", 50)
        ));         // Creating my stub (behavior for my mock object/StudentService obj)

        mvc.perform(MockMvcRequestBuilders.get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(content()
                        .json("[{\"firstName\": \"John\", \"lastName\":  \"Doe\", \"age\": 20}" +
                                ", {\"firstName\": \"Tom\", \"lastName\":  \"Hanks\", \"age\": 50}]"))
                .andDo(print())
                .andReturn();

    }

}
