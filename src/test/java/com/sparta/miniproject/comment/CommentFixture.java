package com.sparta.miniproject.comment;

import com.sparta.miniproject.entity.Comment;
import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.company.CompanyFixture;
import com.sparta.miniproject.member.MemberFixture;
import com.sparta.miniproject.entity.Member;

import java.util.List;

public class CommentFixture {
    public static Comment case1(Company company, Member member) {
        Comment entity = new Comment();
        entity.setId(1L);
        entity.setComment("mock comment1");
        entity.setCompany(company);
        entity.setMember(member);
        return entity;
    }

    public static Comment case2(Company company, Member member) {
        Comment entity = new Comment();
        entity.setId(2L);
        entity.setComment("mock comment2");
        entity.setCompany(company);
        entity.setMember(member);
        return entity;
    }

    public static List<Comment> caseList1() {
        Company company1 = CompanyFixture.case1();
        Company company2 = CompanyFixture.case2();
        Member member1 = MemberFixture.case1();
        Member member2 = MemberFixture.case2();

        return List.of(
                case1(company1, member1),
                case2(company1, member2),
                case1(company2, member1),
                case2(company2, member2)
        );
    }
}
