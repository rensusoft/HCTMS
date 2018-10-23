package com.rensu.education.hctms.basicdata.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.core.utils.SystemConfigInitListener;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.StringUtil;
import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.basicdata.bean.RoleProcRela;
import com.rensu.education.hctms.basicdata.bean.RprFormRela;
import com.rensu.education.hctms.basicdata.dao.FormInfoDao;
import com.rensu.education.hctms.basicdata.dao.ProcessInfoDao;
import com.rensu.education.hctms.basicdata.dao.RoleProcRelaDao;
import com.rensu.education.hctms.basicdata.dao.RprFormRelaDao;


@Service("roleProcRelaService")
public class RoleProcRelaService extends BaseService<RoleProcRela> {
	
	Logger log = Logger.getLogger(RoleProcRelaService.class);
	
	@Autowired
	protected RoleProcRelaDao roleProcRelaDao;
	@Autowired
	protected ProcessInfoDao processInfoDao;
	@Autowired
	private SystemConfigInitListener systemConfigInitListener;
	@Autowired
	private FormInfoDao formInfoDao;
	@Autowired
	private RprFormRelaDao rprFormRelaDao;

	
	/***
	 * 根据type_code查询流程关联角色
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月15日
	 */
	public Object selectProcessRole(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String name="";
		String role_ids="";
		String ids="";
		String type_code = req.getParameter("type_code");
		String end_proc_id = req.getParameter("end_proc_id");
		RoleProcRela  roleProcRela   =  new RoleProcRela();
		roleProcRela.setType_code(type_code);
		if(end_proc_id!=null && !end_proc_id.equals("")){
			roleProcRela.setEnd_proc_id(Integer.parseInt(end_proc_id));
		}
		List<RoleProcRela> roleList = roleProcRelaDao.selectProcessRole(roleProcRela);
		for(int i=0;i<roleList.size();i++){
			if(i+1<roleList.size()){
			   if(roleList.get(i).getProc_num()==roleList.get(i+1).getProc_num()){
				name+=roleList.get(i).getRole_name()+",";
				role_ids+=roleList.get(i).getRole_id()+",";
				ids+=roleList.get(i).getId()+",";
				roleList.remove(i);
				i=i-1;
				continue;
     			}
			}
			name+=roleList.get(i).getRole_name();
			role_ids+=roleList.get(i).getRole_id();
			ids+=roleList.get(i).getId();
			roleList.get(i).setName(name);
			roleList.get(i).setRole_ids(role_ids);
			roleList.get(i).setIds(ids);
			name="";
			role_ids="";
			ids="";
		}
		if(end_proc_id!=null && !end_proc_id.equals("")){
			return StringUtil.returns(true, roleList);
		}
		FormInfo formInfo= new FormInfo();
		String  str="";
		for (RoleProcRela roleProcRela2 : roleList) {
			formInfo.setEnd_proc_id(roleProcRela2.getEnd_proc_id());
			formInfo.setState("Y");
			List<FormInfo> list= formInfoDao.formRelation(formInfo);
			for (int i = 0;i<list.size();i++) {
				if(i==0){
					str=list.get(i).getName();
				}else{
					str=str+","+list.get(i).getName();
				}
			}
			roleProcRela2.setForm_name(str);
			str="";
		}
		mv.setViewName("web/basicdata/flowCont2");
		mv.addObject("roleList",roleList);
		mv.addObject("type_code",type_code);
		return mv;
	}

