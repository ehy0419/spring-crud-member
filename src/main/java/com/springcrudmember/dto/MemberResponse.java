package com.springcrudmember.dto;

import lombok.Getter;

@Getter
public class MemberResponse {

    private final Long id;
    private final String name;

    public MemberResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
