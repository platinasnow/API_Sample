package org.api.sample;

import org.api.sample.model.Members;
import org.api.sample.service.MemberService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class MembersRepoTest {

    @Autowired
    private MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MembersRepoTest.class);

    @Test
    public void findMemberList(){
        List<Members> list = memberService.findAll();
        logger.info("members ===> {}", list);
        then(list.size()).isEqualTo(2);
    }

    @Test
    public void findMemberItem(){
        Members item = memberService.findById("test");
        logger.info("members ===> {}", item);
        then(item).isNotNull();


        Members emptyItem = memberService.findById("test123");
        logger.info("members ===> {}", emptyItem);
        then(emptyItem).isNull();

    }



}
