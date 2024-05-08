package org.spring.Team_project_1.member;

import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Page<MemberEntity> findByEmailContains(Pageable pageable, String search);

//    Page<MemberEntity> findByNameContains(Pageable pageable, String search);

    Page<MemberEntity> findByAddressContains(Pageable pageable, String search);

    Optional<MemberEntity> findByEmail(String email);

//    Page<MemberEntity> findByRoleContains(Pageable pageable, Role role);

//    Page<MemberEntity> findByRoleContains(Pageable pageable, Role role);

    Page<MemberEntity> findByRole(Pageable pageable, Role role);

    Page<MemberEntity> findByNameContains(Pageable pageable, String search);
}
