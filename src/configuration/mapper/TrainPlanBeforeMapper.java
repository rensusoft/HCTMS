package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.userauth.bean.StudentInfo;


/**
 * TrainPlanBefore Mapper
 * @author Administrator
 *
 */
public interface TrainPlanBeforeMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	public int selectTrainCount(T t) ;
	
	public int deleteList(T t) ;
	
	public List<TrainPlanBefore> selectTPBList(TrainPlanBefore trainPlanBefore);
	
	public int deleteTPBList(TrainPlanBefore trainPlanBefore);
	
	public TrainPlanBefore getTscIdFromTPBefore(StudentInfo studentInfo);

	public int insertWithList(List<TrainPlanBefore> list);
}
