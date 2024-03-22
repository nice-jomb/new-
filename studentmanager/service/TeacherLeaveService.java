package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.TeacherLeave;
import com.wdd.studentmanager.util.PageBean;

import java.util.Map;

public interface TeacherLeaveService {
    PageBean<TeacherLeave> queryPage(Map<String, Object> paramMap);

    int addLeave(TeacherLeave teacherLeave);

    int editLeave(TeacherLeave teacherLeave);

    int checkLeave(TeacherLeave teacherLeave);

    int deleteLeave(Integer id);
}
