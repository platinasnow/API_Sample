package org.api.sample;

import org.api.sample.model.Members;
import org.api.sample.service.MemberService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest(properties = {"classpath:test-application.yml"})
@Sql(scripts = {"classpath:test-data.sql"})
public class MembersRepoTest {

    @Autowired
    private MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MembersRepoTest.class);

    @Test
    public void findMemberList(){
        List<Members> list = memberService.findAll();
        logger.info("members ===> {}", list);
        assert (list.size() == 2);
    }

    @Test
    public void findMemberItem(){
        Members item = memberService.findById("test");
        logger.info("members ===> {}", item);
        assert (item != null);

        Members emptyItem = memberService.findById("test123");
        logger.info("members ===> {}", emptyItem);
        assert (emptyItem == null);
    }



}
