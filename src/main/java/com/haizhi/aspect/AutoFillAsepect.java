package com.haizhi.aspect;


import com.haizhi.annotation.AutoFill;
import com.haizhi.constant.AutoFillConstant;
import com.haizhi.context.BaseContext;
import com.haizhi.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

//自定义切面 实现公共字段自动填充处理逻辑
@Aspect
@Component
@Slf4j
public class AutoFillAsepect {
    @Pointcut("execution(* com.haizhi.mapper.*.*(..))&& @annotation(com.haizhi.annotation.AutoFill)")
    public void  autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint){
        log.info("开始进行公共字段自动填充。。");
        //获取到当前被拦截的方法上的数据库操作类型
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();//方法签名对象
        AutoFill autoFill=signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获得数据库操作类型
        //获取到当前被拦截的方法的参数--实体对象
        Object[] args = joinPoint.getArgs();
        if (args==null||args.length==0){
            return;
        }
        Object entity=args[0];
        //准备赋值的数据
        LocalDateTime now =LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        //通过不同的操作类型 为对应属性通过反射来赋值
        if (operationType==OperationType.INSERT){
            try {
                Method setCreateTime= entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME,LocalDateTime.class);

                Method setUpdateTime= entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);

             //通过反射为对象属性赋值
            setCreateTime.invoke(entity,now);

            setUpdateTime.invoke(entity,now);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType==OperationType.UPDATE) {
            //为两个公共字段赋值

            try {
                Method setUpdateTime= entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);

                setUpdateTime.invoke(entity,now);


            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
