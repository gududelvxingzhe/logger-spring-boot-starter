package com.example.loggerspringbootstarter.AOP;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.loggerspringbootstarter.config.LogConfigProperties;
import com.example.loggerspringbootstarter.config.LogProfiler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.configurationprocessor.json.JSONStringer;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@Aspect
@Component
public class LogAroundAspect implements LogAspect {

	@Setter
	@Resource
	private LogConfigProperties logConfigProperties;
	private static final ParameterNameDiscoverer PND = new DefaultParameterNameDiscoverer();
	
	@PostConstruct
	public void init(){
		log.info("the log config properties is {}", JSON.toJSONString(logConfigProperties));
	}
	
	/**
	 * 要解析的注解
	 */
	@Pointcut("@annotation(com.example.loggerspringbootstarter.config.LogProfiler)")
	public void pointcut(){
	
	}
	
	@Around(value = "pointcut()")
	public Object execParamLogAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		//如果停用日志打印，则直接执行原有代码，跳过日志打印
		if (!logConfigProperties.getEnable()){
			joinPoint.proceed();
		}
		StringBuilder builder = new StringBuilder();
		//入参内容,此处省略代码
		if(log.isInfoEnabled()){
			
			this.buildRequestMessage(joinPoint, builder);
		}
		Object result = joinPoint.proceed();
		
		//打印出参，此处省略代码
		if (log.isInfoEnabled()){
			this.buildResponseMessage(joinPoint, result, builder);
		}
		log.info(builder.toString());
		return result;
	}
	
	private void buildResponseMessage(ProceedingJoinPoint joinPoint, Object result, StringBuilder builder) {
		
		builder.append("    接口返回：").append(JSON.toJSON(result));
	}
	
	private void buildRequestMessage(ProceedingJoinPoint joinPoint, StringBuilder builder) throws NoSuchMethodException {
		//获取类的字节码对象，通过字节码对象获取方法信息
//		Class<?> targetCls=joinPoint.getTarget().getClass();
		//获取方法签名(通过此签名获取目标方法信息)
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//获取目标方法上的注解指定的操作名称
//		Method method = targetCls.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
//		LogProfiler logProfiler = method.getAnnotation(LogProfiler.class);
//		String value = logProfiler.value();
		
		//获取目标方法名(目标类型+方法名)
		
//		String targetObjectMethodName = targetCls.getName()+"."+ method.getName();
//		Object【】 args = joinPoint.getArgs();
//		String【】 parameterNames = signature.getParameterNames();
//		HashMap<String, Object> params = new HashMap<>();
//		for (int i = 0; i < parameterNames.length; i++) {
//			params.put(parameterNames【i】,args【i】);
//		}
		
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
		builder.append("调用接口：").append(signature).append( "    ").append(" 注解名称：").append(value).append("---").append(this.logConfigProperties.getName()).append("      ").append( "接口入参：").append(JSON.toJSON(params));
	}
}
