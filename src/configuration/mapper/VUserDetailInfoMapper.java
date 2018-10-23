package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;


/**
 * VUserDetailInfo Mapper
 * @author Administrator
 *
 */
public interface VUserDetailInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	/**
	 * 查询所有部门
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	public List<VUserDetailInfo> findAllOrga();
	/**
	 * 查询所有角色
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	public List<VUserDetailInfo> findAllRole();
	
	

}
