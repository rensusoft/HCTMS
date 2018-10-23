package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.userauth.bean.UserAuthority;


/**
 * UserAuthority Mapper
 * @author Administrator
 *
 */
public interface UserAuthorityMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public int updateState(int authId);

	public List<UserAuthority> selectTeacherList(UserAuthority userAuthority);

	public UserAuthority selectOneNameByAuth(Integer teacherId);
	
	public T selectStuByAuthId(UserAuthority userAuthority);
	
	public int updateOrga_id(UserAuthority userAuthority);

	public List<UserAuthority> selectMessagePeo(UserAuthority userAuthority);
}
