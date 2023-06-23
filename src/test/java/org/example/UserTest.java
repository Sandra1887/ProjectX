package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserTest {
    User userMock;
    @BeforeEach
    public void setUp() {
        userMock = mock(User.class);
    }
    @Test
    public void testSetName() {
        String expected = "mockedName";
        userMock.setName(expected);

        Mockito.verify(userMock).setName(expected);
    }
    @Test
    public void testGetName() {
        String expected = "mockedName";
        when(userMock.getName()).thenReturn(expected);

        String actual = userMock.getName();

        assertEquals(expected, actual);
    }
    @Test
    public void testSetAge() {
        int expected = 10;
        userMock.setAge(expected);

        Mockito.verify(userMock).setAge(expected);
    }
    @Test
    public void testGetAge() {
        int expected = 10;
        when(userMock.getAge()).thenReturn(expected);

        int actual = userMock.getAge();

        assertEquals(expected, actual);
    }
    @Test
    public void testSetTodos() {
        List<ToDo> expected = List.of(new ToDo("mockedAssignment", "mockedAssignee", "mockedDon"));

        userMock.setTodos(expected);

        Mockito.verify(userMock).setTodos(expected);
    }
    @Test
    public void testGetTodos() {
        List<ToDo> expected = List.of(new ToDo("mockedAssignment", "mockedAssignee", "mockedDon"));
        when(userMock.getTodos()).thenReturn(expected);

        List<ToDo> actual = userMock.getTodos();

        assertEquals(expected, actual);
    }

}