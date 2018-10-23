package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.basicdata.bean.RoleProcRela;


/**
 * RoleProcRela Mapper
 * @author Administrator
 *
 */
public interface RoleProcRelaMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<T> selectAuthIdList(T t);

	public List<T> selectProcessRole(T t);

	public int deleteByEnd(T t);

	public int deleteByIds(T t );

	public int updateByEnd(T t);

	public List<T> selectListByprocId(T t);

	public List<T> selectRoleId(int end_id);

	public List<RoleProcRela> selectRoleProc();
}
