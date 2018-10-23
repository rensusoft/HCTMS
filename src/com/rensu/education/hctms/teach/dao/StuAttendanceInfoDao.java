package com.rensu.education.hctms.teach.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.util.DateUtil;

import configuration.mapper.StuAttendanceInfoMapper;
import configuration.mapper.TrainPlanMapper;

@Repository("stuAttendanceInfoDao")
public class StuAttendanceInfoDao extends BaseDao<StuAttendanceInfo> {
	
	Logger log = Logger.getLogger(StuAttendanceInfoDao.class);
	
	@Autowired
	private StuAttendanceInfoMapper<StuAttendanceInfo> stuAttendanceInfoMapper;
	@Autowired
	private TrainPlanMapper<TrainPlan> trainPlanMapper;
	
	
	@Override
	public int add(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.add(stuAttendanceInfo);
	};
	
	@Override
	public int update(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.update(stuAttendanceInfo);
	};
	
	@Override
	public int delete(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.delete(stuAttendanceInfo);
	};
	
	@Override
	public StuAttendanceInfo selectOne(int id) {
		return stuAttendanceInfoMapper.selectOne(id);
	};
	
	@Override
	public List<StuAttendanceInfo> selectList(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.selectList(stuAttendanceInfo);
	};
	
	@Override
	public List<StuAttendanceInfo> selectPage(RowBounds rowBounds, StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.selectPage(rowBounds, stuAttendanceInfo);
	};
	
	@Override
	public int selectCount(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.selectCount(stuAttendanceInfo);
	};
	
	@Override
	public int getSequence() {
		return stuAttendanceInfoMapper.getSequence();
	}

	public List<StuAttendanceInfo> selectListBiao(
			StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.selectListBiao(stuAttendanceInfo);
	}

	/**
	 * 查询学生的每日考勤状态
	 * @param stuAttendanceInfo
	 * @return StuAttendanceInfo
	 * @author guocc
	 * @date 2017年5月26日
	 */
	public List<StuAttendanceInfo> getStuAttendanceInfoByAttendDate(StuAttendanceInfo stuAttendanceInfo) {
		return stuAttendanceInfoMapper.getStuAttendanceInfoByAttendDate(stuAttendanceInfo);
	}
	
	public int alreadyComplateAttendance(Map<String,Object> pMap){
		return stuAttendanceInfoMapper.alreadyComplateAttendance(pMap);
	}
	
	public Map<String,Object> getStuAttListByOrgaId(int orgaId,int stuAuthId){
		Map<String,Object> rtnMap = new HashMap<String, Object>();
		List<StuAttendanceInfo> attList = new ArrayList<StuAttendanceInfo>();
		int sDate = -1;
		int eDate = -1;
		TrainPlan _tp = new TrainPlan();
		_tp.setStu_auth_id(stuAuthId);
		_tp.setTrain_dept_id(orgaId+"");
		_tp.setState("Y");
		List<TrainPlan> tpList = trainPlanMapper.selectList(_tp);
		if(tpList!=null&&tpList.size()>0){
			sDate = tpList.get(0).getTrain_start_date();
			eDate = tpList.get(0).getTrain_end_date();
			rtnMap.put("sDate", DateUtil.getFormatDateString(sDate+""));
			rtnMap.put("eDate", DateUtil.getFormatDateString(eDate+""));
		}
		int nowDate = Integer.parseInt(DateUtil.getCurrDate());
		//1、先判断轮转最后日期与当前日期做个比较，取哪一个
		if(nowDate<=eDate){
			eDate = nowDate;
		}
		System.out.println("--nowDate="+nowDate);
		System.out.println("--sDate="+sDate);
		System.out.println("--eDate="+eDate);
		//2、确定好时间段，开始根据每个日期去考勤表查每个日期的考勤情况
		int attNum = 0;
		int attNotNum = 0;
		int vNum = 0;
		while(sDate<=eDate){
			StuAttendanceInfo sai = new StuAttendanceInfo();
			sai.setAttend_time_str(DateUtil.getFormatDateString(eDate+""));
			//查询此日期的考勤情况
			StuAttendanceInfo _sai = new StuAttendanceInfo();
			_sai.setStu_auth_id(stuAuthId);
			_sai.setAttend_date(eDate);
			_sai.setOrga_id(orgaId);
			_sai.setState("Y");
			List<StuAttendanceInfo> saiList = stuAttendanceInfoMapper.selectList(_sai);
			if(saiList!=null&&saiList.size()>0){
				if(saiList.get(0).getAttend_state()==0){ //缺勤
					sai.setAttend_state_str("缺勤");
					attNotNum++;
				}else if(saiList.get(0).getAttend_state()==1){
					sai.setAttend_state_str("到勤");
					attNum++;
				}else if(saiList.get(0).getAttend_state()==2){
					sai.setAttend_state_str("请假");
					vNum++;
				}else{
					sai.setAttend_state_str("到勤");
					attNum++;
				}
			}else{
				sai.setAttend_state_str("到勤");
				attNum++;
			}
			attList.add(sai);
			String fl = DateUtil.addDate(eDate+"000000", -1,"yyyyMMdd");
			eDate = Integer.parseInt(fl);
		}
		rtnMap.put("attNum", attNum+"");
		rtnMap.put("attNotNum", attNotNum+"");
		rtnMap.put("vNum", vNum+"");
		rtnMap.put("attList", attList);
		return rtnMap;
	}
	
	
}
