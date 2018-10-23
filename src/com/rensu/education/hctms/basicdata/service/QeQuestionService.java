package com.rensu.education.hctms.basicdata.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;






import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.ImportExcel;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.QeKnowledgeBase;
import com.rensu.education.hctms.basicdata.bean.QeQuesAnswer;
import com.rensu.education.hctms.basicdata.bean.QeQuesOption;
import com.rensu.education.hctms.basicdata.bean.QeQuesType;
import com.rensu.education.hctms.basicdata.bean.QeQuestion;
import com.rensu.education.hctms.basicdata.dao.QeKnowledgeBaseDao;
import com.rensu.education.hctms.basicdata.dao.QeQuesAnswerDao;
import com.rensu.education.hctms.basicdata.dao.QeQuesOptionDao;
import com.rensu.education.hctms.basicdata.dao.QeQuesTypeDao;
import com.rensu.education.hctms.basicdata.dao.QeQuestionDao;

import java.io.OutputStream;

@Service("qeQuestionService")
public class QeQuestionService extends BaseService<QeQuestion> {
	
	Logger log = Logger.getLogger(QeQuestionService.class);
	
	@Autowired
	protected QeQuestionDao qeQuestionDao;
	@Autowired
	protected QeQuesAnswerDao qeQuesAnswerDao;
	@Autowired
	protected QeQuesOptionDao qeQuesOptionDao;
	@Autowired
	protected QeQuesTypeDao qeQuesTypeDao;
	@Autowired
	protected QeKnowledgeBaseDao qeKnowledgeBaseDao;

	public JSONObject selectPage(int pageIndex, int rows, HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		String type_id = req.getParameter("type_id");
		String qkb_id = req.getParameter("qkb_id");
		String ssco_num = req.getParameter("ssco_num");
		String esco_num = req.getParameter("esco_num");
		String sdifficulty_num = req.getParameter("sdifficulty_num");
		String edifficulty_num = req.getParameter("edifficulty_num");
		String sdifferent_num = req.getParameter("sdifferent_num");
		String edifferent_num = req.getParameter("edifferent_num");
		QeQuestion question = new QeQuestion();
		if(type_id!=null&&!type_id.equals(""))
			question.setType_id(Integer.parseInt(type_id));
		if(qkb_id!=null&&!qkb_id.equals(""))
			question.setQkb_id(Integer.parseInt(qkb_id));
		question.setSsco_num(ssco_num);
		question.setEsco_num(esco_num);
		question.setSdifficulty_num(sdifficulty_num);
		question.setEdifficulty_num(edifficulty_num);
		question.setSdifferent_num(sdifferent_num);
		question.setEdifferent_num(edifferent_num);
		question.setState("Y");
		question.setOrderCondition(" id asc");
		List<QeQuestion> dataList = qeQuestionDao.selectPage(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), question);
		int totalCount = qeQuestionDao.selectCount(question);
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

	public Object selectOne(HttpServletRequest req){
		String id = req.getParameter("id");
		JSONObject jobj = new JSONObject();
		if(id!=null&&!id.equals("")){
			QeQuestion question = qeQuestionDao.selectOne(Integer.parseInt(id));
			jobj.put("data", question);
		}
		return StringUtil.dnull(jobj.toString());
	}

