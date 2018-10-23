package configuration.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.userauth.bean.RoleInfo;


/**
 * RoleInfo Mapper
 * @author Administrator
 *
 */
public interface RoleInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	public List<T> loginByCodePsd(Map<String,Object> pramMap);

	public List<RoleInfo> getAllRoleInfo();

	public List<RoleInfo> selectTeacherList();
}
