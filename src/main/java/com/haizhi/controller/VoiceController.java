package com.haizhi.controller;

import com.haizhi.service.VoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/voice")
@Slf4j
@Api(tags = "语音相关接口")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class VoiceController {

    private final String filePath = "/haizhi/laji/";

@Autowired
private VoiceService voiceService;

    @PostMapping
    @ApiOperation(value = "语音评分")
    public ResponseEntity<String> processVoice(@RequestParam("file") MultipartFile file) {
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info(file.toString());
            //原始文件名
            String originalFilename = file.getOriginalFilename();//abc.jpg
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg
            //创建一个目录对象
            File dir = new File(filePath);
            //判断当前目录是否存在
            if(!dir.exists()){
                //目录不存在，需要创建
                dir.mkdirs();
            }
            try {
            //将临时文件转存到指定位置
            file.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save voice file.");
        }
        // 进行语音处理和评估
        float score = voiceService.processAndEvaluateVoice(filePath);
        // 返回评分结果
        return ResponseEntity.ok("宝宝的成绩为: " + score);
    }


}