	public Object update(HttpServletRequest req){
		boolean flag = false;
		int num = 0;
		String qid = req.getParameter("qid");
		String type_id = req.getParameter("type_id");
		String qkb_id = req.getParameter("qkb_id");
		String sco_num = req.getParameter("sco_num");
		String difficulty_num = req.getParameter("difficulty_num");
		String different_num = req.getParameter("different_num");
		String require = req.getParameter("require");
		String content = req.getParameter("content");
		String state = req.getParameter("state");
		String aid = req.getParameter("aid");
		String answer = req.getParameter("answer");
		String optList = req.getParameter("optList");
		if(qid!=null&&!qid.equals("")&&((type_id!=null&&!type_id.equals(""))
				||(qkb_id!=null&&!qkb_id.equals(""))||(sco_num!=null&&!sco_num.equals(""))
				||(difficulty_num!=null&&!difficulty_num.equals(""))
				||(different_num!=null&&!different_num.equals(""))||(require!=null&&!require.equals(""))
				||(content!=null&&!content.equals(""))||(state!=null&&!state.equals("")))){
			QeQuestion question = new QeQuestion();
			question.setId(Integer.parseInt(qid));
			if(type_id!=null&&!type_id.equals(""))
				question.setType_id(Integer.parseInt(type_id));
			if(qkb_id!=null&&!qkb_id.equals(""))
				question.setQkb_id(Integer.parseInt(qkb_id));
			if(sco_num!=null&&!sco_num.equals(""))
				question.setSco_num(Integer.parseInt(sco_num));
			if(difficulty_num!=null&&!difficulty_num.equals(""))
				question.setDifficulty_num(Integer.parseInt(difficulty_num));
			if(different_num!=null&&!different_num.equals(""))
				question.setDifferent_num(Integer.parseInt(different_num));
			if(require!=null&&!require.equals(""))
				question.setTeaching_require(Integer.parseInt(require));
			if(content!=null&&!content.equals(""))
				question.setQues_content(content);
			if(state!=null&&!state.equals(""))
				question.setState(state);
			num = qeQuestionDao.update(question);
			if(num>0) flag = true;
			else flag = false;
		}
		if(answer!=null&&!answer.equals("")){
			QeQuesAnswer quesanswer = new QeQuesAnswer();
			if(aid!=null&&!aid.equals("")){
				quesanswer.setId(Integer.parseInt(aid));
				quesanswer.setAnswer(answer);
				num = qeQuesAnswerDao.update(quesanswer);
			}else if(qid!=null&&!qid.equals("")){
				quesanswer.setId(qeQuesAnswerDao.getSequence());
				quesanswer.setQq_id(Integer.parseInt(qid));
				quesanswer.setAnswer(answer);
				quesanswer.setState("Y");
				num = qeQuesAnswerDao.add(quesanswer);
			}
			if(num>0) flag = true;
			else flag = false;
		}else{
			if(qid!=null&&!qid.equals("")){
				QeQuesAnswer qeQuesAnswer = new QeQuesAnswer();
				qeQuesAnswer.setQq_id(Integer.parseInt(qid));
				List<QeQuesAnswer> list = qeQuesAnswerDao.selectList(qeQuesAnswer);
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						qeQuesAnswer = list.get(i);
						qeQuesAnswer.setState("X");
						qeQuesAnswerDao.update(qeQuesAnswer);
					}
					
				}
			}
		}
		if(optList!=null&&!optList.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(optList);
			List<QeQuesOption> list = (List<QeQuesOption>) JSONArray.toCollection(jsonArray, QeQuesOption.class);
			if(list!=null&&list.size()>0){
				if(qid!=null&&!qid.equals("")){
					QeQuesOption quesOption = new QeQuesOption();
					quesOption.setQq_id(Integer.parseInt(qid));
					List<QeQuesOption> optinList = qeQuesOptionDao.selectList(quesOption);
					if(optinList!=null&&optinList.size()>0){
						for(int i=0;i<optinList.size();i++){
							int j=0;
							for(;j<list.size();j++){
								if(optinList.get(i).getId()==list.get(j).getId()){
									break;
								}
							}
							if(j>=list.size()){
								QeQuesOption option = optinList.get(i);
								option.setState("X");
								qeQuesOptionDao.update(option);
							}
						}
					}
				}
				for(int i=0;i<list.size();i++){
					QeQuesOption option = list.get(i);
					if(option.getId()==null){
						option.setId(qeQuesOptionDao.getSequence());
						option.setState("Y");
						num = qeQuesOptionDao.add(option);
					}else{
						num = qeQuesOptionDao.update(option);
					}
				}
				if(num>0) flag = true;
				else flag = false;
			}else{
				if(qid!=null&&!qid.equals("")){
					QeQuesOption quesOption = new QeQuesOption();
					quesOption.setQq_id(Integer.parseInt(qid));
					List<QeQuesOption> optinList = qeQuesOptionDao.selectList(quesOption);
					if(optinList!=null&&optinList.size()>0){
						for(int i=0;i<optinList.size();i++){
							QeQuesOption option = optinList.get(i);
							option.setState("X");
							qeQuesOptionDao.update(option);
						}
					}
				}
			}
		}
		return StringUtil.returns(flag, "");
	}
	
	public Object add(HttpServletRequest req){
		boolean flag = false;
		String type_id = req.getParameter("type_id");
		String qkb_id = req.getParameter("qkb_id");
		String sco_num = req.getParameter("sco_num");
		String difficulty_num = req.getParameter("difficulty_num");
		String different_num = req.getParameter("different_num");
		String require = req.getParameter("require");
		String content = req.getParameter("content");
		String answer = req.getParameter("answer");
		String optList = req.getParameter("optList");
		QeQuestion question = new QeQuestion();
		Integer qid = qeQuestionDao.getSequence();
		question.setId(qid);
		if(type_id!=null&&!type_id.equals(""))
			question.setType_id(Integer.parseInt(type_id));
		if(qkb_id!=null&&!qkb_id.equals(""))
			question.setQkb_id(Integer.parseInt(qkb_id));
		if(sco_num!=null&&!sco_num.equals(""))
			question.setSco_num(Integer.parseInt(sco_num));
		if(difficulty_num!=null&&!difficulty_num.equals(""))
			question.setDifficulty_num(Integer.parseInt(difficulty_num));
		if(different_num!=null&&!different_num.equals(""))
			question.setDifferent_num(Integer.parseInt(different_num));
		if(require!=null&&!require.equals(""))
			question.setTeaching_require(Integer.parseInt(require));
		if(content!=null&&!content.equals(""))
			question.setQues_content(content);
		question.setState("Y");
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		question.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
		int num = qeQuestionDao.add(question);
		if(num>0) flag = true;
		else flag = false;
		if(answer!=null&&!answer.equals("")){
			QeQuesAnswer quesanswer = new QeQuesAnswer();
			quesanswer.setId(qeQuesAnswerDao.getSequence());
			quesanswer.setQq_id(qid);
			quesanswer.setAnswer(answer);
			quesanswer.setState("Y");
			num = qeQuesAnswerDao.add(quesanswer);
			if(num>0) flag = true;
			else flag = false;
		}
		if(optList!=null&&!optList.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(optList);
			List<QeQuesOption> list = (List<QeQuesOption>) JSONArray.toCollection(jsonArray, QeQuesOption.class);
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					QeQuesOption option = list.get(i);
					option.setId(qeQuesOptionDao.getSequence());
					option.setQq_id(qid);
					option.setState("Y");
					num = qeQuesOptionDao.add(option);
				}
				if(num>0) flag = true;
				else flag = false;
			}
		}
		return StringUtil.returns(flag, "");
	}
	
	public Object downQuesExcel(HttpServletResponse response) {
		boolean flag = false;
		String[] Title = { "所属章节","题型", "教学要求","分值", "难度", "区分度", "题目内容", "选项编辑","答案解析"};
		try {
		   OutputStream os = response.getOutputStream();// 取得输出流      
		   response.reset();// 清空输出流      
		   response.setHeader("Content-disposition", "attachment; filename="+ new String("题库列表.xls".getBytes("GB2312"),"ISO8859-1"));
		   // 设定输出文件头      
		   response.setContentType("application/msexcel");// 定义输出类型   
		   WritableWorkbook workbook = Workbook.createWorkbook(os);
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);
		   for (int i = 0; i < Title.length; i++) {
			   sheet.addCell(new Label(i, 0,Title[i],wcf_center));
		   }
		   QeKnowledgeBase knowledge = new QeKnowledgeBase();
		   knowledge.setState("Y");
		   knowledge.setOrderCondition(" ordinal asc");
		   List<QeKnowledgeBase> kldList = qeKnowledgeBaseDao.selectList(knowledge);
		   if(kldList!=null&&kldList.size()>0){
			   List list = new ArrayList();
			   for(int i=0;i<kldList.size();i++){
				   list.add(kldList.get(i).getId()+"."+kldList.get(i).getName());
			   }
			   Label norFormat = new Label(0, 1, "请选择", wcf_center); 
			   WritableCellFeatures ws = new WritableCellFeatures(); 
			   ws.setDataValidationList(list); 
			   norFormat.setCellFeatures(ws); 
			   sheet.addCell(norFormat);
		   }
		   QeQuesType type = new QeQuesType();
		   type.setState("Y");
		   List<QeQuesType> typeList = qeQuesTypeDao.selectList(type);
		   if(typeList!=null&&typeList.size()>0){
			   List list = new ArrayList();
			   for(int i=0;i<kldList.size();i++){
				   list.add(typeList.get(i).getId()+"."+typeList.get(i).getType_name());
			   }
			   Label norFormat = new Label(1, 1, "请选择", wcf_center); 
			   WritableCellFeatures ws = new WritableCellFeatures(); 
			   ws.setDataValidationList(list); 
			   norFormat.setCellFeatures(ws); 
			   sheet.addCell(norFormat);
		   }
		   List list = new ArrayList();
		   list.add("1.掌握");
		   list.add("2.熟悉");
		   list.add("3.了解");
		   
		   Label norFormat = new Label(2, 1, "请选择", wcf_center); 
		   WritableCellFeatures ws = new WritableCellFeatures(); 
		   ws.setDataValidationList(list); 
		   norFormat.setCellFeatures(ws); 
		   sheet.addCell(norFormat);
		   workbook.write();
		   workbook.close();
		} catch (Exception e) {
		   e.printStackTrace();
		} finally{
		   flag = true;
		}
		return StringUtil.returns(flag, "");
	}
	
	public Object uploadQuesExcel(HttpServletRequest req) throws FileUploadException, IOException{
		boolean flag = false;
		//1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		//4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		List<FileItem> list = upload.parseRequest(req);
		if(list!=null&&list.size()>0){
			String filename = list.get(0).getName();
			filename = filename.substring(filename.lastIndexOf("\\")+1);
			InputStream in = list.get(0).getInputStream();
			org.apache.poi.ss.usermodel.Workbook wb = ImportExcel.openWorkbook(in, filename);
			if (wb == null) {
				return null;
			}
			List<QeQuestion> quesList = new ArrayList<QeQuestion>();
			List<QeQuesOption> optionList = new ArrayList<QeQuesOption>();
			List<QeQuesAnswer> answerList = new ArrayList<QeQuesAnswer>();
			LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
			Sheet sheet = (Sheet) wb.getSheetAt(0);
			Row row = null;
			int totalRows = sheet.getPhysicalNumberOfRows();
			for (int r = 1; r < totalRows; r++) {
				QeQuestion ques = new QeQuestion();
				int qid = qeQuestionDao.getSequence();
				ques.setId(qid);
				row = sheet.getRow(r);
				if (row.getCell(0) != null) {
					String str = ImportExcel.getCellValue(row.getCell(0));
					if(str.contains("."))
						ques.setQkb_id(Integer.parseInt(str.split("\\.")[0]));
				}
				if (row.getCell(1) != null) {
					String str = ImportExcel.getCellValue(row.getCell(1));
					if(str.contains("."))
						ques.setType_id(Integer.parseInt(str.split("\\.")[0]));
				}
				if (row.getCell(2) != null) {
					String str = ImportExcel.getCellValue(row.getCell(2));
					if(str.contains("."))
						ques.setTeaching_require(Integer.parseInt(str.split("\\.")[0]));
				}
				if (row.getCell(3) != null) {
					double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(3)));
					int b=(int)a;
					ques.setSco_num(b);
				}
				if (row.getCell(4) != null) {
					double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(4)));
					int b=(int)a;
					ques.setDifficulty_num(b);
				}
				if (row.getCell(5) != null) {
					double a=Double.parseDouble(ImportExcel.getCellValue(row.getCell(5)));
					int b=(int)a;
					ques.setDifferent_num(b);
				}
				if (row.getCell(6) != null) {
					ques.setQues_content(ImportExcel.getCellValue(row.getCell(6)));
				}
				ques.setCreate_auth_id(loginBean.getvUserDetailInfo().getAuth_id());
				ques.setState("Y");
				quesList.add(ques);
				if (row.getCell(7) != null) {
					String str = ImportExcel.getCellValue(row.getCell(7));
					if(str.contains(";")){
						String[] strlist = str.split(";");
						if(strlist!=null&&strlist.length>0){
							for(int i=0;i<strlist.length;i++){
								QeQuesOption option = new QeQuesOption();
								if(strlist[i].contains(".")){
									option.setQq_id(qid);
									option.setOption_content(strlist[i].split(".")[1]);
									option.setOption_flag(strlist[i].split(".")[0]);
									option.setState("Y");
									optionList.add(option);
								}
							}
						}
					}
				}
				if (row.getCell(8) != null) {
					QeQuesAnswer answer = new QeQuesAnswer();
					answer.setId(qeQuesAnswerDao.getSequence());
					answer.setQq_id(qid);
					answer.setAnswer(ImportExcel.getCellValue(row.getCell(8)));
					answer.setState("Y");
					answerList.add(answer);
				}
			}
			int num = 0;
			if(quesList.size()>0){
				num = qeQuestionDao.insertWithList(quesList);
				if(num > 0) flag = true;
				else flag = false;
			}
			if(optionList.size()>0){
				num = qeQuesOptionDao.insertWithList(optionList);
				if(num > 0) flag = true;
				else flag = false;
			}
			if(answerList.size()>0){
				num = qeQuesAnswerDao.insertWithList(answerList);
				if(num > 0) flag = true;
				else flag = false;
			}
		}
		return StringUtil.returns(flag, "");
	}
}
