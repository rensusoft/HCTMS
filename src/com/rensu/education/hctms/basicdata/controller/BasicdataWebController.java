package com.rensu.education.hctms.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.HtmlTemplate;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.bean.MarksheetItems;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.basicdata.bean.TeachFormConfig;
import com.rensu.education.hctms.basicdata.service.FormInfoService;
import com.rensu.education.hctms.basicdata.service.HtmlTemplateService;
import com.rensu.education.hctms.basicdata.service.MarksheetDetailService;
import com.rensu.education.hctms.basicdata.service.MarksheetItemsService;
import com.rensu.education.hctms.basicdata.service.MarksheetMainService;
import com.rensu.education.hctms.basicdata.service.MarksheetSubService;
import com.rensu.education.hctms.basicdata.service.TeachFormConfigService;
import com.rensu.education.hctms.basicdata.service.TfcDeptformRelaService;
import com.rensu.education.hctms.basicdata.service.TfcStutypeformRelaService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.service.RoleProcRelaService;
 
@Controller
@RequestMapping(value="/basicdataweb")
public class BasicdataWebController{
	
	Logger log = Logger.getLogger(BasicdataWebController.class);
	
	@Autowired
	protected MarksheetItemsService marksheetItemsService;
	@Autowired
	protected RoleProcRelaService roleProcRelaService;
	@Autowired
	protected FormInfoService formInfoService;
	@Autowired
	protected HtmlTemplateService htmlTemplateService;
	@Autowired
	protected MarksheetMainService marksheetMainService;
	@Autowired
	protected MarksheetSubService marksheetSubService;
	@Autowired
	protected MarksheetDetailService marksheetDetailService;
	@Autowired
	protected TeachFormConfigService teachFormConfigService;
	@Autowired
	protected TfcDeptformRelaService tfcDeptformRelaService;
	@Autowired
	protected TfcStutypeformRelaService tfcStutypeformRelaService;
	

	
	/***
	 * 根据type_code查询流程关联角色
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月15日
	 */
	@ResponseBody
	@RequestMapping(value="/selectProcessRole")
	public Object selectProcessRole(HttpServletRequest req){
		return roleProcRelaService.selectProcessRole(req);
	}
	/***
	 * 禁用、启用流程
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月17日
	 */
	@ResponseBody
	@RequestMapping(value="/updateProcessState")
	public Object updateProcessState(HttpServletRequest req){
		return roleProcRelaService.updateProcessState(req);
	}
	/***
	 * 模糊检索表单填充input
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月20日
	 */
	@ResponseBody
	@RequestMapping(value="/selectFormInfo")
	public Object selectFormInfo(HttpServletRequest req){
		return roleProcRelaService.selectFormInfo(req);
	}
	/***
	 * 关联的表单
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月20日
	 */
	@ResponseBody
	@RequestMapping(value="/formRelation")
	public Object formRelation(HttpServletRequest req){
		return roleProcRelaService.formRelation(req);
	}

	
	/**
	 * 基本评分方式列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryItemList")
	public Object queryItemList(){
		MarksheetItems items = new MarksheetItems();
		items.setState("Y");
		List<MarksheetItems> list = marksheetItemsService.selectList(items);
		JSONObject jobj = new JSONObject();
		jobj.put("rows", list);
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 评分表单列表
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryFormInfo")
	public Object queryFormInfo(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req) {
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		FormInfo form = new FormInfo();
		String name = req.getParameter("name");
		String create_auth_id = req.getParameter("create_auth_id");
		String availability = req.getParameter("availability");
		String state = req.getParameter("state");
		if(name!=null&&!name.trim().equals(""))
			form.setName(name.trim());
		if(create_auth_id!=null)
			form.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		form.setAvailability(availability);
		form.setState(state);
		form.setOrderCondition(" id desc");
		return formInfoService.selectPage(page, rows, form);
	}

	/**
	 * 查看表单信息
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getFormInfoById")
	public Object getFormInfoById(HttpServletRequest req, HttpServletResponse res){
		String id = req.getParameter("id");
		JSONObject jobj = new JSONObject();
		if(id!=null&&!id.equals("")){
			FormInfo form = formInfoService.selectOne(Integer.parseInt(id));
			form.setState("Y");
			jobj.put("data", form);
		}
		return StringUtil.dnull(jobj.toString());
	} 
	/**
	 * 查看评分表单评分项列表
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryMarksheetSubList")
	public Object queryMarksheetSubList(HttpServletRequest req, HttpServletResponse res){
		String mm_id = req.getParameter("mmid");
		String ms_id = req.getParameter("msid");
		String type_code = req.getParameter("type");
		JSONObject jobj = new JSONObject();
		List<MarksheetSub> sublist = null;
		if((mm_id!=null&&!mm_id.equals(""))||(ms_id!=null&&!ms_id.equals(""))){
			MarksheetSub sub = new MarksheetSub();
			if(mm_id!=null&&!mm_id.equals("")){
				sub.setMm_id(Integer.parseInt(mm_id));
			}
			if(ms_id!=null&&!ms_id.equals("")){
				sub.setMs_id(Integer.parseInt(ms_id));
			}
			if(type_code!=null&&!type_code.equals("")){
				sub.setType_code(Integer.parseInt(type_code));
			}
			sub.setState("Y");
			sub.setOrderCondition(" id,sort_num");
			sublist = marksheetSubService.selectList(sub);
			jobj.put("rows", sublist);
		}
		return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 查看评分表单评分方式
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryMarksheetDetailList")
	public Object queryMarksheetDetailList(HttpServletRequest req, HttpServletResponse res){
		String ms_id = req.getParameter("msid");
		JSONObject jobj = new JSONObject();
		if(ms_id!=null&&!ms_id.equals("")){
			MarksheetDetail detail = new MarksheetDetail();
			detail.setMs_id(Integer.parseInt(ms_id));
			detail.setState("Y");
			detail.setOrderCondition(" sco_num desc");
			List<MarksheetDetail> list = marksheetDetailService.selectList(detail);
			jobj.put("rows", list);
		}
		return StringUtil.dnull(jobj.toString());
	}
	/**
	 * 表单状态修改（删除、停/启用）
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyFormInfoState")
	public Object modifyFormInfoState(HttpServletRequest req, HttpServletResponse res){
		boolean flag = formInfoService.modifyFormInfoState(req);
		return StringUtil.returns(flag, "");
	}
	
	/**
	 * 新增普通表单
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addHtmlTemplat")
	public Object addHtmlTemplat(HttpServletRequest req, HttpServletResponse res){
		boolean flag = htmlTemplateService.addHtmlTemplat(req);
		return StringUtil.returns(flag, "");
	}
	
	/**
	 * 新增普通表单
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateHtmlTemplat")
	public Object updateHtmlTemplat(HttpServletRequest req, HttpServletResponse res){
		boolean flag = false;
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		if(id!=null&&!id.equals("")){
			HtmlTemplate html = new HtmlTemplate();
			html.setId(Integer.parseInt(id));
			if(name!=null&&!name.trim().equals(""))
				html.setT_name(name.trim());
			if(content!=null&&!content.trim().equals(""))
				html.setT_content(content.trim());
			int result = htmlTemplateService.update(html);
			if(result>0){
				flag = true;
			}
		}
		return StringUtil.returns(flag, "");
	}
	
	/**
	 * 新建评分表单
	 * @param req
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addMarksheet")
	public Object addMarksheet(HttpServletRequest req, HttpServletResponse res){
		boolean flag = marksheetMainService.addMarksheet(req);
		return StringUtil.returns(flag, "");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateMarksheet")
	public Object updateMarksheet(HttpServletRequest req, HttpServletResponse res){
		boolean flag = marksheetMainService.updateMarksheet(req);
		return StringUtil.returns(flag, "");
	}
	
	

	/**
	 * 得到所有的科室和学生类型和已存在的数据
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月10日
	 */
	@ResponseBody
	@RequestMapping(value="/getOrgaListAndStuType")
	public Object getOrgaListAndStuType(HttpServletRequest req){
		Map<String, Object> map=new HashMap<String, Object>();
		map=teachFormConfigService.getOrgaListAndStuType(req);	
		return map;
	}
	
