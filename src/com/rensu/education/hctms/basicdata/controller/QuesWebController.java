package com.rensu.education.hctms.basicdata.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.commons.fileupload.FileUploadException;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.basicdata.service.QeKnowledgeBaseService;
import com.rensu.education.hctms.basicdata.service.QeQuesAnswerService;
import com.rensu.education.hctms.basicdata.service.QeQuesOptionService;
import com.rensu.education.hctms.basicdata.service.QeQuesTypeService;
import com.rensu.education.hctms.basicdata.service.QeQuestionService;
 
@Controller
@RequestMapping(value="/quesweb")
public class QuesWebController{
	
	Logger log = Logger.getLogger(QuesWebController.class);
	
	@Autowired
	protected QeQuesOptionService qeQuesOptionService;
	@Autowired
	protected QeQuesTypeService qeQuesTypeService;
	@Autowired
	protected QeQuestionService qeQuestionService;
	@Autowired
	protected QeKnowledgeBaseService qeKnowledgeBaseService;
	@Autowired
	protected QeQuesAnswerService qeQuesAnswerService;

	/**
	 * 题型列表
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getQuesTypeList")
	public Object getQuesTypeList(HttpServletRequest req){
        return qeQuesTypeService.selectList();
	}
	
	/**
	 * 问题列表
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryQuesInfo")
	public Object queryQuesInfo(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req) {
		return qeQuestionService.selectPage(page, rows, req);
	}
	
	/**
	 * 问题详情
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getQuesInfoById")
	public Object getQuesInfoById(HttpServletRequest req, HttpServletResponse res){
		return qeQuestionService.selectOne(req);
	} 
	
	/**
	 * 选项列表
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getOptionInfoByQqid")
	public Object getOptionInfoByQqid(HttpServletRequest req, HttpServletResponse res){
		return qeQuesOptionService.selectListByQqid(req);
	}
	
	/**
	 * 问题答案
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getAnswerInfoByQqid")
	public Object getAnswerInfoByQqid(HttpServletRequest req, HttpServletResponse res){
		return qeQuesAnswerService.selectOneByQqid(req);
	} 
	
	/**
	 * 知识库列表
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getQeknowledgeList")
	public Object getQeknowledgeList(HttpServletRequest req, HttpServletResponse res){
        return qeKnowledgeBaseService.selectList(req);
	}
	
	@ResponseBody
	@RequestMapping(value="/getQeknowledgeById")
	public Object getQeknowledgeById(HttpServletRequest req, HttpServletResponse res){
        return qeKnowledgeBaseService.selectOne(req);
	}
	
	/**
	 * 问题编辑
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyQuesInfo")
	public Object modifyQuesInfo(HttpServletRequest req, HttpServletResponse res){
		return qeQuestionService.update(req);
	}
	
	/**
	 * 问题新增
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addQuesInfo")
	public Object addQuesInfo(HttpServletRequest req, HttpServletResponse res){
		return qeQuestionService.add(req);
	} 
	
	/**
	 * 问题列表模板导出
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/downQuesExcel")
	public void downQuesExcel(HttpServletRequest req, HttpServletResponse res){
		qeQuestionService.downQuesExcel(res);
	} 
	
	@ResponseBody
	@RequestMapping(value="/uploadQuesExcel")
	public void uploadQuesExcel(HttpServletRequest req, HttpServletResponse res) throws FileUploadException, IOException{
		res.getWriter().print(qeQuestionService.uploadQuesExcel(req).toString());
	} 
}
