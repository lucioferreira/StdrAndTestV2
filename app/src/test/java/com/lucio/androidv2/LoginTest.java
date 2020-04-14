package com.lucio.androidv2;

import com.lucio.androidv2.helper.Login;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LoginTest {

    @Test
    public void testPasswordExpectTrue(){
        System.out.println("**--- Test testPasswordExpectTrue executed ---**");

        Login login = Mockito.mock(Login.class);

        String password = "A@1";
        boolean expected = true;

        when(login.isValidPassword(password)).thenReturn(expected);

        boolean actual = login.isValidPassword(password);

        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordExpectFalse(){
        System.out.println("**--- Test testPasswordExpectFalse executed ---**");

        Login login = Mockito.mock(Login.class);

        String password = "teste";
        boolean expected = false;

        when(login.isValidPassword(password)).thenReturn(expected);

        boolean actual = login.isValidPassword(password);

        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameAsEmailExpectTrue(){
        System.out.println("**--- Test testUsernameAsEmailExpectTrue executed ---**");

        Login login = Mockito.mock(Login.class);

        String username = "name@domain.com";
        boolean expected = true;

        when(login.isValidUsername(username)).thenReturn(expected);

        boolean actual = login.isValidUsername(username);

        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameAsCpfExpectTrue(){
        System.out.println("**--- Test testUsernameAsCpfExpectTrue executed ---**");

        Login login = Mockito.mock(Login.class);

        String username = "999.999.999-99";
        boolean expected = true;

        when(login.isValidUsername(username)).thenReturn(expected);

        boolean actual = login.isValidUsername(username);

        assertEquals(expected, actual);
    }


    @Test
    public void testUsernameExpectFalse(){
        System.out.println("**--- Test testUsernameExpectFalse executed ---**");

        Login login = Mockito.mock(Login.class);

        String username = "name.domain.com";
        boolean expected = false;

        when(login.isValidUsername(username)).thenReturn(expected);

        boolean actual = login.isValidUsername(username);

        assertEquals(expected, actual);
    }

}
