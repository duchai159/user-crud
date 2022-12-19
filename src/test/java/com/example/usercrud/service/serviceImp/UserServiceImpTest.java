package com.example.usercrud.service.serviceImp;

import com.example.usercrud.entity.User;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.mapper.UserMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {
    @Nested
    public class getUserByIdTest{
        @Mock
        private UserMapper userMapper;
        @InjectMocks
        private UserServiceImp userServiceImp;

        @Test
        void UserNotFoundTest() {
            Mockito.when(userMapper.getUserById(Mockito.anyLong())).thenReturn(Optional.empty());
            assertThrows(UserNotFoundException.class, () -> userServiceImp.getUserById(Mockito.anyLong()));
        }
        @Test
        void success(){
            User user = new User((long) 7,"a",2,"aa");
            Mockito.when(userMapper.getUserById(user.getId())).thenReturn(Optional.of(user));
            assertDoesNotThrow(()-> userServiceImp.getUserById(user.getId()));
        }
    }
//    @Mock
//    ArrayList<String> mockedList;
//    @Test
//    public void testMockFunction(){
//        Mockito.when(mockedList.size()).thenThrow(NullPointerException.class);
//        assertThrows(NullPointerException.class,() -> mockedList.size());
//    }
}