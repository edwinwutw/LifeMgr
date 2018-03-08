package com.mvstar.edwinwu.lifemgr.ui.contact.detail;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


/**
 * Created by edwinwu on 2018/3/6.
 */
public class ContactDetailViewModelTest {

    @Mock
    private ContactRepository mRepository;

    private ContactDetailViewModel mViewModel;

    @Before
    public void setUpViewModel() throws Exception {
        MockitoAnnotations.initMocks(this);

        mViewModel = new ContactDetailViewModel(mRepository, anyString());
    }

    @Test
    public void testNull() {
        assertThat(mViewModel.getContact(), nullValue());
        assertThat(mViewModel.getSaveContactResult(), notNullValue());
    }

    @Test
    public void testGetContact() throws Exception {
        //arrange

        //action
        mViewModel.getContact();

        //assert
        verify(mRepository).getContact(anyString());
    }

    @Test
    public void testInsertContact() throws Exception {
        //arrange

        //action
        mViewModel.insertContact(anyString(), anyString(), anyString(), anyString());

        //assert
        verify(mRepository).insertContact(any());
    }

    @Test
    public void testUpdateContact() throws Exception {
        //arrange

        //action
        mViewModel.updateContact(anyString(), anyString(), anyString(), anyString());

        //assert
        verify(mRepository).updateContact(any());
    }

    @After
    public void tearDown() {
    }
}