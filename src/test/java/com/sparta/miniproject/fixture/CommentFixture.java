package com.sparta.miniproject.fixture;

import com.sparta.miniproject.entity.Comment;
import com.sparta.miniproject.entity.Company;
import com.sparta.miniproject.entity.Member;

import java.util.List;

public class CommentFixture {
    public static Comment caseWhichMadeFirst(Company company, Member member) {
        Comment entity = new Comment();
        entity.setId(1L);
        entity.setComment("mock comment1");
        entity.setCompany(company);
        entity.setMember(member);
        return entity;
    }

    public static Comment caseWhichMadeSecond(Company company, Member member) {
        Comment entity = new Comment();
        entity.setId(2L);
        entity.setComment("mock comment2");
        entity.setCompany(company);
        entity.setMember(member);
        return entity;
    }

    public static List<Comment> caseList() {
        Company company1 = CompanyFixture.caseWhichRegisteredAtFirst();
        Company company2 = CompanyFixture.caseWhichRegisteredAtSecond();
        Member member1 = MemberFixture.caseWhoLoggedIn();
        Member member2 = MemberFixture.caseWhoAccessIllegally();

        return List.of(
                caseWhichMadeFirst(company1, member1),
                caseWhichMadeSecond(company1, member2),
                caseWhichMadeFirst(company2, member1),
                caseWhichMadeSecond(company2, member2)
        );
    }
}
