package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.Student;
import com.wdd.studentmanager.util.PageBean;

import java.util.List;
import java.util.Map;


public interface StudentService {
    PageBean<Student> queryPage(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    Student findById(Integer sid);

    int editStudent(Student student);

    Student findByStudent(Student student);

    boolean isStudentByClazzId(Integer next);

    int editPswdByStudent(Student student);

    int findByName(String username);
}
