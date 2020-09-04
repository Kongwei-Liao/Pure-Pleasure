package com.purepleasure.pepsicola.database.jpa.repository;

import com.purepleasure.pepsicola.database.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * 根据姓名查用户
     * @param name
     * @return
     */
    Student findByName(String name);

}
