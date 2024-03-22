package com.wdd.studentmanager.interceptors;

import com.wdd.studentmanager.domain.Admin;
import com.wdd.studentmanager.domain.Student;
import com.wdd.studentmanager.domain.Teacher;
import com.wdd.studentmanager.domain.Teacherhead;
import com.wdd.studentmanager.util.Const;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Admin user = (Admin)request.getSession().getAttribute(Const.ADMIN);
        Teacher teacher = (Teacher)request.getSession().getAttribute(Const.TEACHER);
        Teacherhead teacherhead = (Teacherhead) request.getSession().getAttribute(Const.TEACHERHEAD);
        Student student = (Student)request.getSession().getAttribute(Const.STUDENT);
        if(!StringUtils.isEmpty(user) || !StringUtils.isEmpty(teacher) || !StringUtils.isEmpty(student) || !StringUtils.isEmpty(teacherhead)){
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/system/login");
        return false;
    }

}
