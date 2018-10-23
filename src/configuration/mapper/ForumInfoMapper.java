package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.publicdata.bean.ForumInfo;
import com.rensu.education.hctms.publicdata.bean.ForumSub;


/**
 * ForumInfo Mapper
 * @author Administrator
 *
 */
public interface ForumInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	public List<ForumInfo> selectForumsList(ForumInfo forumInfo);
	
	public ForumInfo selectReplyCount(ForumInfo forumInfo);
	
	public ForumInfo selectForumsById(ForumInfo forumInfo);
	
	public List<ForumSub> selectReplyInfo(ForumInfo forumInfo);
	
	public ForumSub selectCiteInfo(ForumSub forumSub);
}
