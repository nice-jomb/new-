package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.Teacherhead;
import com.wdd.studentmanager.util.PageBean;

import java.util.List;
import java.util.Map;

public interface TeacherheadService {
    PageBean<Teacherhead> queryPage(Map<String, Object> paramMap);

    int deleteTeacherhead(List<Integer> ids);

    int addTeacherhead(Teacherhead teacherhead);

    Teacherhead findById(Integer thid);

    int editTeacherhead(Teacherhead teacherhead);

    Teacherhead findByTeacherhead(Teacherhead teacherhead);

    int editPswdByTeacherhead(Teacherhead teacherhead);
}
