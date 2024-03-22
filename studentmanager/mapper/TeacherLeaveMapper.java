package com.wdd.studentmanager.mapper;

import com.wdd.studentmanager.domain.TeacherLeave;

import java.util.List;
import java.util.Map;

public interface TeacherLeaveMapper {
    List<TeacherLeave> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addLeave(TeacherLeave teacherLeave);

    int editLeave(TeacherLeave teacherLeave);

    int checkLeave(TeacherLeave teacherLeave);

    int deleteLeave(Integer id);
}
