package com.mvstar.edwinwu.lifemgr.mockito;

import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by edwinwu on 2018/3/14.
 */
public class UserServiceTest {

    private static final String PASSWORD = "password";
    private static final User ENABLED_USER = new User("user id", "hash", true);
    private static final User DISABLED_USER = new User("disabled user id", "disabled user password hash", false);

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    private PasswordEncoder createPasswordEncoder() {
        //PasswordEncoder mock = mock(PasswordEncoder.class);
        when(passwordEncoder.encode(anyString())).thenReturn("any password hash");
        when(passwordEncoder.encode(PASSWORD)).thenReturn(ENABLED_USER.getPasswordHash());
        return passwordEncoder;
    }

    private UserRepository createUserRepository() {
        //UserRepository mock = mock(UserRepository.class);
        when(userRepository.findById(ENABLED_USER.getId())).thenReturn(ENABLED_USER);
        when(userRepository.findById(DISABLED_USER.getId())).thenReturn(DISABLED_USER);
        return userRepository;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userRepository = createUserRepository();
        passwordEncoder = createPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void testDate() {
        Date mock = mock(Date.class);

        doAnswer(InvocationOnMock::callRealMethod).when(mock).setTime(anyLong());
        doAnswer(InvocationOnMock::callRealMethod).when(mock).getTime();

        mock.setTime(52);
        mock.getTime();

        verify(mock).setTime(52);
        verify(mock).getTime();
        //following will fail because 'doSomethingUnexpected()' is unexpected
        verifyNoMoreInteractions(mock);
        assertEquals(52, mock.getTime());
    }



    @Test
    public void shouldBeValidForValidCredentials() {
        boolean userIsValid = userService.isValidUser(ENABLED_USER.getId(), PASSWORD);
        assertTrue(userIsValid);

        // userRepository had to be used to find a user with id="user id"
        verify(userRepository).findById(ENABLED_USER.getId());

        // passwordEncoder had to be used to compute a hash of "password"
        verify(passwordEncoder).encode(PASSWORD);
    }

    @Test
    public void shouldBeInvalidForInvalidId() {
        boolean userIsValid = userService.isValidUser("invalid id", PASSWORD);
        assertFalse(userIsValid);

        InOrder inOrder = inOrder(userRepository, passwordEncoder);
        inOrder.verify(userRepository).findById("invalid id");
        inOrder.verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    public void shouldBeInvalidForInvalidPassword() {
        boolean userIsValid = userService.isValidUser(ENABLED_USER.getId(), "invalid");
        assertFalse(userIsValid);

        verify(userRepository).findById(ENABLED_USER.getId());

        ArgumentCaptor<String> passwordCaptor = ArgumentCaptor.forClass(String.class);
        verify(passwordEncoder).encode(passwordCaptor.capture());
        assertEquals("invalid", passwordCaptor.getValue());
    }

    @Test
    public void shouldBeInvalidForDisabledUser() {
        boolean userIsValid = userService.isValidUser(DISABLED_USER.getId(), PASSWORD);
        assertFalse(userIsValid);

        verify(userRepository).findById(DISABLED_USER.getId());
        verifyZeroInteractions(passwordEncoder);
    }


}