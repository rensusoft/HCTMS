package configuration.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.userauth.bean.UserInfo;
import com.rensu.education.hctms.userauth.bean.VUserDetailInfo;


/**
 * UserInfo Mapper
 * @author Administrator
 *
 */
public interface UserInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int updateByUserCode(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	public List<T> loginByCodePsd(Map<String,Object> pramMap);

	public List<UserInfo> getAllUserInfo();
	/**
	 * 查询相关人物  相关的角色信息
	 * @param td
	 * @return
	 *@author huq
	 *@date 2017年1月3日
	 */
	public List<VUserDetailInfo> selectBytpId(UserInfo td);
	/**
	 * 模糊查询   用户的
	 * @param rowBounds
	 * @param user
	 * @return
	 *@author huq
	 *@date 2017年1月4日
	 */
	public List<UserInfo> selectPageByMoHu(RowBounds rowBounds, UserInfo user);
	/**
	 * 查询所有可用的用户总量
	 * @param t
	 * @return
	 *@author huq
	 *@date 2017年1月16日
	 */
	public int selectKeYonCount(T t) ;

	public T selectTeacher(T t);

	public List<UserInfo> selectOrgaTea(T t);

	public List<UserInfo> findAllUserName(UserInfo userInfo);
	
	public List<UserInfo> findAllUserNameAndId(UserInfo userInfo);//guocc模糊查询

	public List<UserInfo> selectTeachersList(UserInfo userInfo);

	public UserInfo selecAuthIdBy(UserInfo info);

	public List<UserInfo> selectTeaList(UserInfo info);
	
}
