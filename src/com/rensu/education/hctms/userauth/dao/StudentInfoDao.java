package com.rensu.education.hctms.userauth.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.userauth.bean.StudentInfo;

import configuration.mapper.StudentInfoMapper;

@Repository("studentInfoDao")
public class StudentInfoDao extends BaseDao<StudentInfo> {
	
	Logger log = Logger.getLogger(StudentInfoDao.class);
	
	@Autowired
	private StudentInfoMapper<StudentInfo> studentInfoMapper;
	
	@Override
	public int add(StudentInfo studentInfo) {
		return studentInfoMapper.add(studentInfo);
	};
	
	@Override
	public int update(StudentInfo studentInfo) {
		return studentInfoMapper.update(studentInfo);
	};
	
	@Override
	public int delete(StudentInfo studentInfo) {
		return studentInfoMapper.delete(studentInfo);
	};
	
	@Override
	public StudentInfo selectOne(int id) {
		return studentInfoMapper.selectOne(id);
	};
	
	@Override
	public List<StudentInfo> selectList(StudentInfo studentInfo) {
		return studentInfoMapper.selectList(studentInfo);
	};
	
	@Override
	public List<StudentInfo> selectPage(RowBounds rowBounds, StudentInfo studentInfo) {
		return studentInfoMapper.selectPage(rowBounds, studentInfo);
	};
	
	public List<StudentInfo> selectPageWithStuType(RowBounds rowBounds, StudentInfo studentInfo) {
	    return studentInfoMapper.selectPageWithStuType(rowBounds, studentInfo);
	};
	
	@Override
	public int selectCount(StudentInfo studentInfo) {
		return studentInfoMapper.selectCount(studentInfo);
	};
	
	@Override
	public int getSequence() {
		return studentInfoMapper.getSequence();
	}

	public StudentInfo selectOneByUserCode(String user_code) {
		return studentInfoMapper.selectOneByUserCode(user_code);
	}

	public int insertWithList(List<StudentInfo> list) {
		return  studentInfoMapper.insertWithList(list);
	}

	public List<StudentInfo> selectStuList(StudentInfo studentInfo) {
		return studentInfoMapper.selectStuList(studentInfo);
	}
	
	public List<StudentInfo> selectStudentList(StudentInfo studentInfo) {
	    return studentInfoMapper.selectStudentList(studentInfo);
	}
	
	public int updateStateList(StudentInfo studentInfo) {
		return studentInfoMapper.updateStateList(studentInfo);
	}
	
	public int updateStuStateList(StudentInfo studentInfo) {
	    return studentInfoMapper.updateStuStateList(studentInfo);
	}
	
	public List<StudentInfo> countStatusList(StudentInfo studentInfo) {
		return studentInfoMapper.countStatusList(studentInfo);
	}
	public int updateStuInfomation(StudentInfo studentInfo) {
		return studentInfoMapper.updateStuInfomation(studentInfo);
	}

	public List<StudentInfo> selectPageInfo(StudentInfo studentInfo) {
		return studentInfoMapper.selectPageInfo(studentInfo);
	}

	public List<StudentInfo> absenceStuInfo(StudentInfo studentInfo) {
		return studentInfoMapper.absenceStuInfo(studentInfo);
	}

	public StudentInfo selectStuNumByAuthId(Integer stu_auth_id) {
		return studentInfoMapper.selectStuNumByAuthId(stu_auth_id);
	}

	public List<StudentInfo> selectListStu(StudentInfo studentInfo) {
		return studentInfoMapper.selectListStu(studentInfo);
	}

	public List<StudentInfo> selectStudent() {
		return studentInfoMapper.selectStudent();
	}

	public StudentInfo selectStuAuthId(StudentInfo studentInfo) {
		
		return studentInfoMapper.selectStuAuthId(studentInfo);
	}

	public StudentInfo selectStuAuthIdByStuName(String stuName) {
		
		return studentInfoMapper.selectStuAuthIdByStuName(stuName);
	}

	public StudentInfo selectStuNameByAuthId(Integer auth_id) {
		return studentInfoMapper.selectStuNameByAuthId(auth_id);
	}

	public StudentInfo selectUserAndAuth(Integer id) {
		return studentInfoMapper.selectUserAndAuth(id);
	}

	public StudentInfo selectStuTscId(Integer stu_auth_id) {
		return studentInfoMapper.selectStuTscId(stu_auth_id);
	}

	public Object updateStuentState(StudentInfo studentInfo) {
		return studentInfoMapper.updateStuentState(studentInfo);
	}

	public Object updateStuInfo(StudentInfo studentInfo) {
		return studentInfoMapper.updateStuInfo(studentInfo);
	}

	public Object updateUserInfo(StudentInfo studentInfo) {
		return studentInfoMapper.updateUserInfo(studentInfo);
	}

	public Object updateUserAuth(StudentInfo studentInfo) {
		return studentInfoMapper.updateUserAuth(studentInfo);
	}

	public StudentInfo selectStuStateYTscId(int stuAuthId) {
		return studentInfoMapper.selectStuStateYTscId(stuAuthId);
	}
}
