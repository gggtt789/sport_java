package com.sport.sport.services;

import com.sport.sport.DAO.MembersDAO;
import com.sport.sport.entities.Member;
import com.sport.sport.services.Impl.MembersServiceImpl;

import com.sport.sport.views.MemberView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class MembersServiceTests {
    private MembersDAO membersDAO;
    private MembersService membersService;

    @Before
    public void setUp() {
        membersDAO = mock(MembersDAO.class);
        membersService = new MembersServiceImpl(membersDAO);
    }

    @Test
    public void getAllMembers() {
        Member member1 = new Member(1L, "name1", null);
        when(membersDAO.getById(1L)).thenReturn(member1);

        Member member2 = new Member(2L, "name2", null);
        when(membersDAO.getById(2L)).thenReturn(member2);

        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        when(membersDAO.getAll()).thenReturn(members);

        List<MemberView> memberViews = membersService.getAll();
        assertEquals(memberViews.size(), 2);
        assertEquals(memberViews.get(0).name, "name1");
        assertEquals(memberViews.get(1).name, "name2");

        verify(membersDAO, times(1)).getById(1L);
        verify(membersDAO, times(1)).getById(2L);
        verify(membersDAO, times(2)).getById(any());
        verify(membersDAO, times(1)).getAll();
    }

    @Test
    public void deleteMember() {
        Member member = new Member(1L, "name", null);
        when(membersDAO.getById(any())).thenReturn(member);
        doNothing().when(membersDAO).update(any());


        membersService.deleteMember(1L);
        verify(membersDAO, times(1)).update(any());
        verify(membersDAO, times(1)).update(member);
        verify(membersDAO, times(1)).getById(any());
        verify(membersDAO, times(1)).getById(1L);
    }


    @Test
    public void addMember() {
        doNothing().when(membersDAO).save(any());

        MemberView memberView = new MemberView();
        memberView.name = "name1";
        membersService.addMember(memberView);

        verify(membersDAO, times(1)).save(any());
    }
}
