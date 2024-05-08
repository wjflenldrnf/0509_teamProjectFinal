package org.spring.Team_project_1.member;

import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.member.memberServiceImpl.MemberServiceImpl;
import org.spring.Team_project_1.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl {


    private final MemberRepository memberRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertMember(MemberDto memberDto) {

        //insert 방법(1)
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                .email(memberDto.getEmail())
                .userPw(passwordEncoder.encode(memberDto.getUserPw()))
//                .userPw(memberDto.getUserPw())
                .role(Role.MEMBER)
                .name(memberDto.getName())
                .nickName(memberDto.getNickName())
                .gender(memberDto.getGender())
                .address(memberDto.getAddress())
                .note(memberDto.getNote())
                .number(memberDto.getNumber())
                .build());

        memberRepository.save(memberEntity);


    }

    @Override
    public void insertSeller(MemberDto memberDto) {

        //insert 방법(1)
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                .email(memberDto.getEmail())
                .userPw(passwordEncoder.encode(memberDto.getUserPw()))
//                .userPw(memberDto.getUserPw())
                .role(Role.SELLER)
                .name(memberDto.getName())
                .nickName(memberDto.getNickName())
                .gender(memberDto.getGender())
                .address(memberDto.getAddress())
                .note(memberDto.getNote())
                .number(memberDto.getNumber())
                .build());

        memberRepository.save(memberEntity);
    }

    //////////////////////////////////////////////////////////////////////////
    @Override
    public int emailChecked(String email) {
        //nameChecked 방법2
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(email);

        if (optionalMemberEntity.isPresent()) {
            System.out.println("email already exists!!");
            return 0;
        }
        System.out.println("Available email for registration");

        return 1;

    }

    //////////////////////////////////////////////////////////////////////////

    @Override
    public Page<MemberDto> memberSearchPagingList(Pageable pageable, String subject, String search) {
        Page<MemberEntity> memberEntities = null;


        //************************
        if (search == null || subject == null) {    //************************

            memberEntities = memberRepository.findAll(pageable);

        } else {

            if (subject.equals("email")) {
                memberEntities = memberRepository.findByEmailContains(pageable, search);
            }
//            else if (subject.equals("name")) {
//                memberEntities = memberRepository.findByNameContains(pageable, search);
//            }
            else if (subject.equals("role")) {
                memberEntities = memberRepository.findByRole(pageable, Role.valueOf(search));
            }

            else if (subject.equals("address")) {
                memberEntities = memberRepository.findByAddressContains(pageable, search);
            }

            else {
                memberEntities = memberRepository.findAll(pageable);
            }

        }


        Page<MemberDto> memberDtos = memberEntities.map(MemberDto::toMemberDto);

        return memberDtos;
    }

//    @Override
//    public Page<MemberDto> memberSearchPagingList(Pageable pageable, String subject, String search) {
//        Page<MemberEntity> memberEntities = null;
//
//
//        //************************
//        if (search == null || subject == null) {    //************************
//
//            memberEntities = memberRepository.findAll(pageable);
//
//        } else {
//
//            if (subject.equals("email")) {
//                memberEntities = memberRepository.findByEmailContains(pageable, search);
//            }
//            else if (subject.equals("name")) {
//                memberEntities = memberRepository.findByNameContains(pageable, search);
//            }
//            else if (subject.equals("address")) {
//                memberEntities = memberRepository.findByAddressContains(pageable, search);
//            }
//
//            else {
//                memberEntities = memberRepository.findAll(pageable);
//            }
//
//        }
//
//
//        Page<MemberDto> memberDtos = memberEntities.map(MemberDto::toMemberDto);
//
//        return memberDtos;
//    }
    ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public MemberDto selectOne(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        //있으면 get으로 끄집어냄
        if (optionalMemberEntity.isPresent()) {
            //꺼내고 엔티티에 담음
            MemberEntity memberEntity = optionalMemberEntity.get();
            //entity -> dto
            MemberDto memberDto = MemberDto.toMemberDto(memberEntity);
            return memberDto;
        } else {
            throw new IllegalArgumentException("존재하지 x");
            //없으면
//            return null;
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public int memberUpdate(MemberDto memberDto) {
        MemberEntity memberEntity = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("no id"));

        String oldPw = memberEntity.getUserPw();
        //암호화된 비밀번호와 비교, 실제 비밀번호 확인
        //form(update) 비교, 실제 비밀번호(테이블에 저장) 확인
        if (memberDto.getUserPw().equals(oldPw)) {
            //비밀번호가 일치
            MemberEntity memberEntity2 = memberRepository.save(MemberEntity.builder()
                    .id(memberDto.getId())
                    .email(memberDto.getEmail())
//                        .userPw(passwordEncoder.encode(memberDto.getUserPw()))
                    .userPw(memberDto.getUserPw())
                    .role(Role.MEMBER)
                    .name(memberDto.getName())
                    .nickName(memberDto.getNickName())
                    .gender(memberDto.getGender())
                    .address(memberDto.getAddress())
                    .note(memberDto.getNote())
                    .number(memberDto.getNumber())
                    .build());
            memberRepository.save(memberEntity2);
        } else {
            // 새로운 비밀번호 111 -> asdfasdf 암호화
            String newPw = passwordEncoder.encode(memberDto.getUserPw());
            //비밀번호가 불일치
            memberDto.setUserPw(newPw); //암호화된 비밀번호를 memberDto 추가 새로운 비밀번호 111-> asdfasdf
            MemberEntity memberEntity2 = memberRepository.save(MemberEntity.builder()
                    .id(memberDto.getId())
                    .email(memberDto.getEmail())
//                        .userPw(passwordEncoder.encode(memberDto.getUserPw()))
                    .userPw(memberDto.getUserPw())
                    .role(Role.MEMBER)
                    .name(memberDto.getName())
                    .nickName(memberDto.getNickName())
                    .gender(memberDto.getGender())
                    .address(memberDto.getAddress())
                    .note(memberDto.getNote())
                    .number(memberDto.getNumber())
                    .build());
            memberRepository.save(memberEntity2);
        }
        return 0;
    }


    ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public void memberDelete(Long id) {

        memberRepository.findById(id).orElseThrow(() -> {

            throw new RuntimeException("no id");
        });

        memberRepository.deleteById(id);

    }


}