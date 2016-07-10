package com.base.dao.mongo;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ReflectionUtils;

import com.base.dao.Pagination;
import com.mongodb.WriteResult;



/**
 * mongod
 * 
 */
public abstract class MongoBaseDao<T extends Serializable> {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 通过条件查询,查询分页结果
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public Pagination<T> findByPage(int pageNumber, int pageSize, Query query) {
		long totalCount = this.mongoTemplate.count(query, this.getEntityClass());
		int total = Integer.parseInt(String.valueOf(totalCount));
		return findByPage(pageNumber, pageSize, query, total);
	}
	
	
	/**
	 * 查询位置最近,距离设置最大值(查询不考虑)
	 * 分页采用的是 传递的Query 进行计算总数
	 * 返回的 Pagination<T>  T 如果有 setDistance 方法，会把
	 * @param pageNumber
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public Pagination<T> findPageGeoNear(int pageNumber, int pageSize, Query query,Point point) {
		
		long totalCount = this.mongoTemplate.count(query, this.getEntityClass());
		int total = Integer.parseInt(String.valueOf(totalCount));
		if(pageNumber <=0){
			pageNumber = 1 ;
		}
		Pageable pageable = new PageRequest(pageNumber-1,pageSize);
		NearQuery nearQuery = NearQuery.near(point);
		// 设置查询距离
		//nearQuery.maxDistance(new Distance(Integer.MAX_VALUE, Metrics.KILOMETERS));
		nearQuery = nearQuery.query(query).spherical(true).in(Metrics.KILOMETERS);
		nearQuery = nearQuery.with(pageable);
		GeoResults<T> data  = mongoTemplate.geoNear(nearQuery,this.getEntityClass());
		// 分页设置
		Pagination<T> page = new Pagination<T>(total, pageSize, pageNumber);
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		page.init(total, pageSize, pageNumber);
		//page.getPageNumberList();
		
		List<T> list = new ArrayList<T>();
		Method [] methods = this.getEntityClass().getMethods();
		Method method = null ;
		for(Method m:methods){
			if(m.getName().equalsIgnoreCase("setDistance")){
				method = m ;
				break;
			}
		}
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		for(GeoResult<T> g : data){
			list.add(g.getContent());
			if(method != null){
				double value = g.getDistance().getValue() ;
				ReflectionUtils.invokeMethod(method,g.getContent(),Double.parseDouble(decimalFormat.format(value)));
			}
		}
	    page.setList(list);
	             
		return page;
	}

	
	/**
	 * 查询位置最近,距离设置最大值(查询不考虑)
	 * 分页采用的是 传递的Query 进行计算总数
	 * 返回的 Pagination<T>  T 如果有 setDistance 方法，会把
	 * @param pageNumber
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public List<T> findGeoNear(Query query,Point point) {
		NearQuery nearQuery = NearQuery.near(point);
		// 设置查询距离
		//nearQuery.maxDistance(new Distance(Integer.MAX_VALUE, Metrics.KILOMETERS));
		nearQuery = nearQuery.query(query).spherical(true).in(Metrics.KILOMETERS);
		GeoResults<T> data  = mongoTemplate.geoNear(nearQuery,this.getEntityClass());
		List<T> list = new ArrayList<T>();
		Method [] methods = this.getEntityClass().getMethods();
		Method method = null ;
		for(Method m:methods){
			if(m.getName().equalsIgnoreCase("setDistance")){
				method = m ;
				break;
			}
		}
		DecimalFormat decimalFormat=new DecimalFormat(".00");
		for(GeoResult<T> g : data){
			list.add(g.getContent());
			if(method != null){
				double value = g.getDistance().getValue() ;
				ReflectionUtils.invokeMethod(method,g.getContent(),Double.parseDouble(decimalFormat.format(value)));
			}
		}
	             
		return list ;
	}
	
	
	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param query
	 * @param totalCount
	 * @return
	 */
	public Pagination<T> findByPage(int pageNumber, int pageSize, Query query,int totalCount) {
		if(pageNumber <=0){
			pageNumber = 1 ;
		}
		Pagination<T> page = new Pagination<T>(totalCount, pageSize, pageNumber);
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		page.init(totalCount, pageSize, pageNumber);
		query.skip((page.getCurrentPage() -1) * page.getPageSize());// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录
		List<T> datas = this.find(query);
		page.setList(datas);
		return page;
	}
	
		
	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 查询出所有数据
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return this.mongoTemplate.findAll(getEntityClass());
	}
	public List<T> findByIds(String[] ids){
		Query query = new Query(Criteria.where("id").in(ids));
		return this.find(query);
	}
	public List<T> findByIds(Collection<String> ids){
		Query query = new Query(Criteria.where("id").in(ids));
		return this.find(query);
	}
	/**
	 * 查询并且修改记录
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public T findAndModify(Query query, Update update) {
		return this.mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}

	/**
	 * 按条件查询,并且删除记录
	 * 
	 * @param query
	 * @return
	 */
	public void findAndRemove(Query query) {
		this.mongoTemplate.remove(query, this.getEntityClass());
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public void updateFirst(Query query, Update update) {
		mongoTemplate.updateFirst(query, update, this.getEntityClass());
	}
	public void updateMulti(Query query,Update update){
		mongoTemplate.updateMulti(query, update, this.getEntityClass());
	}
	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param bean
	 * @return
	 */
	public T save(T bean) {
		mongoTemplate.save(bean);
		return bean;
	}
	/**
	 * 
	* @Title: save 
	* @Description: 批量保存对象
	* @param @param beans    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void save(Collection<T> beans){
		mongoTemplate.insertAll(beans);
	}
	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName
	 *            集合名
	 * @return
	 */
	public T findById(String id, String collectionName) {
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}
	
	public long count(Query query){
		return mongoTemplate.count(query, this.getEntityClass());
	}
	public long count(){
		return mongoTemplate.count(new Query(), this.getEntityClass());
	}
	public void removeAll(){
		Query query = new Query(new Criteria());
		mongoTemplate.remove(query, this.getEntityClass());
	}
	public void removeByIds(String[] ids){
		Query query = new Query(Criteria.where("id").in(ids));
		mongoTemplate.remove(query, this.getEntityClass());
	}
	public int removeByIds(Collection<String> ids){
		Query query = new Query(Criteria.where("id").in(ids));
		WriteResult wr = mongoTemplate.remove(query, this.getEntityClass());
		return wr.getN();
	}
	public int removeByQuery(Query query){
		WriteResult wr = mongoTemplate.remove(query, this.getEntityClass());
		return wr.getN();
	}
	
	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected  Class<T> getEntityClass(){
		Class<T> clazz = (Class<T>)((ParameterizedType)getClass()   
                .getGenericSuperclass()).getActualTypeArguments()[0];
		return clazz ;
	};
	
	/**
	 * 聚合查询
	 * 
	 * @param aggregation 聚合条件
	 * @param collectionName 集合名
	 * @return
	 */
	public AggregationResults<T> findObjectByAggregation(Aggregation aggregation, String collection) {
		return mongoTemplate.aggregate(aggregation, collection, this.getEntityClass());
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
	/**
	 * spring mongodb　集成操作类　
	 */
	@Resource(name="mongoTemplate")
	public MongoTemplate mongoTemplate;
}
