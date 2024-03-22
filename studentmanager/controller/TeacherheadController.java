package com.wdd.studentmanager.controller;

import com.wdd.studentmanager.domain.Teacherhead;
import com.wdd.studentmanager.service.TeacherheadService;
import com.wdd.studentmanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/teacherhead")
public class TeacherheadController {
    @Autowired
    private TeacherheadService teacherheadService;


    @RequestMapping("/teacherhead_list")
    public String teacherheadList(){
        return "teacherhead/teacherheadList";
    }

    /**
     * 异步加载主任数据列表
     * @param page
     * @param rows
     * @param teacherheadName
     * @param clazzid
     * @param from
     * @param session
     * @return
     */
    @PostMapping("/getTeacherheadList")
    @ResponseBody
    public Object getTeacherheadList(@RequestParam(value = "page", defaultValue = "1")Integer page,
                                     @RequestParam(value = "rows", defaultValue = "100")Integer rows,
                                     String teacherheadName,
                                     @RequestParam(value = "clazzid", defaultValue = "0")String clazzid, String from, HttpSession session){
        Map<String,Object> paramMap = new HashMap();
        paramMap.put("pageno",page);
        paramMap.put("pagesize",rows);
        if(!StringUtils.isEmpty(teacherheadName))  paramMap.put("username",teacherheadName);
        if(!clazzid.equals("0"))  paramMap.put("clazzid",clazzid);

        //判断是老师还是学生权限
        Teacherhead teacherhead = (Teacherhead) session.getAttribute(Const.TEACHERHEAD);
        if(!StringUtils.isEmpty(teacherhead)){
            //是老师权限，只能查询自己的信息
            paramMap.put("teacherheadid",teacherhead.getId());
        }

        PageBean<Teacherhead> pageBean = teacherheadService.queryPage(paramMap);
        if(!StringUtils.isEmpty(from) && from.equals("combox")){
            return pageBean.getDatas();
        }else{
            Map<String,Object> result = new HashMap();
            result.put("total",pageBean.getTotalsize());
            result.put("rows",pageBean.getDatas());
            return result;
        }
    }

    /**
     * 删除教师
     * @param data
     * @return
     */
    @PostMapping("/deleteTeacherhead")
    @ResponseBody
    public AjaxResult deleteTeacherhead(Data data){
        AjaxResult ajaxResult = new AjaxResult();
        try {
            File fileDir = UploadUtil.getImgDirFile();
            for(Integer id : data.getIds()){
                Teacherhead byId = teacherheadService.findById(id);
                if(!byId.getPhoto().isEmpty()){
                    File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                    if(file != null){
                        file.delete();
                    }
                }
            }
            int count = teacherheadService.deleteTeacherhead(data.getIds());
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("删除成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("删除失败");
        }
        return ajaxResult;
    }

    /**
     * 添加教师
     * @param files
     * @param teacherhead
     * @return
     * @throws IOException
     */
    @RequestMapping("/addTeacherhead")
    @ResponseBody
    public AjaxResult addTeacherhead(@RequestParam("file") MultipartFile[] files, Teacherhead teacherhead) throws IOException {

        AjaxResult ajaxResult = new AjaxResult();
        teacherhead.setSn(SnGenerateUtil.generateTeacherheadSn(teacherhead.getClazzId()));

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for(MultipartFile fileImg : files){

            // 拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator +uuidName+ extName);

                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            teacherhead.setPhoto(uuidName+extName);
        }
        //保存学生信息到数据库
        try{
            int count = teacherheadService.addTeacherhead(teacherhead);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("保存成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("保存失败");
        }

        ajaxResult.setSuccess(true);
        return ajaxResult;
    }

    @PostMapping("/editTeacherhead")
    @ResponseBody
    public AjaxResult editTeacherhead(@RequestParam("file") MultipartFile[] files,Teacherhead teacherhead){
        AjaxResult ajaxResult = new AjaxResult();

        // 存放上传图片的文件夹
        File fileDir = UploadUtil.getImgDirFile();
        for(MultipartFile fileImg : files){

            String name = fileImg.getOriginalFilename();
            if(name.equals("")){
                break;
            }

            // 拿到文件名
            String extName = fileImg.getOriginalFilename().substring(fileImg.getOriginalFilename().lastIndexOf("."));
            String uuidName = UUID.randomUUID().toString();

            try {
                // 构建真实的文件路径
                File newFile = new File(fileDir.getAbsolutePath() + File.separator +uuidName+ extName);
                // 上传图片到 -》 “绝对路径”
                fileImg.transferTo(newFile);

                Teacherhead byId = teacherheadService.findById(teacherhead.getId());
                File file = new File(fileDir.getAbsolutePath() + File.separator + byId.getPhoto());
                if(file != null){
                    file.delete();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            teacherhead.setPhoto(uuidName+extName);
        }

        try{
            int count = teacherheadService.editTeacherhead(teacherhead);
            if(count > 0){
                ajaxResult.setSuccess(true);
                ajaxResult.setMessage("修改成功");
            }else{
                ajaxResult.setSuccess(false);
                ajaxResult.setMessage("修改失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage("修改失败");
        }
        return ajaxResult;
    }
}
