package com.sparta.miniproject.comment;

import com.sparta.miniproject.company.Company;
import com.sparta.miniproject.member.Member;

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
}
