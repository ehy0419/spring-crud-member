package com.springcrudmember.repository;

import com.springcrudmember.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public class MemberRepository extends JpaRepository<Member, Long> {
}
