package com.qmmt.edu.controllers.interceptor;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SpringMethodInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		//System.out.println("���ؿ�ʼ��");
	
		
		Annotation[] ann = invocation.getMethod().getAnnotations();
		for(int i=0;i<ann.length;i++){
			// System.out.println(ann[i].annotationType().getName());
			// System.out.println(ann[i].getClass().getName());

//			 System.out.println(invocation.getMethod().isAnnotationPresent(AuthorUser.class));
//			 if(ann[i].annotationType().getName().equals("com.mytest.springmvc.AuthorUser")){
//				 AuthorUser ua = invocation.getMethod().getAnnotation(AuthorUser.class);
//				 System.out.println("ua==="+ua.role());
//			 }
			 
			
//			 if ( ann[i].getClass().equals(getClass())annotationType().isAnnotationPresent(AuthorUser.class)) {  
//				 AuthorUser anno = (AuthorUser)ann[i].annotationType().getAnnotation(AuthorUser.class);  
//				 System.out.println(anno.role());
//			} 
		}

		
		Object[] args = invocation.getArguments();    
        HttpServletRequest request = null;  
        HttpServletResponse response = null;  
//        ActionMapping  mapping = null;  
//        for (int i = 0 ; i < args.length ; i++ )    {  
//          if (args[i] instanceof HttpServletRequest) request = (HttpServletRequest)args[i];     
//          if (args[i] instanceof HttpServletResponse) response = (HttpServletResponse)args[i];     
//          if (args[i] instanceof ActionMapping) mapping = (ActionMapping)args[i];     
//        }  
       // System.out.println("���ؽ���");
        
        return invocation.proceed();
       // return null;
	}

}