	/**
	 * 得到所有的科室和学生类型
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月10日
	 */
	@ResponseBody
	@RequestMapping(value="/getForm")
	public Object getForm(@Param("rows") int rows, @Param("page") int page, HttpServletRequest req){
      return teachFormConfigService.getForm(page, rows, req);
	}
	
	/**
	 * 得到教学模板配置表
	 * @param rows
	 * @param page
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年3月10日
	 */
	@ResponseBody
	@RequestMapping(value="/getTeachForm")
	public Object getTeachForm(HttpServletRequest req){
		TeachFormConfig tfc = new TeachFormConfig();
		tfc.setOrderCondition("id");
		tfc.setState("Y");
	    List<TeachFormConfig> lists=teachFormConfigService.selectList(tfc);
        return StringUtil.returns(true, lists);
	}

	/**
	 *绑定   教学配置模板 	
	 *@param req
	 *@return
	 *@author huq
	 *@date 2017年3月13日
	 */
	@ResponseBody
	@RequestMapping(value="/addTDR")
	public Object addTDR(HttpServletRequest req){
	  int i=tfcDeptformRelaService.addTDR(req);
	  if(i>0){
		  return StringUtil.returns(true, "绑定成功！");
	  }else{
		  return StringUtil.returns(false, "绑定失败！");  
	  }
	}
	
