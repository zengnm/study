package com.share.example.dao;

import com.jd.dbs.DBSqlSessionTemplate;
import com.jd.dbs.datasources.IDataSourceService;
import com.share.example.domain.BaseDomain;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Dao层基础类（建议所有DaoImpl都继承此类）
 * @author suihonghua
 *
 */
public abstract class BaseDao {

	private static final String SQLID_SEPARATOR = ".";// namespace中连接类全名和sqlId的分隔符
	
	@Resource
	private SqlSession sqlSession;
	
	protected String getSqlId(String sqlId) {
		return getSqlId(this.getClass(), sqlId);
	}

	@SuppressWarnings("rawtypes")
	protected static String getSqlId(Class clazz, String sqlId) {
		if (clazz == null || sqlId == null) {
			return null;
		}
		return clazz.getName() + SQLID_SEPARATOR + sqlId;
	}
	
	/**
     * 获取数据库连接
     * @author suihonghua
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws Exception{
    	Connection conn = null;
    	SqlSession current_sql_session = this.getSqlSession();
    	if (current_sql_session instanceof DBSqlSessionTemplate) {
			IDataSourceService dataSource = ((DBSqlSessionTemplate) current_sql_session).getDataSourceService();
			Map<String, DataSource> mDateSource = dataSource.getDataSources();
			conn = DataSourceUtils.getConnection(mDateSource.get(dataSource.getDefaultIdentity()));
		} else {
			conn = current_sql_session.getConnection();
		}
    	return conn;
    }
    
    /**
     * 获取配置信息
     * @author suihonghua
     * @return
     * @throws Exception
     */
    protected Configuration getConfiguration() throws Exception{
    	SqlSession sql_session=null;
    	
    	SqlSession current_sql_session = this.getSqlSession();
    	if (current_sql_session instanceof DBSqlSessionTemplate) {
			sql_session = ((DBSqlSessionTemplate) current_sql_session).getDefaultSession();
		} else {
			sql_session = current_sql_session;
		}
    	Configuration conf = sql_session.getConfiguration();
    	return conf;
    }
	
	/**
	 * 填充创建时间和更新时间
	 * @param model
	 */
	protected void fillCreateTimeAndUpdateTime(BaseDomain model) {
		Date date = new Date();
		model.setCreateTime(date);
		model.setUpdateTime(date);
	}
	
	/**
	 * 批量填充创建时间和更新时间
	 * @param list
	 */
	protected void batchFillCreateTimeAndUpdateTime(List<?> list) {
		Date date = new Date();
		for(Object obj: list) {
			BaseDomain model = (BaseDomain) obj;
			model.setCreateTime(date);
			model.setUpdateTime(date);
		}
	}
	
	/**
	 * 填充更新时间
	 * @param model
	 */
	protected void fillUpdateTime(BaseDomain model) {
		Date date = new Date();
		model.setUpdateTime(date);
	}
	
	/**
	 * 批量填充更新时间
	 * @param list
	 */
	protected void batchFillUpdateTime(List<?> list) {
		Date date = new Date();
		for(Object obj: list) {
			BaseDomain model = (BaseDomain) obj;
			model.setUpdateTime(date);
		}
	}
	
	/**
     * 插入操作
     * 
     * @author suihonghua
     * @param statement
     * @param parameter
     * @return
     * @throws Exception
     */
    protected int insert(String statement, Object parameter){
		return this.getSqlSession().insert(statement, parameter);
	}
    
    /**
     * 更新操作
     * 
     * @author suihonghua
     * @param statement
     * @param parameter
     * @return
     * @throws Exception
     */
    protected int update(String statement, Object parameter){
		return this.getSqlSession().update(statement, parameter);
	}
    
    /**
     * 删除操作
     * 
     * @author suihonghua
     * @param statement
     * @param parameter
     * @return
     * @throws Exception
     */
    protected int delete(String statement, Object parameter){
		return this.getSqlSession().delete(statement, parameter);
	}
	
	/**
	 * 查询第一条记录(如果结果集不为空，则返回第一条记录)
	 * 
	 * @author suihonghua
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected Object selectFirst(String statement,Object parameter){
		List returnList = this.selectList(statement, parameter, 0, 1);
		if(returnList == null || returnList.size() == 0){
			return null;
		}else{
			return returnList.get(0);
		}
	}
	
	/**
	 * 查询单条记录
	 * 
	 * @author suihonghua
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	protected Object selectOne(String statement,Object parameter){
		return this.getSqlSession().selectOne(statement, parameter);
	}
	
	/**
	 * 
	 * @author suihonghua
	 * @param statement
	 * @param parameter
	 * @param mapKey
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected Map selectMap(String statement, Object parameter, String mapKey){
		return this.getSqlSession().selectMap(statement, parameter, mapKey);
	}
	
	/**
	 * 查询列表
	 * 
	 * @author suihonghua
	 * @param statement
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected List selectList(String statement, Object parameter){
		return this.getSqlSession().selectList(statement,parameter);
	}
	
	/**
	 * 支持 limit 语法查询， offset <  [rownum] <= (offset+rows)
	 * 
	 * @author suihonghua
	 * @param statement
	 * @param parameter
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	protected List selectList(String statement, Object parameter, int offset, int limit){
		return this.getSqlSession().selectList(statement, parameter, new RowBounds(offset, limit));
	}

    /**
     * 获取映射信息
     * @author suihonghua
     * @param statement
     * @return
     * @throws Exception
     */
    protected MappedStatement getMappedStatement(String statement)  throws Exception{
        return this.getConfiguration().getMappedStatement(statement);
    }

    protected int selectCount(String statement, Object parameter){
        return (null == this.selectOne(statement, parameter)) ? 0 : (Integer)this.selectOne(statement, parameter);
    }

    //---------- getter and setter ----------//
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
}
