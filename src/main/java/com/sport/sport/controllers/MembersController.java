package com.sport.sport.controllers;

import com.sport.sport.services.Impl.MembersServiceImpl;
import com.sport.sport.services.MembersService;
import com.sport.sport.views.MemberView;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {
    private final MembersService membersService;

    public MembersController() { membersService = new MembersServiceImpl(); }

    @DeleteMapping(value =  "/delete_member/{id}")
    public void deleteMember(@PathVariable Long id) {
        membersService.deleteMember(id);
    }

    @PostMapping(value = "/add_member")
    public void addMember(@RequestBody MemberView memberView) {
        membersService.addMember(memberView);
    }

    @PostMapping(value = "/update_member/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberView memberView) {
        membersService.updateMember(id, memberView);
    }

    @GetMapping(value = "/get_member")
    public MemberView getMember(@RequestParam Long id) {
        return membersService.getMember(id);
    }

    @GetMapping(value = "/get_members")
    public List<MemberView> getMembers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String teamName
    ) {
        List<MemberView> members = new ArrayList<>();
        for (MemberView memberView : membersService.getAll()) {
            if (
                (name == null || name.equals(memberView.name)) &&
                (role == null || role.equals(memberView.role)) &&
                (id == null || id.equals(memberView.id)) &&
                (teamName == null || teamName.equals(memberView.current_team.name))
            ) {
                members.add(memberView);
            }
        }
        return members;
    }
}
