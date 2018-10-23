package com.rensu.education.hctms.userauth.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("orgaInfoService")
public class OrgaInfoService extends BaseService<OrgaInfo> {
	
	Logger log = Logger.getLogger(OrgaInfoService.class);
	
	@Autowired
	protected OrgaInfoDao orgaInfoDao;

	/**
	 * hzx
	 * 分页查询OrgaInfo
	 * @param req
	 * @return
	 */
	public JSONObject selectPage(int pageIndex, int rows,OrgaInfo orgaInfo) {
		JSONObject jobj = new JSONObject();
		List<OrgaInfo> dataList = orgaInfoDao.selectPage(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				orgaInfo);
		int totalCount = orgaInfoDao.selectCount(orgaInfo);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	/***
	 * 更新OrgaInfo（科室管理）
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月4日
	 */
	public Object updateOrgaInfo(HttpServletRequest req) {
		//得到页面数据
		String state = req.getParameter("state");
		String ids = req.getParameter("ids");
		String orga_name = req.getParameter("orga_name");
		String sup_id = req.getParameter("sup_id");
		String orga_type = req.getParameter("orga_type");
		String orga_code1 = req.getParameter("orga_code1");
		String limit_num = req.getParameter("limit_num");
		OrgaInfo orgaInfo = new OrgaInfo();
		orgaInfo.setIds(ids);
		orgaInfo.setState(state);
		orgaInfo.setOrga_name(orga_name);
		orgaInfo.setOrga_code1(orga_code1);
		if(!("").equals(orga_type)&&null!=orga_type){
			orgaInfo.setOrga_type(Integer.parseInt(orga_type));
		}
		if(!("").equals(limit_num)&&null!=limit_num){
			orgaInfo.setLimit_num(Integer.parseInt(limit_num));
		}
		if(!("").equals(sup_id)&&null!=sup_id){
		orgaInfo.setSup_id(Integer.parseInt(sup_id));
		    if(Integer.parseInt(sup_id)==0){
			  orgaInfo.setOrga_level(1);
		    }else{
			orgaInfo.setOrga_level(2);
		}
		}
		int num= orgaInfoDao.update(orgaInfo);
		if(num>0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	}

	public Object addOrgaInfo(HttpServletRequest req) {
		//得到页面数据
		String orga_name = req.getParameter("orga_name");
		String sup_id = req.getParameter("sup_id");
		String orga_type = req.getParameter("orga_type");
		String orga_code1 = req.getParameter("orga_code1");
		String limit_num = req.getParameter("limit_num");
		OrgaInfo orgaInfo = new OrgaInfo();
		orgaInfo.setOrga_id(orgaInfoDao.getSequence());
		orgaInfo.setOrga_name(orga_name);
		orgaInfo.setOrga_code1(orga_code1);
		orgaInfo.setState("Y");
		if(!("").equals(orga_type)&&!(null==orga_type)){
			orgaInfo.setOrga_type(Integer.parseInt(orga_type));
		}
		if(!("").equals(limit_num)&&null!=limit_num){
			orgaInfo.setLimit_num(Integer.parseInt(limit_num));
		}
		orgaInfo.setCreate_time(new java.sql.Timestamp(new java.util.Date().getTime()));
		orgaInfo.setSup_id(Integer.parseInt(sup_id));
		if(Integer.parseInt(sup_id)==0){
			orgaInfo.setOrga_level(1);
		}else{
			orgaInfo.setOrga_level(2);
		}
		int num= orgaInfoDao.add(orgaInfo);
		if(num>0){
			return StringUtil.returns(true, "操作成功");
		}else{
			return StringUtil.returns(true, "操作异常");
		}
	}
	
	/***
	 * 加载获取一级机构名称
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月5日
	 */
	public Object selectOragName(HttpServletRequest req) {
		JSONObject jobj = new JSONObject();
		OrgaInfo orgaInfo = new OrgaInfo();
		String id = req.getParameter("ids");
		if(("").equals(id)||null==id){
			orgaInfo.setSup_id(0);
		}else{
			orgaInfo.setOrga_id(Integer.parseInt(id));
		}
		orgaInfo.setState("Y");
		List<OrgaInfo> dataList = orgaInfoDao.selectList(orgaInfo);
		jobj.put("rows", dataList);
		return StringUtil.dnull(jobj.toString());
	}
}
