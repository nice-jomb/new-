package com.wdd.studentmanager.mapper;

import com.wdd.studentmanager.domain.Leave;

import java.util.List;
import java.util.Map;


public interface LeaveMapper {
    List<Leave> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int addLeave(Leave leave);

    int editLeave(Leave leave);

    int checkLeave(Leave leave);

    int deleteLeave(Integer id);
}
