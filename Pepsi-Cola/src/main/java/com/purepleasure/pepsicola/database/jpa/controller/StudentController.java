package com.purepleasure.pepsicola.database.jpa.controller;

import com.purepleasure.pepsicola.database.jpa.entity.Student;
import com.purepleasure.pepsicola.database.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/getByName")
    public Student getByName() {
        return studentRepository.findByName("廖港伟");
    }
}
