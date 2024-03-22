package com.wdd.studentmanager.service.Impl;

import com.wdd.studentmanager.domain.Teacherhead;
import com.wdd.studentmanager.mapper.TeacherheadMapper;
import com.wdd.studentmanager.service.TeacherheadService;
import com.wdd.studentmanager.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class TeacherheadServiceImpl implements TeacherheadService {
    @Autowired
    private TeacherheadMapper teacherheadMapper;

    @Override
    public PageBean<Teacherhead> queryPage(Map<String, Object> paramMap) {
        PageBean<Teacherhead> pageBean = new PageBean<>((Integer) paramMap.get("pageno"),(Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex",startIndex);
        List<Teacherhead> datas = teacherheadMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = teacherheadMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int deleteTeacherhead(List<Integer> ids) {
        return teacherheadMapper.deleteTeacherhead(ids);
    }

    @Override
    public int addTeacherhead(Teacherhead teacherhead) {
        return teacherheadMapper.addTeacherhead(teacherhead);
    }

    @Override
    public Teacherhead findById(Integer thid) {
        return teacherheadMapper.findById(thid);
    }

    @Override
    public int editTeacherhead(Teacherhead teacherhead) {
        return teacherheadMapper.editTeacherhead(teacherhead);
    }

    @Override
    public Teacherhead findByTeacherhead(Teacherhead teacherhead) {
        return teacherheadMapper.findByTeacherhead(teacherhead);
    }

    @Override
    public int editPswdByTeacherhead(Teacherhead teacherhead) {
        return teacherheadMapper.editPswdByTeacherhead(teacherhead);
    }
}
