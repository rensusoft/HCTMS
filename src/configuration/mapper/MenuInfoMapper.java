package configuration.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;


/**
 * 
 * @param <T>
 * @date 2016年4月20日
 * @autor chen xiaoxiao
 */
public interface MenuInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	/**
	 * 根据角色获取该角色对应的所有菜单。
	 * @param argMap
	 * @return
	 * @date 2016年4月26日
	 * @autor chen xiaoxiao
	 */
	public List<T> getMenusByRoleId(Map<String, Object> argMap);
}
