package com.rensu.education.hctms.score.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.dao.SysDictSubDao;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.score.bean.StuSco;
import com.rensu.education.hctms.score.dao.StuScoDao;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.ImportExcel;
import com.rensu.education.hctms.util.JxlUtil;
import com.rensu.education.hctms.util.StringUtil;



@Service("stuScoService")
public class StuScoService extends BaseService<StuSco> {



	Logger log = Logger.getLogger(StuScoService.class);

	@Autowired
	protected StuScoDao stuScoDao;
	@Autowired
	protected StudentInfoDao studentInfoDao;
	@Autowired
	protected SysDictSubDao sysDictSubDao;
	private String msg = "";
	String existcodes = "";
	
	/***
	 * 导出录入学生成绩模板
	 * 
	 * 
	 */

	public void downStuscoInfo(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String title = "学生考试成绩批量导入模板";
		int index = 0;
		JxlUtil jxl = new JxlUtil(title + ".xls");
		List<StudentInfo> studentInfos = studentInfoDao.selectStudent();
		jxl.buildSheet(title, 0);
		jxl.addHeaderCell("学生姓名", 0, index++);
		jxl.addHeaderCell("学生工号", 0, index++);
		jxl.addHeaderCell("考核科目", 0, index++);
		jxl.addHeaderCell("成绩", 0, index++);
		for (int k = 0; studentInfos != null && k < studentInfos.size(); k++) {
			StudentInfo or = studentInfos.get(k);
			jxl.addDataCell(or.getStu_name(), k + 1, 0);
				jxl.addDataCell(or.getUser_code().toString(), k + 1, 1);
		}

		@SuppressWarnings("unchecked")
		List<SysDictSub> sysDictSubs = (List<SysDictSub>) req.getSession()
				.getServletContext().getAttribute(Consts.SESSION_SYS_DICT_SUB);
		jxl.buildSheet("科目类型代码字典", 1);
		jxl.addHeaderCell("科目类型代码", 0, 0);
		jxl.addHeaderCell("科目类型名称", 0, 1);
		List<SysDictSub> list=new ArrayList<SysDictSub>();
		for (int i = 0; sysDictSubs != null && i < sysDictSubs.size(); i++) {
			if (sysDictSubs.get(i).getSup_code().equals("subject")) {
				list.add(sysDictSubs.get(i));
			}
		}
		
		for (int k=0;k<list.size();k++){
			SysDictSub sd = list.get(k);
			jxl.addDataCell(sd.getItem_type_code(), k + 1, 0);
			jxl.addDataCell(sd.getItem_type_name(), k + 1, 1);
		}
		java.io.File file = jxl.getFileExcel();
		JxlUtil.downLoadExcel(file, req, res);
	}


