package com.rensu.education.hctms.teach.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.teach.service.StuExerKnowlwdgeService;
import com.rensu.education.hctms.teach.service.StuExerQuestionService;
import com.rensu.education.hctms.teach.service.StuExercisesService;
 
@Controller
@RequestMapping(value="/exerweb")
public class ExerWebController{
	
	Logger log = Logger.getLogger(ExerWebController.class);
	@Autowired
	protected StuExercisesService  stuExercisesService;
	@Autowired
	protected StuExerQuestionService  stuExerQuestionService;
	@Autowired
	protected StuExerKnowlwdgeService  stuExerKnowlwdgeService;
	
	/**
	 * 练习列表
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryExerInfo")
	public Object queryExerInfo(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req) {
		return stuExercisesService.selectPage(page, rows, req);
	}
	
	/**
	 * 新建练习
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addExerInfo")
	public Object addExerInfo(HttpServletRequest req, HttpServletResponse res){
		return stuExercisesService.add(req);
	} 
	
	/**
	 * 查看习题
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getExerInfoById")
	public Object getExerInfoById(HttpServletRequest req, HttpServletResponse res){
		return stuExercisesService.selectOne(req);
	} 
	
	/**
	 * 查看知识点列表
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getExerKnowledgeById")
	public Object getExerKnowledgeById(HttpServletRequest req, HttpServletResponse res){
		return stuExerKnowlwdgeService.selectList(req);
	} 
	
	/**
	 * 编辑练习
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteExerInfo")
	public Object deleteExerInfo(HttpServletRequest req, HttpServletResponse res){
		return stuExercisesService.delete(req);
	}
	
	/**
	 * 练习题目列表
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getExerQuesInfo")
	public Object getExerQuesInfo(HttpServletRequest req, HttpServletResponse res){
		return stuExerQuestionService.selectList(req);
	}
	
	/**
	 * 解答
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/answerExerQues")
	public Object answerExerQues(HttpServletRequest req, HttpServletResponse res){
		return stuExerQuestionService.update(req);
	}
	
	@ResponseBody
	@RequestMapping(value="/finishExerQues")
	public Object finishExerQues(HttpServletRequest req, HttpServletResponse res){
		return stuExercisesService.update(req);
	}
}
