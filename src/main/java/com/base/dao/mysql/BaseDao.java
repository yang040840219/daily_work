/**
 * ProjectName:  mall-framework
 * FileName:  BaseDao.java
 * PackageName:  com.cyou.mall.framework.base
 * Copyright (c) 2013, CYOU All Rights Reserved.
*/
package com.base.dao.mysql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.base.dao.Pagination;


/**
 * ClassName:  BaseDao. <br/>
 * Description:  公共DAO <br/>
 * 2013-3-1 下午10:23:46 <br/>
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao<T extends Serializable> extends BaseSqlSessionDaoSupport{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 子类直接覆盖，用于设置sqlSessionTemplate<br>
	 * 尽量直接设定sessionTemplate，防止配置被覆盖<br>
	 * 
	 * @param sqlSessionFactory
	 * @author zoudongchao 多个数据源的时候有用
	 * @since 2013-9-12 下午5:01:12
	 */
	 @Resource(name="sqlSessionFactory")
	 public  void setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
		 super.setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory));
	 };
	
	/**
	 *
	 * TODO 功能描述 <br/>
	 * @param statement
	 * @param entity
	 * @return
	 * 2013-4-7 下午5:06:30
	 */
	public int save(final String statement,final List<T> list){
		Assert.notNull(statement, "mapper statement 不能为空");
		logger.debug("method[save], params[statement:{"+statement+"} ");
		return getSqlSession().insert(statement,list);
	}
	/**
	 * 保存实体 <br/>
	 * @param statement mapper中statement的id
	 * @param entity    mapper中statement的参数 entity
	 * @return 影响的行数
	 * 2013-3-2 下午6:41:46
	 */
	public int save(final String statement,final T entity){
		Assert.notNull(statement, "mapper statement 不能为空");
		Assert.notNull(entity, "entity不能为空");
		logger.debug("method[save], params[statement:{"+statement+"} ,entity: {"+entity+"}]");
		return getSqlSession().insert(statement, entity);
	}
	
	/**
	 * 保存实体，并返回id， 需要通过 selectKey 形式
	 * @param statement
	 * @param entity
	 * @return
	 */