	/**
	 * 读取学生成绩excel文档
	 * 
	 * @param in
	 * @param fileName
	 * @param req
	 * @return
	 */
	public List<StuSco> uploadStoscoInfo(InputStream in, String fileName,
			HttpServletRequest req) throws Exception {
		msg = "";
		existcodes = "";
		Workbook wb = ImportExcel.openWorkbook(in, fileName);
		if (wb == null) {
			msg = "导入文件" + fileName + "扩展名异常，请确认文件格式";
			log.info(msg);
			return null;
		}
		List<StuSco> list = new ArrayList<StuSco>();
		Sheet sheet = (Sheet) wb.getSheetAt(0);
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(
				Consts.SESSION_LOGIN_KEY);
		Integer create_auth_id = loginBean.getvUserDetailInfo().getAuth_id();
		boolean flag = true;
		for (int r = 1; r < totalRows; r++) {
			StuSco stuSco = new StuSco();
			row = sheet.getRow(r);
			HSSFCell cell = null;
			Integer stu_auth_id = null;
			if (row.getCell(0) != null && row.getCell(1) != null) {
				String stuName = ImportExcel.getCellValue(row.getCell(0));
				String user_Code = ImportExcel.getCellValue(row.getCell(1));
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStuName(stuName);
				studentInfo.setUser_code(user_Code);
				StudentInfo studentInfos = studentInfoDao
						.selectStuAuthId(studentInfo);
				stu_auth_id = studentInfos.getAuth_id();
				stuSco.setStu_auth_id(studentInfos.getAuth_id());
			}
			List<StuSco> scoList = null;
			if (row.getCell(2) != null) {
				cell = (HSSFCell) row.getCell(2);
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					existcodes += "第" + (r + 1) + "行考核科目id格式错误，应为数字;";
					flag = false;
					continue;
				}
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				String a = ImportExcel.getCellValue(row.getCell(2));
				a.toString();
				SysDictSub sysDictSub = sysDictSubDao.selectByCode(a);
				if (sysDictSub != null) {
					Integer b = (int) Double.parseDouble(a);
					StuSco sco= new StuSco();
					sco.setStu_auth_id(stu_auth_id);
					sco.setState("Y");
					sco.setSubject_id(b);
					scoList = stuScoDao.selectList(sco);
					stuSco.setSubject_id(b);
				} else {
					existcodes += "第" + (r + 1) + "行考核科目id不存在;";
					flag = false;
					continue;
				}
			}
			stuSco.setState("Y");

			if (row.getCell(3) != null) {
				cell = (HSSFCell) row.getCell(3);
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
					existcodes += "第" + (r + 1) + "行分数格式错误，应为数字;";
					flag = false;
					continue;
				}
				double a = Double.parseDouble(ImportExcel.getCellValue(row
						.getCell(3)));

				if (a > 100 || a < 0) {
					existcodes += "第" + (r + 1) + "行分数格式错误，为百分制;";
					flag = false;
					continue;
				} else {
					Integer b = (int) a;
					stuSco.setSco_num(b);
				}
			}
			if (scoList != null && !scoList.isEmpty()) {
				stuSco.setId(scoList.get(0).getId());
				int num = stuScoDao.update(stuSco);
				if (num < 0){
					flag = false;
				}
			}else{
				Integer id = stuScoDao.getSequence();
				stuSco.setId(id);
				stuSco.setCreate_auth_id(create_auth_id);
				Timestamp d = new Timestamp(System.currentTimeMillis());
				stuSco.setCreate_time(d);
				list.add(stuSco);
			}
		}

		log.info("existcodes-------" + existcodes);
		if (flag) {
			if (list.isEmpty()) {
				existcodes = "true";
			}
			return list;
		} else {
			return null;
		}
	}

	public String uploadStuSco(InputStream in, String fileName,
			HttpServletRequest req) {
		List<StuSco> list = null;

		try {
			list = uploadStoscoInfo(in, fileName, req);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			int num = stuScoDao.insertWithList(list);
			
			if (num > 0) {
				msg = "true";
			}
		}
		if (existcodes != null && !existcodes.equals("")) {
			msg = existcodes;
		}
		return msg;
	}

	
	
	public Object selectPageWithUserInfo(int pageIndex, int rows,StuSco stuSco) {

        JSONObject jobj=new JSONObject();
		List<StuSco> stuScos = stuScoDao.selectPageWithUserInfo(new RowBounds((pageIndex - 1) * rows, pageIndex * rows),stuSco);
		List<StuSco> scos=stuScoDao.selectAll();
		for(int i=0;i<stuScos.size();i++)
		{
			for(int j=0;j<scos.size();j++){
				Integer subId=scos.get(j).getSubject_id();
				Integer sco=scos.get(j).getSco_num();
				
			     
				if(stuScos.get(i).getStu_auth_id().equals(scos.get(j).getStu_auth_id()))
				{
                    if(subId!=null){
                		if(subId==1)
    					{
    						stuScos.get(i).setMedicalSco(sco.toString());
    					}
    					
    					if(subId==2)
    					{
    						stuScos.get(i).setSurgicalSco(sco.toString());
    					}
    					if(subId==3)
    					{
    						stuScos.get(i).setEspeciallySco(sco.toString());
    					}
    					if(subId==4)
    					{
    						stuScos.get(i).setMedicalScos(sco.toString());
    					}
    					if(subId==5)
    					{
    						stuScos.get(i).setSurgicalScos(sco.toString());
    					}
    					if(subId==6)
    					{
    						stuScos.get(i).setEspeciallyScos(sco.toString());
    					}
    					if(subId==7)
    					{
    						stuScos.get(i).setFinalSco(sco.toString());
    					}
				}else{
					stuScos.get(i).setMedicalSco("");
					stuScos.get(i).setSurgicalSco("");
					stuScos.get(i).setEspeciallySco("");
					stuScos.get(i).setMedicalScos("");
					stuScos.get(i).setSurgicalScos("");
					stuScos.get(i).setEspeciallyScos("");
					stuScos.get(i).setFinalSco("");
				}
					
				}
			}
		}
		int totalCount=stuScoDao.selectPageWithUserInfoCount();
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", stuScos);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
}
