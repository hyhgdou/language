package com.haizhi.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.haizhi.pojo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

//文件上传接口
@RestController
@RequestMapping("/files")
public class FileController {
    // 文件上传存储路径
    private static final String filePath = System.getProperty("user.dir") + "/file/";//项目的根目录
    private static final String http = "http://59.110.229.154:9090/files/";

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {//接收一个文件参数
        synchronized (FileController.class) {
            // 获取当前时间戳--文件标识：每个文件都有一个时间戳，因此文件名称不会重复
            String flag = System.currentTimeMillis() + "";
            // 获取原始文件名（上传的文件本身的名字）
            String fileName = file.getOriginalFilename();
            try {
                // 如果没有file文件夹，则会在项目根目录下创建一个file文件夹，用来保存上传的文件
                if (!FileUtil.isDirectory(filePath)) {
                    FileUtil.mkdir(filePath);
                }
                // 文件存储形式：时间戳-文件名
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--上传成功");
                Thread.sleep(1L);
            } catch (Exception e) {
                System.err.println(fileName + "--文件上传失败");
            }
            return Result.success(http + flag + "-" + fileName);
        }
    }


    /**
     * 获取文件
     */
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response) {
        if (!FileUtil.isDirectory(filePath)) {
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);//拿到所有文件后，遍历
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isNotEmpty(avatar)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(avatar, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }
    }
}
