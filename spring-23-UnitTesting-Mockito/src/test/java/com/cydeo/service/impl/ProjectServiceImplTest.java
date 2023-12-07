package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    void getByProjectCode_Test(){

        //Mocks are objects , stubbers are behaviour

        // --> GHERKIN STANDARDS : GIVEN STEP

        //if findByProjectCode is called by using any kind of string, return me new Project obj
        when(projectRepository.findByProjectCode(anyString())).thenReturn(new Project()); //stubbing
        //when projectMapper.convertToDto excepting any kind of project, return me new projectDto obj
        when(projectMapper.convertToDto(any(Project.class))).thenReturn(new ProjectDTO());


        // --> GHERKIN STANDARDS : WHEN STEP

        ProjectDTO projectDTO = projectService.getByProjectCode(anyString());

        // --> GHERKIN STANDARDS : THEN STEP

        InOrder inOrder = inOrder(projectRepository,projectMapper); // I want to check the order of these 2 Mocks

        inOrder.verify(projectRepository).findByProjectCode(anyString()); // We are providing in which order these 2Mocks obj should be
        inOrder.verify(projectMapper).convertToDto(any(Project.class));

        assertNotNull(projectDTO); //Returning obj null or not?


    }


    @Test
    void getByProjectCode_ExceptionTest(){

        //when I send empty project String, instead of project entity obj,  throw me an exception
        when(projectRepository.findByProjectCode("")).thenThrow(new NoSuchElementException("Project Not Found"));

        //assertThrows work    : first provide expected exception type, give lambda expression and call method you want to test
        Throwable throwable = assertThrows(NoSuchElementException.class,()->projectService.getByProjectCode(""));

        verify(projectRepository).findByProjectCode("");
        //this checking projectmapper convertToDto method "by using any Project entity class never run
        verify(projectMapper,never()).convertToDto(any(Project.class));

        //                   excepting message         coming message.
        assertEquals("Project Not Found",throwable.getMessage());  //we check true exception or not





    }




}