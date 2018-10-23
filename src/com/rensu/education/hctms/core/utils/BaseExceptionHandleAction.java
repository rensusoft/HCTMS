package com.rensu.education.hctms.core.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


public class BaseExceptionHandleAction {

    
    /** 基于@ExceptionHandler异常处理 */
    /*@ExceptionHandler
    public ModelAndView  handleAndReturnPage(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        ModelAndView  mv = new ModelAndView("Exception") ;
        mv.addObject("ex", ex);

        // 根据不同错误转向不同页面
        if (ex instanceof BusinessException) {
            return mv;
        } else {
            return mv; //返回Exception.jsp页面
        }
    }*/

    /** 基于@ExceptionHandler异常处理 */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handleAndReturnData(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	
        Map<String, Object> data = new HashMap<String, Object>();
        if(ex instanceof BusinessException) {
            BusinessException e = (BusinessException)ex;
            //data.put("code", e.getCode());
            data.put("success", false);
            data.put("data", "错误代码："+e.getCode()+"<br/>错误信息："+e.getMessage());
        }
        
        return data;
    }

}