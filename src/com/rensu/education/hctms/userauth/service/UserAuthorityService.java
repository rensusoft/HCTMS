package com.rensu.education.hctms.userauth.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.login.bean.LoginBean;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.RoleInfo;
import com.rensu.education.hctms.userauth.bean.Tree;
import com.rensu.education.hctms.userauth.bean.UserAuthority;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.dao.UserAuthorityDao;
import com.rensu.education.hctms.util.Consts;

@Service("userAuthorityService")
public class UserAuthorityService extends BaseService<UserAuthority> {
	
	Logger log = Logger.getLogger(UserAuthorityService.class);
	
	@Autowired
	protected UserAuthorityDao userAuthorityDao;
	@Autowired
	protected OrgaInfoDao orgaInfoDao;
	

	
	
	/**
	 * 用户权限保存操作。
	 * @param req
	 * @return
	 * @date 2016年5月17日
	 * @autor chen xiaoxiao
	 */
    public int userAuthSave(HttpServletRequest req) {
		int res = 0;
		UserAuthority userAuth = new UserAuthority();	
		Timestamp d = new Timestamp(System.currentTimeMillis());
		userAuth.setAuth_id(userAuthorityDao.getSequence());
		userAuth.setUser_id(Integer.parseInt(req.getParameter("userId")));
		userAuth.setRole_id(Integer.parseInt(req.getParameter("roleId")));
		userAuth.setOrga_id(Integer.parseInt(req.getParameter("orgaId")));
		userAuth.setCreate_time(d);
		userAuth.setState("Y");
		res=userAuthorityDao.add(userAuth);
		return res;
	}




	/**
	 * 用户表权限删除操作
	 * @param authId
	 * @param req
	 * @return
	 *@author huq
	 *@date 2017年1月4日
	 */
	public boolean userAuthDel(int authId) {
		UserAuthority userAuthority=new UserAuthority();
		userAuthority.setAuth_id(authId);
		userAuthority.setState("X");
		int i=userAuthorityDao.update(userAuthority);
		if(i==0){
			return false;
		}
		return true;
	}
	
	
	public Object getPeoByOrga(HttpServletRequest req) {
		Map<String, Object> map=new HashMap<String, Object>();
		String senderAuthIds=req.getParameter("senderAuthIds");
		//得到医院所有科室
		LoginBean loginBean = (LoginBean) req.getSession().getAttribute(Consts.SESSION_LOGIN_KEY);
		Integer roleId=loginBean.getvUserDetailInfo().getRole_id();  //角色id
		Integer orgaId=loginBean.getvUserDetailInfo().getOrga_id();  //科室ID
		Integer authId=loginBean.getvUserDetailInfo().getAuth_id();  //权限ID
		Integer userId=loginBean.getvUserDetailInfo().getUser_id();  //用户ID
		OrgaInfo orgaInfo = new OrgaInfo();
		orgaInfo.setState("Y");
		List<Tree> lstTree = new ArrayList<Tree>();
		List<RoleInfo> roleList = new ArrayList<RoleInfo>();
		List<OrgaInfo> orgalist = new ArrayList<OrgaInfo>();
		//如果是科教科（临床教学处）
		if(roleId==50){
			//得到医院所有的科室
			orgalist = orgaInfoDao.selectList(orgaInfo);
			OrgaInfo all= new OrgaInfo();
			all.setOrga_name("全部");
			all.setSup_id(-100);
			all.setOrga_id(-100);
			orgalist.add(all);
			//ztree科室层
			lstTree = orgaZtreeMade(orgalist);
			//角色层
			RoleInfo kzrRole= new RoleInfo();
			kzrRole.setRole_name("科主任");
			kzrRole.setRole_id(40);
			roleList.add(kzrRole);
			RoleInfo ksRole= new RoleInfo();
			ksRole.setRole_name("教学秘书");
			ksRole.setRole_id(30);
			roleList.add(ksRole);
			RoleInfo teaRole= new RoleInfo();
			teaRole.setRole_name("带教老师");
			teaRole.setRole_id(20);
			roleList.add(teaRole);
			RoleInfo stuRole= new RoleInfo();
			stuRole.setRole_name("学生");
			stuRole.setRole_id(10);
			roleList.add(stuRole);
			lstTree = roleZtreeMade(orgalist,roleList,lstTree,senderAuthIds,userId);
		}else if(roleId==40 || roleId==10){
			OrgaInfo all= new OrgaInfo();
			all.setOrga_name("全部");
			all.setSup_id(-1);
			all.setOrga_id(orgaId);
			orgalist.add(all);
			lstTree = orgaZtreeMade(orgalist);
			RoleInfo ksRole= new RoleInfo();
			ksRole.setRole_name("教学秘书");
			ksRole.setRole_id(30);
			roleList.add(ksRole);
			RoleInfo teaRole= new RoleInfo();
			teaRole.setRole_name("带教老师");
			teaRole.setRole_id(20);
			roleList.add(teaRole);
			RoleInfo stuRole= new RoleInfo();
			stuRole.setRole_name("学生");
			stuRole.setRole_id(10);
			roleList.add(stuRole);
			lstTree = roleZtreeMade(orgalist,roleList,lstTree,senderAuthIds,userId);
		}else if(roleId==30){
			OrgaInfo all= new OrgaInfo();
			all.setOrga_name("全部");
			all.setSup_id(-1);
			all.setOrga_id(orgaId);
			orgalist.add(all);
			lstTree = orgaZtreeMade(orgalist);
			RoleInfo teaRole= new RoleInfo();
			teaRole.setRole_name("带教老师");
			teaRole.setRole_id(20);
			roleList.add(teaRole);
			RoleInfo stuRole= new RoleInfo();
			stuRole.setRole_name("学生");
			stuRole.setRole_id(10);
			roleList.add(stuRole);
			lstTree = roleZtreeMade(orgalist,roleList,lstTree,senderAuthIds,userId);
		}else if(roleId==20){
			OrgaInfo all= new OrgaInfo();
			all.setOrga_name("全部");
			all.setSup_id(-1);
			all.setOrga_id(orgaId);
			orgalist.add(all);
			lstTree = orgaZtreeMade(orgalist);
			RoleInfo stuRole= new RoleInfo();
			stuRole.setRole_name("学生");
			stuRole.setRole_id(10);
			roleList.add(stuRole);
			lstTree = roleZtreeMade(orgalist,roleList,lstTree,senderAuthIds,userId);
		}
		map.put("lstTree", lstTree);
		map.put("success", true);
		return map;
	}
	
	
	public List<Tree> orgaZtreeMade(List<OrgaInfo> tosList) {
		List<Tree> lstTree = new ArrayList<Tree>();
		for (int i = 0; tosList != null && i < tosList.size(); i++) {
			Tree t = new Tree();
			t.setpId(-100);
			t.setName(tosList.get(i).getOrga_name());
			if(-100==tosList.get(i).getOrga_id()&&null!=tosList.get(i).getSup_id()&&-100==tosList.get(i).getSup_id()){
				t.setId(-100);
				t.setOpen(true);
				t.setIcon("img/2.png");
			}else{
				t.setId(-1*tosList.get(i).getOrga_id());
				t.setIcon("img/1_close.png");
			}
			lstTree.add(t);
		}
		return lstTree;
	}
	