	/***
	 * 通过普通表单ID查询出HTML内容
	 * @param req
	 * @return
	 * @author yuanb
	 * @date 2017年3月16日
	 */
	@ResponseBody
	@RequestMapping(value="/getHTMLInfo")
	public Object getHTMLInfo(HttpServletRequest req){
		Map<String,Object> rtnMap = htmlTemplateService.getHTMLInfo(req);
		if(rtnMap != null){
			return rtnMap;
		}else{
			return StringUtil.returns(false, "未查询到相关数据！");  
		}

	}
	/**
	 * 查看表单信息
	 * @param req
	 * @param res
	 * @return
	 * @author guocc
	 * @data 2017-05-04
	 */
	@ResponseBody
	@RequestMapping(value="/getFormInfoByIdFromSFM")
	public Object getFormInfoByIdFromSFM(HttpServletRequest req, HttpServletResponse res){
			String id = req.getParameter("id");
			String flag = req.getParameter("flag");
			String flag_read = req.getParameter("flag_read");
			String s_user_auth_id = req.getParameter("s_user_auth_id");
			String s_orga_id = req.getParameter("s_orga_id");
			String create_auth_id_str = req.getParameter("create_auth_id");
			String sfm_id = req.getParameter("sfm_id");
			JSONObject jobj = new JSONObject();
			ScoFormMain scoFormMain = new ScoFormMain();
			if (create_auth_id_str != null && create_auth_id_str.equals("-100")) {//  列表处触发请求
				LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
				Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
				scoFormMain.setCreate_auth_id(create_auth_id);
			}else if(create_auth_id_str != null && !create_auth_id_str.equals("-100")) {//  审核流程记录处触发请求
				scoFormMain.setCreate_auth_id(Integer.parseInt(create_auth_id_str));
			}
			if (s_user_auth_id != null && !s_user_auth_id.equals("")) {
				scoFormMain.setUser_auth_id(Integer.parseInt(s_user_auth_id));
			}
			if (s_orga_id != null && !s_orga_id.equals("")) {
				scoFormMain.setOrga_id(Integer.parseInt(s_orga_id));;
			}
			if (sfm_id != null && !sfm_id.equals("")) {
				scoFormMain.setId(Integer.parseInt(sfm_id));
			}
			if(id!=null&&!id.equals("")){
				FormInfo form = new FormInfo();
				scoFormMain.setForm_id(Integer.parseInt(id));
				if(flag_read != null && flag_read.equals("10")){
					form = formInfoService.selectOneFromSFM(scoFormMain);//  从sco_form_main表取数据（总分和得分）
				}else if(flag_read != null && flag_read.equals("-10")){
					if (flag != null && flag.equals("1")) {//  表单已近编辑过了
						form = formInfoService.selectOneFromSFM(scoFormMain);//  从sco_form_main表取数据（总分和得分）
					}else if (flag != null && flag.equals("-1")) {
						form = formInfoService.selectOne(Integer.parseInt(id));
					}
				}
				form.setState("Y");
				jobj.put("data", form);
			}
			return StringUtil.dnull(jobj.toString());
	}
	
	/**
	 * 查询表单名
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月10日
	 */
	@ResponseBody
	@RequestMapping(value="/selectTSR")
	public Object selectTSR(HttpServletRequest req){
	  List<FormInfo> lists=tfcStutypeformRelaService.selectTSR(req);
	  return StringUtil.returns(true,lists);  
	}
	
	/**
	 * 修改或者添加新的表单  （出科）
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年4月10日
	 */
	@ResponseBody
	@RequestMapping(value="/updateTSR")
	public Object updateTSR(HttpServletRequest req){
	  int i =tfcStutypeformRelaService.updateTSR(req);
	  if(i>0){
		  return StringUtil.returns(true,"保存成功"); 
	  }else{
		  return StringUtil.returns(false,"保存失败"); 
	  } 
	}

}
