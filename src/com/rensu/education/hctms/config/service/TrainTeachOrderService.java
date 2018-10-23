package com.rensu.education.hctms.config.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.util.StringUtil;


@Service("trainTeachOrderService")
public class TrainTeachOrderService extends BaseService<TrainTeachOrder> {
	
	Logger log = Logger.getLogger(TrainTeachOrderService.class);
	
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	
	 /***
	  * 新增STU_TEACH_ORDER  中的信息
	  * @param req
	  * @return
	  * @author hezx
	  * @date 2017年3月14日
	  */
	public Object insTrainTeaOrder(HttpServletRequest req) {
		String tpc_id=req.getParameter("id");
		String names=req.getParameter("names");
		String type_ids=req.getParameter("type_ids");
		String nums=req.getParameter("nums");
		//去掉字符串第一个，并分割字符串
		String[] neme=names.substring(1,names.length()).split(",");
		String[] type_id=type_ids.substring(1,type_ids.length()).split(",");
		String[] num=nums.substring(1,nums.length()).split(",");
		int id = 0;
		int number = 0;
		trainTeachOrderDao.deleteByTpc(Integer.parseInt(tpc_id));
		for(int i=0;i<type_id.length;i++){
			TrainTeachOrder trainTeachOrder = new TrainTeachOrder();
			trainTeachOrder.setData_type_id(Integer.parseInt(type_id[i]));
			trainTeachOrder.setTpc_id(Integer.parseInt(tpc_id));
			trainTeachOrder.setOrder_name(neme[i]);			
			trainTeachOrder.setState("Y");
			trainTeachOrder.setSort_num(i);
//			if(Integer.parseInt(type_id[i])==1||Integer.parseInt(type_id[i])==2||Integer.parseInt(type_id[i])==4){
				trainTeachOrder.setId(trainTeachOrderDao.getSequence());
				trainTeachOrder.setOrder_num(Integer.parseInt(num[i]));
				trainTeachOrder.setOrder_num_unit("份");
				trainTeachOrder.setType_id(0);
				number +=trainTeachOrderDao.add(trainTeachOrder);
				   continue;
//			}else if(Integer.parseInt(num[i])==-1){
//				id=trainTeachOrderDao.getSequence();
//				trainTeachOrder.setId(id);
//				trainTeachOrder.setType_id(1);
//				number +=trainTeachOrderDao.add(trainTeachOrder);
//				   continue;
//			}else{
//				trainTeachOrder.setId(trainTeachOrderDao.getSequence());
//				trainTeachOrder.setOrder_num_unit("份");
//				trainTeachOrder.setType_id(2);
//				trainTeachOrder.setSup_id(id);
//				trainTeachOrder.setOrder_num(Integer.parseInt(num[i]));
//				number +=trainTeachOrderDao.add(trainTeachOrder);
//				   continue;
//			}
		}
		if(number>0){
			return StringUtil.returns(true, "操作成功！");
		}else{
			return StringUtil.returns(false, "操作失败！");
		}
	}
	
	/***
	 * 轮转大纲展示页面的信息
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年3月14日
	 */
	public Object outlineExhibition(HttpServletRequest req) {
		String tpc_id=req.getParameter("id");
		List<TrainTeachOrder>  stuTeachOrderList=null;
		if(!tpc_id.equals("")&&tpc_id!=null){
			stuTeachOrderList = trainTeachOrderDao.outlineExhibition(Integer.parseInt(tpc_id));	
		}
		return StringUtil.returns(true, stuTeachOrderList);
	}

	public List<TrainTeachOrder> selectTTOList(TrainTeachOrder trainTeachOrder) {
		// TODO Auto-generated method stub
		return trainTeachOrderDao.selectTTOList(trainTeachOrder);
	}
	

}
