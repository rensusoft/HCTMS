package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.message.bean.MessageReceive;


/**
 * MessageReceive Mapper
 * @author Administrator
 *
 */
public interface MessageReceiveMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<MessageReceive> selectPageAll(RowBounds rowBounds,
			MessageReceive messageReceive);

	public int selectCountByLei(MessageReceive messageReceive);

	public int addMany(List<MessageReceive> lists);

	public List<MessageReceive> selectListAll(MessageReceive messageReceive);

	public int updateState(MessageReceive messageReceive);

	public List<MessageReceive> getTodo(MessageReceive messageReceive);

	public List<MessageReceive> getNews(MessageReceive messageReceive);

	public List<MessageReceive> selectNewsProgress_state0(
			MessageReceive messageReceive);
	
	public int updateAllMsgStateByAuthId(MessageReceive messageReceive);

}
