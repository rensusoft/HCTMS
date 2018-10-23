package com.rensu.education.hctms.teach.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.teach.bean.MedicalDiagnose;
import com.rensu.education.hctms.teach.bean.MedicalRecord;
import com.rensu.education.hctms.teach.dao.MedicalDiagnoseDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("medicalDiagnoseService")
public class MedicalDiagnoseService extends BaseService<MedicalDiagnose> {
	
	Logger log = Logger.getLogger(MedicalDiagnoseService.class);
	
	@Autowired
	protected MedicalDiagnoseDao medicalDiagnoseDao;
	
	/**
	 * 查询医疗文书--入院记录--诊断
	 * @param pageIndex
	 * @param rows
	 * @param medicalDiagnose
	 * @return
	 * @author guocc
	 * @date 2017年8月21日
	 */
	public JSONObject selectMedicalDiagnose(int pageIndex, int rows, MedicalDiagnose medicalDiagnose) {
		JSONObject jobj = new JSONObject();
		List<MedicalDiagnose> dataList = medicalDiagnoseDao.selectPage(new RowBounds((pageIndex - 1) * rows, pageIndex * rows), 
				medicalDiagnose);
		int totalCount = medicalDiagnoseDao.selectCount(medicalDiagnose);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

}
