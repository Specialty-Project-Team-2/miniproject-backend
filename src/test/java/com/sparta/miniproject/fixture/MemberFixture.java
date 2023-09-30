package com.sparta.miniproject.fixture;

import com.sparta.miniproject.entity.Member;

public class MemberFixture {
    public static Member caseWhoLoggedIn() {
        Member entity = new Member();
        entity.setId(1L);
        entity.setEmail("iksadnorth@gmail.com");
        entity.setNickname("iksad");
        entity.setPassword("1234");
        return entity;
    }

    public static Member caseWhoAccessIllegally() {
        Member entity = new Member();
        entity.setId(2L);
        entity.setEmail("iksadsouth@gmail.com");
        entity.setNickname("south");
        entity.setPassword("q1w2e3r4");
        return entity;
    }
}
