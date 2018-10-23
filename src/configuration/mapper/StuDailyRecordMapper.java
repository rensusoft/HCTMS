package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.StuDailyRecord;


/**
 * StuDailyRecord Mapper
 * @author Administrator
 *
 */
public interface StuDailyRecordMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	//分页查询培训日志
	public List<StuDailyRecord> selectDailyRecord(RowBounds rowBounds, StuDailyRecord stuDailyRecord);

	public List<StuDailyRecord> selectReviewedDaily(RowBounds rowBounds,
			StuDailyRecord stuDailyRecord);

	public StuDailyRecord selectDailyRecordById(Integer id);
	
	public int updateAllStuDRState(StuDailyRecord stuDailyRecord);
	
}
