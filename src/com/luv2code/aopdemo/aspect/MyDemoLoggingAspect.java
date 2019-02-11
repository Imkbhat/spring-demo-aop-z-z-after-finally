package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	
	//after finally
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint thePoint) {
		//print which method
			String method = thePoint.getSignature().toShortString();
			System.out.println(">>>>>>>>>>>>Method name @@@@@ "+method);
	}
	
	//After throwing aspect
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
			throwing="theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint thePoint, Throwable theExc) {
		
		//print which method
		String method = thePoint.getSignature().toShortString();
		System.out.println(">>>>>>>>>>>>Method name"+method);
		
		//log the exception
		System.out.println("\n Exception is"+theExc);
	}
	
	
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.combinedGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinpoints) {
		System.out.println("\n=======>>> Excecuting @Before  Advice on method()");
		
		//display signature
		MethodSignature methodSig = (MethodSignature)theJoinpoints.getSignature();
		System.out.println("Method======>"+methodSig);
	
	//display method arguments
		
		// get args
		Object[] args = theJoinpoints.getArgs();

		//loop thru args
		for (Object argus :  args) {
			System.out.println(argus);
			if (argus instanceof Account) {
				//downcast and print specific stuff
				Account theAccount = (Account) argus;
				
				System.out.println("Acount name:"+theAccount.getName());
				System.out.println("Acount level:"+theAccount.getLevel());
			}
		}
	
	}	
}
