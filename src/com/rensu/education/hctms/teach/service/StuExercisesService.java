package com.rensu.education.hctms.teach.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.QeQuestion;
import com.rensu.education.hctms.basicdata.dao.QeQuestionDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.teach.bean.StuExerKnowlwdge;
import com.rensu.education.hctms.teach.bean.StuExerQuestion;
import com.rensu.education.hctms.teach.bean.StuExercises;
import com.rensu.education.hctms.teach.dao.StuExerKnowlwdgeDao;
import com.rensu.education.hctms.teach.dao.StuExerQuestionDao;
import com.rensu.education.hctms.teach.dao.StuExercisesDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;


@Service("stuExercisesService")
public class StuExercisesService extends BaseService<StuExercises> {
	
	Logger log = Logger.getLogger(StuExercisesService.class);
	
	@Autowired
	protected StuExercisesDao stuExercisesDao;
	@Autowired
	protected StuExerKnowlwdgeDao stuExerKnowlwdgeDao;
	@Autowired
	protected QeQuestionDao qeQuestionDao;
	@Autowired
	protected StuExerQuestionDao stuExerQuestionDao;

	public JSONObject selectPage(int pageIndex, int rows, HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		String ques_title = req.getParameter("ques_title");
		String ques_num = req.getParameter("ques_num");
		String ques_sco = req.getParameter("ques_sco");
		String answer = req.getParameter("answer");
		StuExercises stuExer = new StuExercises();
		stuExer.setQues_title(ques_title);
		if(ques_num!=null&&!ques_num.equals(""))
			stuExer.setQues_num(Integer.parseInt(ques_num));
		if(ques_sco!=null&&!ques_sco.equals(""))
			stuExer.setQues_sco(Integer.parseInt(ques_sco));
		if(answer!=null&&!answer.equals(""))
			stuExer.setAnswer(Integer.parseInt(answer));
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		stuExer.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
//		stuExer.setState("Y");
		stuExer.setOrderCondition(" id desc");
		int totalCount = stuExercisesDao.selectCount(stuExer);
		List<StuExercises> dataList = stuExercisesDao.selectPage(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), stuExer);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));//总页数
		jobj.put("page", pageIndex);//当前页码
		jobj.put("records", totalCount);//总的记录数
		jobj.put("rows", dataList);//显示的具体数据，jsonarray格式。
		if(jobj!=null){
			String jsonStr = StringUtil.dnull(jobj.toString());
			jobj = JSONObject.fromObject(jsonStr);
		}
		return jobj;
	}
	
	public Object add(HttpServletRequest req) {
		boolean flag = false;
		String ques_title = req.getParameter("ques_title");
		String ques_num = req.getParameter("ques_num");
		String ques_sco = req.getParameter("ques_sco");
		String seklist = req.getParameter("seklist");
		StuExercises stuExer = new StuExercises();
		int qeid = stuExercisesDao.getSequence();
		stuExer.setId(qeid);
		stuExer.setQues_title(ques_title);
		if(ques_num!=null&&!ques_num.equals(""))
			stuExer.setQues_num(Integer.parseInt(ques_num));
		if(ques_sco!=null&&!ques_sco.equals(""))
			stuExer.setQues_sco(Integer.parseInt(ques_sco));
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		stuExer.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		stuExer.setState("Y");
		int num = stuExercisesDao.add(stuExer);
		if(num>0) flag = true;
		else flag = false;
		if(seklist!=null){
			JSONArray jsonArray = JSONArray.fromObject(seklist);
			List<StuExerKnowlwdge> list = (List<StuExerKnowlwdge>) JSONArray.toCollection(jsonArray,StuExerKnowlwdge.class);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					StuExerKnowlwdge exerKnow = list.get(i);
					exerKnow.setId(stuExerKnowlwdgeDao.getSequence());
					exerKnow.setQe_id(qeid);
					exerKnow.setState("Y");
					num = stuExerKnowlwdgeDao.add(exerKnow);
				}
				if(num>0) flag = true;
				else flag = false;
				if(flag)
					flag = createStuExerQues(qeid);
			}
		}
		return StringUtil.returns(flag, "");
	}
	
	/**
	 * 根据知识点随机抽取试题
	 * @param qeid
	 * @return
	 */
	private boolean createStuExerQues(int qeid){
		boolean flag = false;
		if(qeid != 0){
			StuExercises stuExer = stuExercisesDao.selectOne(qeid);
			if(stuExer!=null){
				StuExerKnowlwdge exerKnow = new StuExerKnowlwdge();
				exerKnow.setQe_id(qeid);
				exerKnow.setState("Y");
				List<StuExerKnowlwdge> sekList = stuExerKnowlwdgeDao.selectList(exerKnow);
				if(sekList!=null&&sekList.size()>0){
					if(stuExer.getQues_num()>0){
						List<StuExerQuestion> seqList = new ArrayList<StuExerQuestion>();
						for(int i=0;i<sekList.size();i++){
							exerKnow = sekList.get(i);
							QeQuestion ques = new QeQuestion();
							ques.setQkb_id(exerKnow.getQkb_id());
							ques.setState("Y");
							List<QeQuestion> queList = qeQuestionDao.selectList(ques);
							if(queList!=null&&queList.size()>0&&exerKnow.getProportion()>0){
								int size = stuExer.getQues_num()*exerKnow.getProportion()/100;
								for(int j=0;j<size;j++){
									int n = (int)(1+Math.random()*(queList.size()-1+1));
									QeQuestion qeques = queList.get(n-1);
									StuExerQuestion exerques = new StuExerQuestion();
									exerques.setQe_id(exerKnow.getQe_id());
									exerques.setQek_id(exerKnow.getId());
									exerques.setQq_id(qeques.getId());
									exerques.setState("Y");
									exerques.setStu_result("N");
									seqList.add(exerques);
								}
								if(seqList.size()>0){
									int num = stuExerQuestionDao.insertWithList(seqList);
									if(num>0) flag = true;
									else flag = false;
								}
							}	
						}
					}
				}
			}
		}
		return flag;
	}
	
	public Object delete(HttpServletRequest req) {
		boolean flag = false;
		String seid = req.getParameter("seid");
		String state = req.getParameter("state");
		StuExercises exer = new StuExercises();
		if(seid!=null&&!seid.equals(""))
			exer.setId(Integer.parseInt(seid));
		if(state!=null&&!state.equals(""))
			exer.setState(state);
		int num = stuExercisesDao.update(exer);
		if(num>0) flag = true;
		else flag = false;
		return StringUtil.returns(flag, "");
	}
	
	public Object update(HttpServletRequest req) {
		boolean flag = false;
		String seid = req.getParameter("seid");
		StuExercises exer = new StuExercises();
		StuExerQuestion ques = new StuExerQuestion();
		if(seid!=null&&!seid.equals("")){
			exer.setId(Integer.parseInt(seid));
			ques.setQe_id(Integer.parseInt(seid));
		}
		List<StuExerQuestion> quesList = stuExerQuestionDao.selectList(ques);
		if(quesList!=null&&quesList.size()>0){
			int qnum = 0;
			for(int i=0;i<quesList.size();i++){
				if(quesList.get(i).getStu_result()=="Y")
					qnum++;
			}
			exer.setAnswer(qnum);
			int ques_sco = qnum/quesList.size()*100;
			exer.setQues_sco(ques_sco);
			exer.setState("E");
			int num = stuExercisesDao.update(exer);
			if(num>0) flag = true;
			else flag = false;
		}
		return StringUtil.returns(flag, "");
	}
	
	public Object selectOne(HttpServletRequest req) {
		String id = req.getParameter("id");
		JSONObject jobj = new JSONObject();
		if(id!=null&&!id.equals("")){
			StuExercises exer = stuExercisesDao.selectOne(Integer.parseInt(id));
			if(exer!=null)
				jobj.put("data", exer);
		}
	return StringUtil.dnull(jobj.toString());
	}
}