//	public T saveAndGetId(final String statement,final T entity){
//		Assert.notNull(statement, "mapper statement 不能为空");
//		Assert.notNull(entity, "entity不能为空");
//		logger.debug("method[save], params[statement:{"+statement+"} ,entity: {"+entity+"}]");
//		return getSqlSession().
//	}
	
	/**
	 * 删除实体 <br/>
	 * @param statement mapper中statement的id
	 * @param entity	mapper中statement的参数 entity
	 * @return 影响的行数
	 * 2013-3-2 下午6:43:21
	 */
	public int delete(final String statement,final T entity){
		Assert.notNull(statement, "mapper statement 不能为空");
		Assert.notNull(entity, "entity不能为空");
		logger.debug("method[delete],params[statement: {"+statement+"} ,entity:{"+entity+"}]");
		return getSqlSession().delete(statement, entity);
	}
	/**
	 * 根据id删除实体 <br/>
	 * @param statement mapper中statement的id
	 * @param id        mapper中statement的参数实体id
	 * @return 影响的行数
	 * 2013-3-2 下午6:43:52
	 */
	public int delete(final String statement,final Number id){
		Assert.notNull(statement, "mapper statement 不能为空");
		Assert.notNull(id, "id不能为空");
		logger.debug("method[delete],params[statement:{"+statement+"} ,entity: {"+id+"}]");
		return getSqlSession().delete(statement,id);
	}
	
	public int delete(final String statement,final Map<String,Object> parameters){
		Assert.notNull(statement, "mapper statement 不能为空");
		logger.debug("method[getList], params[statement:{"+statement+"} ,parameterMap: {"+parameters+"}]");
		return getSqlSession().delete(statement, parameters);
	}
	
	/**
	 * 修改实体 <br/>
	 * @param statement mapper中statement的id
	 * @param entity	mapper中statement的参数entity
	 * @return 影响的行数
	 * 2013-3-2 下午6:44:40
	 */
	public int update(final String statement){
		Assert.notNull(statement, "mapper statement 不能为空");
		return this.getSqlSession().update(statement);
	}
	/**
	 * 修改实体 <br/>
	 * @param statement mapper中statement的id
	 * @param entity	mapper中statement的参数entity
	 * @return 影响的行数
	 * 2013-3-2 下午6:44:40
	 */
	public int update(final String statement,final T entity){
		Assert.notNull(statement, "mapper statement 不能为空");
		Assert.notNull(entity, "entity不能为空");
		logger.debug("method[update], params[statement:{"+statement+"} ,entity: {"+entity+"}]");
		return this.getSqlSession().update(statement, entity);
	}
	/**
	 * 修改实体 <br/>
	 * @param statement mapper中statement的id
	 * @param parameters mapper中statement的参数map
	 * @return
	 * 2013-3-2 下午6:45:24
	 */
	public int update(final String statement,final Map<String,Object> parameters){
		Assert.notNull(statement, "mapper statement 不能为空");
		logger.debug("method[update], params[statement:{"+statement+"} ,parameterMap: {"+parameters+"}]");
		return this.getSqlSession().update(statement, parameters);
	}
    
	public <T extends Serializable> T getCount(final String statement,final Number id){
		return  (T) getSqlSession().selectOne(statement, id);
	}
	/**
	 * 根据条件查询唯一实体 <br/>
	 * @param statement mapper中statement的id
	 * @param entity  mapper中statement的参数entity
	 * @return
	 * 2013-3-2 下午6:47:10
	 */
	public T getUnique(final String statement,final T entity){
		logger.debug("method[getUnique], params[statement:{"+statement+"} ,entity: {"+entity+"}]");
		return (T) getSqlSession().selectOne(statement, entity);
	}
	/**
	 * 根据条件查询唯一实体  <br/>
	 * @param statement	mapper中statement的id
	 * @param parameters	mapper中statement的参数entity
	 * @return
	 * 2013-3-2 下午6:48:19
	 */
	public T getUnique(final String statement,final Map<String,Object> parameters){
		logger.debug("method[getUnique], params[statement:{"+statement+"} ,parameterMap: {"+parameters+"}]");
		return (T) getSqlSession().selectOne(statement, parameters);
	}
	/**
	 * 查询实体 <br/>
	 * @param statement  mapper中statement的id
	 * @return
	 * 2013-3-2 下午6:48:58
	 */
	public List<T> getList(final String statement){
		logger.debug("method[getList], params[statement:{"+statement+"} ]");
		return this.getSqlSession().selectList(statement);
	}
	/**
	 * 根据条件查询 <br/>
	 * @param statement mapper中statement的id
	 * @param entity	mapper中statement的参数entity
	 * @return
	 * 2013-3-2 下午6:49:35
	 */
	public List<T> getList(final String statement,final T entity){
		logger.debug("method[getList], params[statement:{"+statement+"} ,entity: {"+entity+"}]");
		return this.getSqlSession().selectList(statement,entity);
	}
	/**
	 * 根据条件查询 <br/>
	 * @param statement mapper中statement的id
	 * @param parameters mapper中statement的参数map
	 * @return
	 * 2013-3-2 下午6:49:35
	 */
	public List<T> getList(final String statement,final Map<String,Object> parameters){
		logger.debug("method[getList], params[statement:{"+statement+"} ,parameterMap: {"+parameters+"}]");
		return this.getSqlSession().selectList(statement,parameters);
	}

	/**
	 * 分页查询 <br/>
	 * @param statement	mapper中statement的id
	 * @param pageNumber 当前要查询的页码
	 * @param pageSize	每页最大条数
	 * @return
	 * 2013-3-2 下午6:50:20
	 */
	public <T extends Serializable> Pagination<T> findPage(final String statement,final int pageNumber, final int pageSize){
		logger.debug("method[findPage], params[statement:{"+statement+"} ,pageNumber:{"+pageNumber+"} ,pageSize:{"+pageSize+"}]");
		Pagination<T> pagination = new Pagination<T>();
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		List<T> list = this.getSqlSession().selectList(statement,  pagination);
		pagination.setList(list);
		return pagination;
	}
	/**
	 * 分页查询<br/>
	 * @param statement mapper中statement的id
	 * @param parameters  mapper中statement的参数map
	 * @param pageNumber 当前要查询的页码
	 * @param pageSize 每页最大条数
	 * @return
	 * 2013-3-2 下午6:51:29
	 */
	public <T extends Serializable> Pagination<T> findPage(final String statement,final Map<String,Object> parameters, final int pageNumber, final int pageSize){
		logger.debug("method[findPage], params[statement:{"+statement+"} ,parameterMap:{"+parameters+"} ,pageNumber:{"+pageNumber+"} ,pageSize:{"+pageSize+"}]");
		Pagination<T> pagination = new Pagination<T>();
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		parameters.put(Pagination.MAP_PAGE_FIELD, pagination);
		List<T> list = this.getSqlSession().selectList(statement,  parameters);
		pagination.setList(list);
		return pagination;
	}
	
	public <T extends Serializable> Pagination<T> findPageWithTotalSql(final String statement,final Map<String,Object> parameters, final int pageNumber, final int pageSize,
			final String totalStatement,final Map<String,Object> totalParameters){
		logger.debug("method[findPage], params[statement:{"+statement+"} ,parameterMap:{"+parameters+"} ,pageNumber:{"+pageNumber+"} ,pageSize:{"+pageSize+"}" +
				"totalStatement:{"+totalStatement+"} ,totalParameters:{"+totalParameters+"}]");
		Pagination<T> pagination = new Pagination<T>();
		pagination.setCurrentPage(pageNumber);
		pagination.setPageSize(pageSize);
		int count = this.count(totalStatement, totalParameters);
		if(count>0){
			pagination.setTotalCount(count);
			if(parameters!=null){
				parameters.put(Pagination.MAP_PAGE_FIELD, pagination);
				List<T> list = this.getSqlSession().selectList(statement,  parameters);
				pagination.setList(list);
			}else{
				List<T> list = this.getSqlSession().selectList(statement,  pagination);
				pagination.setList(list);
			}
			pagination.init(count,pageSize, pageNumber);
		}
		
		return pagination;
	}
	
	
	/**
	 * 根据条件统计数量 <br/>
	 * @param statment
	 * @param params
	 * @return
	 * 2013-4-7 下午9:05:08
	 */
	public int count(final String statement,final Map<String,Object> parameters){
		logger.debug("method[findPage], params[statement:{"+statement+"} ,parameterMap:{"+parameters+"}]");
		return ((Number) this.getSqlSession().selectOne(statement, parameters)).intValue();
	}
	/**
	 * 根据条件统计数量 <br/>
	 * @param statment
	 * @param params
	 * @return
	 * 2013-4-7 下午9:05:08
	 */
	public Number statis(final String statement,final Map<String,Object> parameters){
		logger.debug("method[findPage], params[statement:{"+statement+"} ,parameterMap:{"+parameters+"}]");
		return (Number) this.getSqlSession().selectOne(statement, parameters);
	}
}

