package com.haizhi.controller;



import com.haizhi.pojo.User;
import com.haizhi.pojo.Result;
import com.haizhi.service.IUserService;
import com.haizhi.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody User user, HttpServletResponse res) throws AccountLockedException, AccountNotFoundException {
        log.info("用户登录: {}", user);
        String p = user.getPassword();
        String password = DigestUtils.md5DigestAsHex(p.getBytes());
        user.setPassword(password);
        User u = userService.login(user);
        if (u ==null || u.getStatus()==0){
            return Result.error("用户名或密码错误");
        }else {
           /* String p = user.getPassword();
            String password = DigestUtils.md5DigestAsHex(p.getBytes());*/
         //   user.setPassword(password);
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("account", u.getAccount());
            claims.put("username", u.getUsername());

            String jwt = JwtUtil.generateJwt(claims); //jwt包含了当前登录的员工信息

             res.setHeader("Access-Control-Allow-Origin", "*");

            return Result.success(jwt);


    }}

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "用户退出")
    public Result<String> logout() {
        return Result.success();
    }
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Result save(@RequestBody User user){
        log.info("用户,user:{}",user);
        userService.save(user);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "用户回显")
    public Result getById(@PathVariable Integer id){
        log.info("查寻用户，id:{}",id);
        User user =userService.getById(id);
        return Result.success(user);
    }

}

