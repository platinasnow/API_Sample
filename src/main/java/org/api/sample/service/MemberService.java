package org.api.sample.service;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.Members;
import org.api.sample.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Members findById(String id){
        return memberRepository.findById(id).orElse(null);
    }

    public List<Members> findAll(){
        return memberRepository.findAll();
    }


}
