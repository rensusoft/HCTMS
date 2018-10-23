package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.ApprovalOpinion;
import com.rensu.education.hctms.teach.bean.ReviewLeaveInfo;

import configuration.mapper.ReviewLeaveMapper;

@Repository("reviewLeaveDao")
public class ReviewLeaveDao extends BaseDao<ReviewLeaveInfo> {
	
	Logger log = Logger.getLogger(ReviewLeaveDao.class);
	
	@Autowired
	private ReviewLeaveMapper<ReviewLeaveInfo> reviewLeaveMapper;
	
	@Override
	public int add(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.add(reviewLeaveInfo);
	};
	
	@Override
	public int update(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.update(reviewLeaveInfo);
	};
	
	@Override
	public int delete(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.delete(reviewLeaveInfo);
	};
	
	@Override
	public ReviewLeaveInfo selectOne(int id) {
		return reviewLeaveMapper.selectOne(id);
	};
	
	@Override
	public List<ReviewLeaveInfo> selectList(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectList(reviewLeaveInfo);
	};
	
	@Override
	public List<ReviewLeaveInfo> selectPage(RowBounds rowBounds, ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectPage(rowBounds, reviewLeaveInfo);
	};
	
	@Override
	public int selectCount(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectCount(reviewLeaveInfo);
	};
	
	@Override
	public int getSequence() {
		return reviewLeaveMapper.getSequence();
	}
	
	/**
	 * 查询流程配置中某角色是否参与请假申请的审核
	 * @param create_roleid
	 * @return
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public Integer selectProcessInfoState(ReviewLeaveInfo reviewLeaveInfo){
		return reviewLeaveMapper.selectProcessInfoState(reviewLeaveInfo);
	}
	
	/**
	 * 查询流程配置中教学秘书是否参与请假申请的审核
	 * @param create_roleid
	 * @return
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public String selectProcessInfoStateOfDirector(int create_roleid){
		return reviewLeaveMapper.selectProcessInfoStateOfDirector(create_roleid);
	}
	
	/**
	 * 查询待审核的请假申请
	 * @param reviewLeaveInfo
	 * @param rowBounds
	 * @return List<ReviewLeaveInfo>
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public List<ReviewLeaveInfo> selectReviewLeave(RowBounds rowBounds, ReviewLeaveInfo reviewLeaveInfo){
		return reviewLeaveMapper.selectReviewLeave(rowBounds, reviewLeaveInfo);
	} 
	
	/**
	 * 查询教学秘书待审核的请假申请
	 * @param reviewLeaveInfo
	 * @param rowBounds
	 * @return List<ReviewLeaveInfo>
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public List<ReviewLeaveInfo> selectReviewLeaveOfDirector(RowBounds rowBounds, ReviewLeaveInfo reviewLeaveInfo){
		return reviewLeaveMapper.selectReviewLeaveOfDirector(rowBounds, reviewLeaveInfo);
	}
	
	/**
	 * 查询待审核的请假申请的条数
	 * @param reviewLeaveInfo
	 * @return int
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public int selectReviewLeaveCount(ReviewLeaveInfo reviewLeaveInfo){
		return reviewLeaveMapper.selectReviewLeaveCount(reviewLeaveInfo);
	} 
	
	/**
	 * 查询教学秘书待审核的请假申请的条数
	 * @param reviewLeaveInfo
	 * @return int
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public int selectReviewLeaveCountOfDirector(ReviewLeaveInfo reviewLeaveInfo){
		return reviewLeaveMapper.selectReviewLeaveCountOfDirector(reviewLeaveInfo);
	}
	
	/***
     * 根据ID查询待审批的申请详情
     * @param reviewLeaveInfo
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public ReviewLeaveInfo selectReviewLeaveById(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectReviewLeaveById(reviewLeaveInfo);
	}  
	
	/***
     * 根据ID查询教学秘书待审批的申请详情
     * @param reviewLeaveInfo
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public ReviewLeaveInfo selectReviewLeaveByIdOfDirector(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectReviewLeaveByIdOfDirector(reviewLeaveInfo);
	}
	
	/***
     * 提交同意的请假申请审批
     * @param approvalOpinion
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public int submitAgree(ApprovalOpinion approvalOpinion) {
		return reviewLeaveMapper.submitAgree(approvalOpinion);
	} 
	
	/***
     * 提交不同意的请假申请审批
     * @param approvalOpinion
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public int submitDisagree(ApprovalOpinion approvalOpinion) {
		return reviewLeaveMapper.submitDisagree(approvalOpinion);
	} 
	
	/***
     * 审批同意时，更改请假申请流程状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateStuVacateState(ApprovalOpinion approvalOpinion) {
		return reviewLeaveMapper.updateStuVacateState(approvalOpinion);
	} 
	
	/***
     * 审批不同意时，更改请假申请流程状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateStuVacateStateDis(ApprovalOpinion approvalOpinion) {
		return reviewLeaveMapper.updateStuVacateStateDis(approvalOpinion);
	}
	
	/***
     * 更改请假申请流程和审批人关系表的状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateProcessUserRelaState(ApprovalOpinion approvalOpinion) {
		return reviewLeaveMapper.updateProcessUserRelaState(approvalOpinion);
	} 
	
	/***
     * 判断教学秘书审批流程前面是否配置有其他的审批流程
     * @param reviewLeaveInfo
     * @return Integer
     * @author guocc
     * @date 2017年3月16日
     */
	public Integer getFlagOfIsFrist(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.getFlagOfIsFrist(reviewLeaveInfo);
	}
	
	/***
	 * 查询请假流程详细信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public List<ReviewLeaveInfo> selectLeaveProcessInfo(ReviewLeaveInfo reviewLeaveInfo) {
		return reviewLeaveMapper.selectLeaveProcessInfo(reviewLeaveInfo);
	}
	
	/***
	 * 查询请假申请的当前状态
	 * @param id
	 * @return Integer
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public Integer selectVacateStatus(int id) {
		return reviewLeaveMapper.selectVacateStatus(id);
	}
	
	/**
	 * @param proc_num
	 * @return ProcessInfo
	 * @author guocc
	 * @date 2017/3/24
	 */
	public ProcessInfo getVacateProcessOfTeacher(int proc_num) {
		return reviewLeaveMapper.getVacateProcessOfTeacher(proc_num);
	}
	
	/**
	 * @param proc_num
	 * @return List<Integer>
	 * @author guocc
	 * @date 2017/3/24
	 */
	public List<Integer> selectRoleId(int proc_num) {
		return reviewLeaveMapper.selectRoleId(proc_num);
	}
	
	/**
	 * @param roleId
	 * @return List<Integer>
	 * @author guocc
	 * @date 2017/3/24
	 */
	public List<Integer> selectAuthId(Integer roleId) {
		return reviewLeaveMapper.selectAuthId(roleId);
	}
}
