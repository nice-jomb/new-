package com.wdd.studentmanager.service.Impl;

import com.wdd.studentmanager.domain.TeacherLeave;
import com.wdd.studentmanager.mapper.TeacherLeaveMapper;
import com.wdd.studentmanager.service.TeacherLeaveService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherLeaveServiceImpl implements TeacherLeaveService {
    @Autowired
    private TeacherLeaveMapper teacherLeaveMapper;


    @Override
    public PageBean<TeacherLeave> queryPage(Map<String, Object> paramMap) {
        PageBean<TeacherLeave> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));
//
        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<TeacherLeave> datas = teacherLeaveMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = teacherLeaveMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addLeave(TeacherLeave teacherLeave) {
        return   teacherLeaveMapper.addLeave(teacherLeave);
    }


    @Override
    public int editLeave(TeacherLeave teacherLeave) {
        return teacherLeaveMapper.editLeave(teacherLeave);
    }

    @Override
    public int checkLeave(TeacherLeave   teacherLeave) {

            return teacherLeaveMapper.checkLeave( teacherLeave);
    }

    @Override
    public int deleteLeave(Integer id) {
        return teacherLeaveMapper.deleteLeave(id);
    }


}
