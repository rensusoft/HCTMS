package com.rensu.education.hctms.basicdata.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.score.bean.ScoFormMain;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.HtmlTemplate;
import com.rensu.education.hctms.basicdata.bean.MarksheetMain;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.HtmlTemplateDao;
import com.rensu.education.hctms.basicdata.dao.MarksheetMainDao;


@Service("formInfoService")
public class FormInfoService extends BaseService<FormInfo> {
	
	Logger log = Logger.getLogger(FormInfoService.class);
	
	@Autowired
	protected FormInfoDao formInfoDao;
	@Autowired
	protected HtmlTemplateDao htmlTemplateDao;
	@Autowired
	protected MarksheetMainDao marksheetMainDao;

	public boolean modifyFormInfoState(HttpServletRequest req) {
		boolean flag = false;
		String id = req.getParameter("id");
		String state = req.getParameter("state");
		String validity = req.getParameter("validity");
		String type = req.getParameter("type");
		if(type!=null&&!type.equals("")
			&&id!=null&&!id.trim().equals("")){
			FormInfo form = formInfoDao.selectOne(Integer.parseInt(id));
			if(form!=null){
				if(state!=null&&!state.trim().equals(""))
					form.setState(state);
				if(validity!=null&&!validity.trim().equals(""))
					form.setAvailability(validity);
				int formresult = formInfoDao.update(form);
				int result = 0;
				if(form.getRelated_id()!=null){
					if(Integer.parseInt(type)==2){
						HtmlTemplate html = new HtmlTemplate();
						html.setId(form.getRelated_id());
						if(validity!=null&&!validity.trim().equals(""))
							html.setValidity(validity);
						if(state!=null&&!state.trim().equals(""))
							html.setState(state);
						result=htmlTemplateDao.update(html);
					}else if(Integer.parseInt(type)==1){
						MarksheetMain main = new MarksheetMain();
						main.setId(form.getRelated_id());
						if(validity!=null&&!validity.trim().equals(""))
							main.setValidity(validity);
						if(state!=null&&!state.trim().equals(""))
							main.setState(state);
						result = marksheetMainDao.update(main);
					}
				}
				if(formresult>0&&result>0){
					flag = true;
				}
			}
		}
		return flag;
	}
	/**
	 * 从SCO_FORM_MAIN表获取分数和总分
	 * @param scoFormMain
	 * @return FormInfo
	 * @author guocc
	 * @date 2017年5月5日
	 */
	public FormInfo selectOneFromSFM(ScoFormMain scoFormMain) {
		return formInfoDao.selectOneFromSFM(scoFormMain);
	}

	/**
	 * 获取表单配置的表格内容
	 * @param formInfo
	 * @return FormInfo
	 * @author guocc
	 * @date 2017年4月27日
	 */
	public FormInfo getTableContent(FormInfo formInfo) {
		return formInfoDao.getTableContent(formInfo);
	}

	/**
	 * 从SCO_FORM_MAIN表获取表单配置的已填写的表格内容
	 * @param scoFormMain
	 * @return FormInfo
	 * @author guocc
	 * @date 2017年5月3日
	 */
	public FormInfo getTableContentFromSFM(ScoFormMain scoFormMain) {
		return formInfoDao.getTableContentFromSFM(scoFormMain);
	}  
	
}