	public List<Tree> roleZtreeMade(List<OrgaInfo> tosList,List<RoleInfo> roleList,List<Tree> lstTree,String senderAuthIds,int userId) {
		if(tosList.size()==1){
			for (int j = 0; roleList != null && j < roleList.size(); j++) {
			Tree t = new Tree();
			t.setId(Integer.parseInt(-1*+roleList.get(j).getRole_id()+""+tosList.get(0).getOrga_id()));
			if(-1*tosList.get(0).getOrga_id()>0){
				t.setpId(-100);
			}else{
				t.setpId(-1*tosList.get(0).getOrga_id());
			}
			t.setName(roleList.get(j).getRole_name());
			t.setIcon("img/4.png");
			lstTree.add(t);
			lstTree=peoZtreeMade(tosList.get(0).getOrga_id(),roleList.get(j).getRole_id(),lstTree,senderAuthIds,userId);
			}
		}else {
		for (int i = 0; tosList != null && i < tosList.size(); i++) {
		if(tosList.get(i).getOrga_id()!=-100){
		for (int j = 0; roleList != null && j < roleList.size(); j++) {
		Tree t = new Tree();
		t.setId(Integer.parseInt(-1*roleList.get(j).getRole_id()+""+tosList.get(i).getOrga_id()));
		t.setpId(-1*tosList.get(i).getOrga_id());
		t.setName(roleList.get(j).getRole_name());
		t.setIcon("img/4.png");
		lstTree.add(t);
		lstTree=peoZtreeMade(tosList.get(i).getOrga_id(),roleList.get(j).getRole_id(),lstTree,senderAuthIds,userId);
			}
		}
		}
	}
	return lstTree;
}

public List<Tree> peoZtreeMade(int hosOrgaId,int roleId,List<Tree> lstTree,String senderAuthIds,int userId) {
	UserAuthority userAuthority = new UserAuthority();
	userAuthority.setRole_id(roleId);
	userAuthority.setOrga_id(hosOrgaId);
	userAuthority.setState("Y");
	List<UserAuthority>  tosList=userAuthorityDao.selectMessagePeo(userAuthority);
	for (int i = 0; tosList != null && i < tosList.size(); i++) {
		if(tosList.get(i).getUser_id()!=userId){
		Tree t = new Tree();
		t.setId(tosList.get(i).getAuth_id());
		t.setpId(Integer.parseInt(-1*roleId+""+hosOrgaId));
		t.setName(tosList.get(i).getUser_name());
		t.setIcon("img/9.png");
		if(null!=senderAuthIds&&!("").equals(senderAuthIds)){
			if (senderAuthIds.contains(",")) {
				String[] data = senderAuthIds.split(",");
				for (int j = 0; j < data.length; j++) {
					if ((t.getId()+"").equals(data[j])) {
						t.setChecked(true);
					}
				}
			} else {
				if ((t.getId()+"").equals(senderAuthIds)) {
					t.setChecked(true);
				}
				
			}
		  }
		lstTree.add(t);
		}
	}
	return lstTree;
}
	
}
