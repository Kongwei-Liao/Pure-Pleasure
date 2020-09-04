package com.purepleasure.pepsicola.database.jpa;

import com.purepleasure.pepsicola.Application;
import com.purepleasure.pepsicola.database.jpa.entity.Student;
import com.purepleasure.pepsicola.database.jpa.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class JpaTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testQueryStudent() {
        Student student = studentRepository.findByName("廖港伟");
        System.out.println(student.getName());
    }

}
