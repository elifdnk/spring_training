package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L,2L,3L})
    void findById_Test(long id){

        Task task =  new Task();


        //CREATE OUR MOCKS
        //GIVEN PART(Preparation)

                                //findById come from CRUD repository, because of this we must  provide Optional obj.
        when(taskRepository.findById(id)).thenReturn(Optional.of(task)); //Optional<Task>
        when(taskMapper.convertToDto(task)).thenReturn(new TaskDTO());

        //RESULT COMING FROM HERE
        //WHEN PART (action is happening)

        taskService.findById(id); //we are not defining this will be the method I want to test


        //VERIFY THE RESULT
        //THEN PART (Assertions and verification checks)

        verify(taskRepository).findById(id);
        verify(taskMapper).convertToDto(task);
    }


    @Test
    void findById_BDD_Test(){

        //GIVEN

        Task task = new Task();

        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDto(task)).willReturn(new TaskDTO());

        //WHEN

        taskService.findById(anyLong());

        //THEN

        then(taskRepository).should().findById(anyLong());
        then(taskMapper).should(atLeastOnce()).convertToDto(task);

    }


}