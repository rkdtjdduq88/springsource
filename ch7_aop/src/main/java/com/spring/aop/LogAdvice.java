package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// 횡단관심사(불필요한 로그 등등)를 처리해 줄 클래스 
// 횡단관심사를 언제 처리할 것인가?
// Before(메소드 호출 전에), After returning(메소드 호출 이후에), After throwing(메소드 예외 발생 후)
// After(메소드 호출 후 예외 발생 여부와 관계없이), Around(메소드 호출 이전과 이후 모두 적용)

@Component("log")
@Aspect //이 클래스에서 aop를 실행한다는 어노테이션
public class LogAdvice {
	// execution() : 표현식 : 특정 메소드만 advice 를 함
	// * : 리턴 타입
	// com.spring.aop : 패키지명
	// Product : 클래스명
	// getInfo() : 메소드명	
//	@Before(value = "execution(* com.spring.aop.Product.getInfo())")  // 언제 aop가 실행될지를 정해주는 코드
//	public void beforeLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
//	}
	
//	@After(value = "execution(* com.spring.aop.Product.getInfo())")  // 언제 aop가 실행될지를 정해주는 코드
//	public void afterLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출(예외 발생 여부와 관계없이 무조건 실행");
//	}
	
//	@AfterThrowing(value = "execution(* com.spring.aop.Product.getInfo())")  // 언제 aop가 실행될지를 정해주는 코드
//	public void afterThrowingLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 예외 발생 시 호출");
//	}
	
//	@AfterReturning(value = "execution(* com.spring.aop.Product.getInfo())")  // 언제 aop가 실행될지를 정해주는 코드
//	public void afterReturnLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 예외발생 없을 때만 호출");
//	}
	
	@Around(value = "execution(* com.spring.aop.Product.getInfo())")  // 언제 aop가 실행될지를 정해주는 코드
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출 됨");
		try {
			pjp.proceed();
		} catch (Throwable e) {			
			e.printStackTrace();
		}
		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출 됨");
		
	}
}
