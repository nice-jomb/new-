package com.wdd.studentmanager.mapper;

import com.wdd.studentmanager.domain.Admin;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminMapper {

    Admin findByAdmin(Admin admin);


    int editPswdByAdmin(Admin admin);
}
