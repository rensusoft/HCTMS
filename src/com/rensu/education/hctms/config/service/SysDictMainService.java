package com.rensu.education.hctms.config.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.core.utils.SystemConfigInitListener;
import com.rensu.education.hctms.config.bean.SysDictMain;
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.dao.SysDictMainDao;
import com.rensu.education.hctms.config.dao.SysDictSubDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("sysDictMainService")
public class SysDictMainService extends BaseService<SysDictMain> {
	
	Logger log = Logger.getLogger(SysDictMainService.class);
	
	@Autowired
	protected SysDictMainDao sysDictMainDao;
	@Autowired
	protected SysDictSubDao sysDictSubDao;
	@Autowired
	private SystemConfigInitListener systemConfigInitListener;
	
	/***
	 *  系统字典 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param orgaInfo
	 * @return
	 * @author hezx
	 * @date 2017年1月12日
	 */
	public JSONObject selectPage(int pageIndex, int rows,SysDictMain sysDictMain) {
		JSONObject jobj = new JSONObject();
		List<SysDictMain> dataList = sysDictMainDao.selectPage(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				sysDictMain);
		int totalCount = sysDictMainDao.selectCount(sysDictMain);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}
    /***
     * 新增系统字典 
     * @param req
     * @return
     * @author hezx
     * @date 2017年1月12日
     */
	public Object addSysDictMain(HttpServletRequest req) {
		//从页面获取参数
		 String item_code = req.getParameter("item_code");
		 String item_code_old = req.getParameter("item_code_old");
		 String item_name= req.getParameter("item_name");
		 String py_code= req.getParameter("py_code");
		 String ordinal= req.getParameter("ordinal");
		 String item_describe= req.getParameter("item_describe");
		 String other_code= req.getParameter("other_code");
		 String state= req.getParameter("state");
		 String availability= req.getParameter("availability");
		 SysDictMain sysDictMain = new SysDictMain();
		 sysDictMain.setItem_code(item_code);
		 sysDictMain.setItem_code_old(item_code_old);
		 sysDictMain.setItem_name(item_name);
		 sysDictMain.setPy_code(py_code);
		 sysDictMain.setItem_describe(item_describe);
		 sysDictMain.setOther_code(other_code);
		 sysDictMain.setState(state);
		 if(!("").equals(availability)&&availability!=null){
		 sysDictMain.setAvailability(Integer.parseInt(availability));
		 }
		 int num =0;
		 if(!("").equals(ordinal)&&ordinal!=null){
			 sysDictMain.setOrdinal(Integer.parseInt(ordinal));
		 }
		 if(item_code_old.equals("")||item_code_old==null){
			 sysDictMain.setAvailability(1);
			  num = sysDictMainDao.add(sysDictMain);
		 }else{
			 num = sysDictMainDao.update(sysDictMain);
			 if(state.equals("X")){
				 SysDictSub sysDictSub = new SysDictSub();
				 sysDictSub.setSup_code(item_code_old);
				 sysDictSub.setState(state);
				 sysDictSubDao.update(sysDictSub);
			 }
		 }
			if(num>0){
				systemConfigInitListener.setServletContext(req.getSession().getServletContext());
				return StringUtil.returns(true, "操作成功");
			}else{
				return StringUtil.returns(false, "操作异常");
			}
	}
	public Object selectSysDictmainById(HttpServletRequest req) {
		 String item_code = req.getParameter("item_code");
		 SysDictMain sysDictMain = new SysDictMain();
		 sysDictMain.setItem_code(item_code);
		 SysDictMain sysDictone  = sysDictMainDao.selectByitem_code(item_code);
		 return sysDictone;
	}
}
