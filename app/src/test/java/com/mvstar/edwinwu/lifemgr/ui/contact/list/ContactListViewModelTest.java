package com.mvstar.edwinwu.lifemgr.ui.contact.list;

import android.arch.lifecycle.Observer;

import com.mvstar.edwinwu.lifemgr.data.ContactRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by edwinwu on 2018/3/7.
 */
@RunWith(JUnit4.class)
public class ContactListViewModelTest {

    @Mock
    private ContactRepository mRepository;

    private ContactListViewModel mViewModel;

    @Before
    public void setUpViewModel() throws Exception {
        MockitoAnnotations.initMocks(this);

        mViewModel = new ContactListViewModel(mRepository);
    }

    @Test
    public void testNull() {
        assertThat(mViewModel.getContactList(), nullValue());
        assertThat(mViewModel.getDeleteContactResult(), notNullValue());
    }

    @Test
    public void testGetContactList() throws Exception {
        //arrange

        //act
        mViewModel.getContactList();

        //assert
        verify(mRepository).getContactList();
    }


    public void testDelete() throws Exception {
        //arrange
        Observer observer = mock(Observer.class);
        mViewModel.getDeleteContactResult().observeForever(observer);
        verifyNoMoreInteractions(observer);

        //doReturn(Single.just(items)).when(model).loadItems();

        //action
        //mViewModel.delete(anyString());

        //verify(mRepository).deleteContact(anyString());
        //assertTrue(mViewModel.getDeleteContactResult().getValue() == nullValue());

        //assert
        // check observer is updated
    }

    @Test
    public void testDeleteContact() throws Exception {
        //arrange

        //action
        mViewModel.deleteContact(anyString());

        //assert
        verify(mRepository).deleteContact(anyString());
    }

    @After
    public void tearDown() {
    }

}