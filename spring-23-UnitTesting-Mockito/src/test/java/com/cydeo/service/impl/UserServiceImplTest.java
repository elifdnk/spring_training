package com.cydeo.service.impl;

import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl userService;  //in test class always adding Impl Class not interface!

    //behind the scene first it create one for USerServiceImpl obj than inject USerRepository@Mock and UserMapper@Mock in the USerServiceImpl class


    @Test
    void findByUserName_Test(){

        //I'm calling the real method inside the main,which is the method I want to test
        userService.findByUserName("harold@manager.com");

        //I'm checking if this method ran or not
        verify(userRepository).findByUserNameAndIsDeleted("harold@manager.com",false);

        //how much time this method called?
        verify(userRepository,times(1)).findByUserNameAndIsDeleted("harold@manager.com",false);

        verify(userRepository,atLeastOnce()).findByUserNameAndIsDeleted("harold@manager.com",false);

        //this method at least 2 times
        verify(userRepository,atLeast(1)).findByUserNameAndIsDeleted("harold@manager.com",false);


        verify(userRepository,atMostOnce()).findByUserNameAndIsDeleted("harold@manager.com",false);
        verify(userRepository,atMost(10)).findByUserNameAndIsDeleted("harold@manager.com",false);


        //in findByUserName  method; who run first userRepository or userMapper?
        InOrder inOrder = inOrder(userRepository,userMapper);

        inOrder.verify(userRepository).findByUserNameAndIsDeleted("harold@manager.com",false);
        inOrder.verify(userMapper).convertToDto(null);




    }
}