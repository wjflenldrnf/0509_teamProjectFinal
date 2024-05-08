package org.spring.Team_project_1.config;


import lombok.RequiredArgsConstructor;
import org.spring.Team_project_1.entity.MemberEntity;
import org.spring.Team_project_1.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> {
            throw new IllegalArgumentException("회원없음");
        });

        return new MyUserDetails(memberEntity);
    }

}
