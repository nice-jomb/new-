package com.wdd.studentmanager.service.Impl;

import com.wdd.studentmanager.domain.Admin;
import com.wdd.studentmanager.mapper.AdminMapper;
import com.wdd.studentmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByAdmin(Admin admin) {
        return adminMapper.findByAdmin(admin);
    }

    @Override
    public int editPswdByAdmin(Admin admin) {
        return adminMapper.editPswdByAdmin(admin);
    }

}
