package configuration.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;


/**
 * StuAttendanceInfo Mapper
 * @author Administrator
 *
 */
public interface StuAttendanceInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<StuAttendanceInfo> selectListBiao(
			StuAttendanceInfo stuAttendanceInfo);

	public List<StuAttendanceInfo> getStuAttendanceInfoByAttendDate(
			StuAttendanceInfo stuAttendanceInfo);
	
	public int alreadyComplateAttendance(Map<String,Object> pMap);
}