	/***
	 * 禁用、启用流程
	 * @param req
	 * @return
	 * @author hezx
	 * @date 2017年2月17日
	 */
	public Object updateProcessState(HttpServletRequest req) {
		String state = req.getParameter("state");
		String id = req.getParameter("end_proc_id");
		String role_ids = req.getParameter("role_ids");
		String proc_name = req.getParameter("proc_name");
		String type_ids = req.getParameter("type_ids");
		String from_types = req.getParameter("from_types");
		String rpr_id = req.getParameter("rpr_id");
		String type_code = req.getParameter("type_code");
		String require_datenum = req.getParameter("require_datenum");
		
		ProcessInfo  processInfo =  new ProcessInfo();
		if(id!=null&&!id.equals("")){
			processInfo.setId(Integer.parseInt(id));
			processInfo.setState(state);
			if(proc_name!=null&&!proc_name.equals("")){
				processInfo.setProc_name(proc_name);
			}
		    int num =processInfoDao.update(processInfo);
		    if(num>0){
				//从缓存中得到ProcessInfo的信息
				List<ProcessInfo> list = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
				List<ProcessInfo> processInfoList = new ArrayList<ProcessInfo>();
				for (ProcessInfo processInfo2 : list) {
					if(processInfo2.getType_code().equals(type_code)){
						processInfoList.add(processInfo2);
					}
				}
				//对processInfoList根据proc_num进行排序
				Collections.sort(processInfoList, new Comparator<ProcessInfo>() {
					public int compare(ProcessInfo arg0, ProcessInfo arg1) {
						Integer code0 = arg0.getProc_num();
						Integer code1 = arg1.getProc_num();
						return code0.compareTo(code1);
					}
				});
				RoleProcRela roleProcRela= new RoleProcRela();
				//启用和禁用 重整roleProcRela表里数据(启用和禁用 按钮)
		       if(state!=""&&state!=null){
		    	   roleProcRela.setEnd_proc_id(Integer.parseInt(id));
		    	   roleProcRela.setState(state);
		    	   roleProcRelaDao.updateByEnd(roleProcRela);  
		    	   roleProcRela.setState("");
		    	   //更新
		    	   if(state.equals("X")){
		    		   roleProcRela.setStart_proc_id(Integer.parseInt(id));
		    		   List<RoleProcRela>  roleProcList= roleProcRelaDao.selectListByprocId(roleProcRela);
		    		 for(int i =1;i<processInfoList.size()-1;i++){
		    			if(processInfoList.get(i).getId()==Integer.parseInt(id)){
		    		        for(int z =1;z<roleProcList.size();z++){
		    		           if(roleProcList.get(z).getStart_proc_id()==Integer.parseInt(id))
								roleProcRela.setStart_proc_id(processInfoList.get(i-1).getId());
		    		           roleProcRela.setEnd_proc_id(processInfoList.get(i+1).getId());
								roleProcRelaDao.updateByEnd(roleProcRela); 
		    		      }  
		    		   }
		    		 }
				   }else if(state.equals("Y")){
					   //更新缓存数据
					     systemConfigInitListener.setServletContext(req.getSession().getServletContext());
					 //从缓存中得到ProcessInfo的信息
						List<ProcessInfo> listY = (List<ProcessInfo>)req.getSession().getServletContext().getAttribute(Consts.SESSION_PROCESS_INFO);
						List<ProcessInfo> processInfoListY = new ArrayList<ProcessInfo>();
						for (ProcessInfo processInfo2 : listY) {
							if(processInfo2.getType_code().equals(type_code))
								processInfoListY.add(processInfo2);
						}
						//对processInfoList根据proc_num进行排序
						Collections.sort(processInfoListY, new Comparator<ProcessInfo>() {
							public int compare(ProcessInfo arg0, ProcessInfo arg1) {
								Integer code0 = arg0.getProc_num();
								Integer code1 = arg1.getProc_num();
								return code0.compareTo(code1);
							}
						});
						int start_id=0;
						int end_id=0;
						roleProcRela.setO_end_proc_id(Integer.parseInt(id));
						for(int i =1;i<processInfoListY.size();i++){
							if(processInfoListY.get(i).getId()==Integer.parseInt(id)){
								start_id=processInfoListY.get(i-1).getId();
								if(i<processInfoListY.size()-1)
								end_id=processInfoListY.get(i+1).getId();
						   roleProcRela.setStart_proc_id(start_id);
						   roleProcRela.setEnd_proc_id(end_id);
							}
						}
			    		   List<RoleProcRela>  roleProcList= roleProcRelaDao.selectListByprocId(roleProcRela);
						for(int z =0;z<roleProcList.size();z++){
						   if(roleProcList.get(z).getEnd_proc_id()==end_id){
								roleProcRela.setStart_proc_id(Integer.parseInt(id));
								roleProcRela.setEnd_proc_id(end_id);
								roleProcRelaDao.updateByEnd(roleProcRela); 
						   }else  if(roleProcList.get(z).getEnd_proc_id()==Integer.parseInt(id)){
							   roleProcRela.setStart_proc_id(start_id);
							   roleProcRela.setEnd_proc_id(Integer.parseInt(id));
							   roleProcRelaDao.updateByEnd(roleProcRela); 
						   }
				    	}	
		    	    }
		          }
		       //编辑时重整roleProcRela表里数据 （编辑按钮）
		       if(role_ids!=null&&!role_ids.equals("")){
					roleProcRela.setEnd_proc_id(Integer.parseInt(id));//结束流程的ID
					   for (int i=1;i<processInfoList.size();i++) {
			    		   if(processInfoList.get(i).getId()==Integer.parseInt(id))
			    		   roleProcRela.setStart_proc_id(processInfoList.get(i-1).getId());
					   }
					//清空该步骤对应的角色 的数据
					roleProcRelaDao.deleteByEnd(roleProcRela);
					roleProcRela.setState(state);//状态
					if(require_datenum!=""&&require_datenum!=null)
						roleProcRela.setRequire_datenum(Integer.parseInt(require_datenum));
					String[] role_idss = role_ids.split(","); 
					//删除流程对应的所有数据
					RprFormRela rprFormRela= new RprFormRela();
					if(rpr_id!=null&&!rpr_id.equals("")){
						rprFormRela.setRpr_ids(rpr_id);
					}
					rprFormRelaDao.deleteByRpr_id(rprFormRela);
					//角色循环 创建角色对应的流程
					for(int i =0; i <role_idss.length;i++){
						//创建ID
						int ids = roleProcRelaDao.getSequence();
						roleProcRela.setId(ids);
						//角色的ID
						roleProcRela.setRole_id(Integer.parseInt(role_idss[i]));
						roleProcRelaDao.add(roleProcRela);
						rprFormRela.setState("Y");
						rprFormRela.setRpr_id(ids);
						if(type_ids!=null&&!type_ids.equals("")){
							String[] type_idss = type_ids.split(",");
							String[] from_typess = from_types.split(",");
							for(int z =0; z <type_idss.length;z++){
								rprFormRela.setId(rprFormRelaDao.getSequence());
								rprFormRela.setType_id(Integer.parseInt(type_idss[z]));
								if(from_typess[z]!=null&&!from_typess[z].equals("")){
									rprFormRela.setForm_type(Integer.parseInt(from_typess[z]));
								}
								rprFormRelaDao.add(rprFormRela);
							}
						}
					}
			    }
		    }
				 //更新缓存数据
			     systemConfigInitListener.setServletContext(req.getSession().getServletContext());
			     return StringUtil.returns(true, "操作成功");

		  }else{
			return StringUtil.returns(false, "操作异常");
		}
	}

//	public Object selectFormInfo(HttpServletRequest req) {
//		String namestr = req.getParameter("namestr");
//		
//		String state = req.getParameter("state");
//		FormInfo formInfo= new FormInfo();
//		formInfo.setNamestr(namestr);
//		formInfo.setState(state);
//		List<FormInfo> list= formInfoDao.selectFormInfo(formInfo);
////		System.out.println(namestr);
//		return StringUtil.returns(true,list);
//		
//	}
	public Object selectFormInfo(HttpServletRequest req)
	{
		String namestr=req.getParameter("namestr");
	
		String state=req.getParameter("state");
		FormInfo formInfo=new FormInfo();
		System.out.println(namestr);
		formInfo.setNamestr(namestr);
		formInfo.setState(state);
		List<FormInfo> list=formInfoDao.selectFormInfo(formInfo);
		
		return StringUtil.returns(true, list);
		
	}

	public Object formRelation(HttpServletRequest req) {
		String end_proc_id = req.getParameter("end_proc_id");
		String state = req.getParameter("state");
		FormInfo formInfo= new FormInfo();
		if(end_proc_id!=null&&!end_proc_id.equals("")){
			formInfo.setEnd_proc_id(Integer.parseInt(end_proc_id));
			formInfo.setState(state);
			List<FormInfo> list= formInfoDao.formRelation(formInfo);
			return StringUtil.returns(true,list);
		}else{
			return StringUtil.returns(false,"异常错误");
		}
	}
}
