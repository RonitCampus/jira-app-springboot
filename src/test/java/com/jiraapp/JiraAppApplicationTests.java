package com.jiraapp;

import com.jiraapp.repository.JiraRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JiraAppApplicationTests {

	@Test
	public void contextLoads() throws SQLException
    {
        JiraRepository jiraRepository =new JiraRepository();

        System.out.println(jiraRepository.getParentStatusFromChild("10068"));


	}

}
