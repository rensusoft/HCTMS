package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;


/**
 * TrainTeachOrder Mapper
 * @author Administrator
 *
 */
public interface TrainTeachOrderMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public int deleteByTpc(int tpc_id);

	public List<TrainTeachOrder> outlineExhibition(int tpc_id);

	public List<TrainTeachOrder> selectTTOList(TrainTeachOrder trainTeachOrder);
	
	public List<TrainTeachOrder> selectTrainTeachOrderList(TrainPlan trainPlan);
}
