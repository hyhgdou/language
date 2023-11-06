package com.haizhi.controller;

import com.haizhi.service.IUserService;
import com.haizhi.service.VoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/voice")
@Slf4j
@Api(tags = "语音相关接口")
public class VoiceController {

@Autowired
private VoiceService voiceService;

    @PostMapping
    @ApiOperation(value = "保存用户上传的语音文件到指定位置")
    public ResponseEntity<String> processVoice(@RequestParam("file") MultipartFile file) {
        String filePath = "C:/Users/hyhjames/Desktop/file";//"path/to/save/voice/file"
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save voice file.");
        }

        // 进行语音处理和评估
        float score = voiceService.processAndEvaluateVoice(filePath);

        // 返回评分结果
        return ResponseEntity.ok("Score: " + score);
    }


}
