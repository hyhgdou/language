package com.haizhi.controller;


import com.haizhi.pojo.Word;
import com.haizhi.pojo.Xiaomin;
import com.haizhi.service.IUserService;
import com.haizhi.service.WordService;
import com.haizhi.service.XiaominService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/xiaomin")
//@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "xm相关接口")
public class xiaominController {
    @Autowired
    private XiaominService xiaominService;


    @GetMapping("/wanan")
    @ApiOperation(value = "生成随机xm")
    public Xiaomin getRandomVocabulary() {
        return xiaominService.getRandomXiaomin();
    }




    @GetMapping("/zuishuai")
    @ApiOperation(value = "生成www")
    public ResponseEntity<Xiaomin> getXm(){
        Xiaomin id = xiaominService.getById(1L);
       if (id!=null){
           return ResponseEntity.ok(id);
       }else {
           return ResponseEntity.notFound().build();
       }
    }

}
