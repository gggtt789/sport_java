package com.sport.sport.controllers;

import com.sport.sport.views.MemberView;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MembersControllerTest {
    private final MembersController membersController = new MembersController();

    @Test
    public void testOk() {
        assert(4 == membersController.getMembers(null, null, null, null).size());

        MemberView memberView = new MemberView();
        memberView.name = "name1";
        memberView.birthday = Date.valueOf("2020-10-10");
        membersController.addMember(memberView);


        List<MemberView> members = membersController.getMembers(null, null, null, null);
        assert(5 == members.size());
        MemberView memberView1 = members.get(4);
        Long id = memberView1.id;
        assertEquals("name1", memberView1.name);


        MemberView memberView2 = new MemberView();
        memberView2.name = "name2";
        memberView2.birthday = Date.valueOf("2022-10-10");
        membersController.updateMember(id, memberView2);


        MemberView memberView3 = membersController.getMember(id);
        assertEquals("name2", memberView3.name);
        assertEquals(Date.valueOf("2022-10-10"), memberView3.birthday);

        membersController.deleteMember(id);
        assert(4 == membersController.getMembers(null, null, null, null).size());
    }
}
