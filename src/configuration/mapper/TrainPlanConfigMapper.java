package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;


/**
 * TrainPlanConfig Mapper
 * @author Administrator
 *
 */
public interface TrainPlanConfigMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<TrainPlanConfig> findRotaryDepartment(
			TrainPlanConfig trainPlanConfig);

	public void updatebyTsc_id(TrainPlanConfig trainPlanConfig);
	//批量添加数据
	public int insertMany(List<TrainPlanConfig> list);

	public List<TrainPlanConfig> selectOrgaInfo(TrainPlanConfig trainPlanConfig);

	public TrainPlanConfig getDeptId(TrainPlanConfig config);

	public List<TrainPlanConfig> selectOrgaByTscId(TrainPlanConfig trainPlanConfig);
}
