package com.wdd.studentmanager.mapper;


import com.wdd.studentmanager.domain.Teacherhead;

import java.util.List;
import java.util.Map;

public interface TeacherheadMapper {
    List<Teacherhead> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int deleteTeacherhead(List<Integer> ids);

    int addTeacherhead(Teacherhead teacherhead);

    Teacherhead findById(Integer thid);

    int editTeacherhead(Teacherhead teacherhead);

    Teacherhead findByTeacherhead(Teacherhead teacherhead);

    int editPswdByTeacherhead(Teacherhead teacherhead);
}
