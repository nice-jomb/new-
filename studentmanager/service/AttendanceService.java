package com.wdd.studentmanager.service;

import com.wdd.studentmanager.domain.Attendance;
import com.wdd.studentmanager.util.PageBean;

import java.util.Map;


public interface AttendanceService {
    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean isAttendance(Attendance attendance);

    int addAtendance(Attendance attendance);

    int deleteAttendance(Integer id);
}
