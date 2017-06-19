package com.sooncode.subassembly.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
@Order(value=2)
public class ParamInterceptAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ParamInterceptAspect.class);
	
	@Pointcut(value="execution(* com.sooncode.subassembly.controller.*.*(..))")
	public void controllerPoint(){}
	
	@Before(value="controllerPoint()")
	public void doBeforeController(JoinPoint joinPoint){
		
		try {
			String controllerName = joinPoint.getSignature().getDeclaringTypeName();
			
			ServletRequestAttributes requestAttributes = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
			HttpServletRequest request = requestAttributes.getRequest();
			String uri = request.getRequestURI();
			String method = request.getMethod();
			String remoteAddr = request.getRemoteAddr();
			Object[] params = joinPoint.getArgs();
            String param = "";
            if ("POST".equals(method)) {
                StringBuffer sb = new StringBuffer();
                if (params != null && params.length >0) {
                    for (int i = 0; i < params.length; i++) {
                        if (params[i].toString().startsWith("{")) {
                            if (i != params.length - 1) {
                                sb.append(params[i].toString());
                                sb.append(",");
                            }else{
                                sb.append(params[i].toString());
                            }
                        }
                    }
                }
                param = sb.toString();
            }else{
                Object attribute = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                param = attribute.toString();
            }
			logger.debug("\n" + "\n" +
					"请求uri--->" + uri + "---controllerName：" + controllerName + "\n" +
					"请求类型--->" + method + "---" + remoteAddr + "\n" +
					"请求参数--->" + param);
		} catch (Exception e) {
			logger.error("******************************* 参数过滤失败  ********************************");
		}
		
		
		
	}
	
	@AfterReturning(value="controllerPoint()",returning="result")
	public void doAfterController(JoinPoint joinPoint,Object result){
		logger.debug("\n" + "\n" + "返回值 --->" + JSON.toJSONString(result) + "\n");
		
	}
	@AfterThrowing(value="controllerPoint()",throwing="e")
	public void doAfterThrowing(JoinPoint joinPoint,Throwable e){
		logger.error("处理失败捕捉到异常--->" + e);
	}
	

}
