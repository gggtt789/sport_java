package com.sport.sport.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Members", schema = "public")
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name") private String name;
    @Column(name = "birthday") private Date birthday;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MemberTeam> member_teams;

    public Member() {}
    public Member(Long id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }
    public Member(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Date getBirthday() { return birthday; }
    public List<MemberTeam> getMemberTeams() { return member_teams; }

    public void setName(String name) { this.name = name; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    
    public void removeMemberTeam(MemberTeam memberTeam) { member_teams.remove(memberTeam); }
    public void addMemberTeam(MemberTeam memberTeam) {
        if (member_teams == null) {
            member_teams = new ArrayList<>();
        }
        member_teams.add(memberTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Member m = (Member) o;
        return id == m.id && name.equals(m.name) && birthday.equals(m.birthday) &&
                member_teams.equals(((Member) o).member_teams);
    }
}
