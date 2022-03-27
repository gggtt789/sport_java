package com.sport.sport.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Member_team", schema = "public")
public class MemberTeam {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "joined_at") private Date joined_at;
    @Column(name = "left_at") private Date left_at;
    @Column(name = "role") private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Long getId() { return id; }
    public Date getJoinedAt() { return joined_at; }
    public Date getLeftAt() { return left_at; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setJoinedAt(Date joined_at) { this.joined_at = joined_at; }
    public void setLeftAt(Date left_at) { this.left_at = left_at; }
    public void setRole(String role) { this.role = role; }

    public Member getMember() { return member; }
    public Team getTeam() { return team; }

    public void setMember(Member member) { this.member = member;}
    public void setTeam(Team team) { this.team = team; }

    public MemberTeam() {}
    public MemberTeam(Long id, Date joined_at, Date left_at, String role) {
        this.id = id;
        this.joined_at = joined_at;
        this.left_at = left_at;
        this.role = role;
    }
    public MemberTeam(Date joined_at, Date left_at, String role) {
        this.joined_at = joined_at;
        this.left_at = left_at;
        this.role = role;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        MemberTeam m = (MemberTeam) o;
        return id.equals(m.id) && member.equals(m.member) && team.equals(m.team) &&
                joined_at.equals(m.joined_at) && left_at.equals(m.left_at) &&
                role.equals(m.role);
    }
}
