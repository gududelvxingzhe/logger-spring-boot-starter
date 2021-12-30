package com.example.loggerspringbootstarter.AOP;

import com.alibaba.fastjson.JSON;
import com.example.loggerspringbootstarter.config.LogConfigProperties;
import com.example.loggerspringbootstarter.config.LogProfiler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@Aspect
public class LogAfterAspect  implements LogAspect{

	@Resource
	private LogConfigProperties logConfigProperties;
	
	
	@PostConstruct
	public void init(){
		log.info("the log config properties is {}", JSON.toJSONString(logConfigProperties));
	}
	
	@Pointcut("@annotation(com.example.loggerspringbootstarter.config.LogProfiler)")
	public void pointcut(){
	
	}
	
	
	@Around(value = "pointcut()")
	public Object execParamLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		//如果停用打印日志，则直接执行原有代码逻辑，跳过打印日志
		if (!logConfigProperties.getEnable()){
			return joinPoint.proceed();
		}
		
		StringBuilder builder = new StringBuilder();
		try {
			if (log.isInfoEnabled()) {
				this.buildRequestMessage(joinPoint, builder);
			}
			// 执行原有方法逻辑
			Object result = joinPoint.proceed();
			
			// 构建打印出参，此处省略代码
			if (log.isInfoEnabled()) {
				this.buildResponseMessage(joinPoint,result, builder);
			}
			return result;
		} finally {
			// 最终打印日志
			if (log.isInfoEnabled()) {
				log.info(builder.toString());
			}
		}
	}
	
	private void buildResponseMessage(ProceedingJoinPoint joinPoint, Object result, StringBuilder builder) {
		builder.append("    接口返回：[").append(result).append("]");
	}
	
	private void buildRequestMessage(ProceedingJoinPoint joinPoint, StringBuilder builder) throws NoSuchMethodException {

		
		//getSignature());是获取到这样的信息 :修饰符+ 包名+组件名(类名) +方法名
		Signature signature = joinPoint.getSignature();
		//如果只需要方法名
//		String methodName = joinPoint.getSignature().getName()
		MethodSignature methodSignature = (MethodSignature)signature;
		Method method = methodSignature.getMethod();
		LogProfiler logProfiler = method.getAnnotation(LogProfiler.class);
		String value = logProfiler.value();
		String[] parameterNames = methodSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();
		HashMap<String, Object> params = new HashMap<>();
		for (int i = 0; i < parameterNames.length; i++) {
			params.put(parameterNames[i],args[i]);
		}
		builder.append("调用接口：[").append(signature).append( "]    ").append(" 注解名称：[").append(value).append("---").append(this.logConfigProperties.getName()).append("]      ").append( "接口入参：[").append(params.toString()).append("]");
	}
}
