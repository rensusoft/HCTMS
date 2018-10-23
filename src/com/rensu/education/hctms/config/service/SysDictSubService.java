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
import com.rensu.education.hctms.config.bean.SysDictSub;
import com.rensu.education.hctms.config.dao.SysDictSubDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("sysDictSubService")
public class SysDictSubService extends BaseService<SysDictSub> {
	
	Logger log = Logger.getLogger(SysDictSubService.class);
	
	@Autowired
	protected SysDictSubDao sysDictSubDao;
	@Autowired
	protected SystemConfigInitListener systemConfigInitListener;
	
	/***
	 *  系统字典详情 分页查询
	 * @param pageIndex
	 * @param rows
	 * @param orgaInfo
	 * @return
	 * @author hezx
	 * @date 2017年1月12日
	 */
	public JSONObject selectPage(int pageIndex, int rows,SysDictSub sysDictSub) {
		JSONObject jobj = new JSONObject();
		List<SysDictSub> dataList = sysDictSubDao.selectPage(
				new RowBounds((pageIndex - 1) * rows, pageIndex * rows),
				sysDictSub);
		int totalCount = sysDictSubDao.selectCount(sysDictSub);
		jobj.put("total", StringUtil.getMaxInt(totalCount, rows));// 总页数
		jobj.put("page", pageIndex);// 当前页码
		jobj.put("records", totalCount);// 总的记录数
		jobj.put("rows", dataList);// 显示的具体数据，jsonarray格式。
		return jobj;
	}

	/***
	 * 系统字典明细 新增
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年1月13日
	 */
	public Object addSysDictSub(HttpServletRequest req) {
		//从页面获取参数
		 String sup_code = req.getParameter("sup_code");
		String item_type_code= req.getParameter("item_type_code");
		String item_type_code_old= req.getParameter("item_type_code_old");
		 String item_type_name= req.getParameter("item_type_name");
		 String ordinal= req.getParameter("ordinal");
		 String state= req.getParameter("state");
		 SysDictSub sysDictSub = new SysDictSub();
		 sysDictSub.setSup_code(sup_code);
		 sysDictSub.setItem_type_code(item_type_code);
		 sysDictSub.setItem_type_name(item_type_name);
		 sysDictSub.setState(state);
		 int num = 0;
		    if(!ordinal.equals("")&&ordinal!=null){
			 sysDictSub.setOrdinal(Integer.parseInt(ordinal));
		    }
		    if(item_type_code_old.equals("")||item_type_code_old==null){
			 num = sysDictSubDao.add(sysDictSub);
		    }else{
			 sysDictSub.setItem_type_code_old(item_type_code_old);
			 num = sysDictSubDao.update(sysDictSub);
		    }
		 if(num>0){
			 systemConfigInitListener.setServletContext(req.getSession().getServletContext());
				return StringUtil.returns(true, "操作成功");
		 }else{
				return StringUtil.returns(false, "操作异常");
			}
	}

}
