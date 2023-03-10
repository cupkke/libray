package com.java.expressage.uilt;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;




public class D {
	private static SqlSessionFactory ssf;
	private static ThreadLocal<SqlSession> threadlocal = new ThreadLocal<SqlSession>();
	private static Logger log = LogManager.getLogger(D.class);
	static {
		try {
			String resource="mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("配置文件读取失败！");
			throw new RuntimeException("配置文件读取失败！");
		}
	}
	//创建数据库连接
	public static SqlSession getConn() {
		SqlSession sqlSession=threadlocal.get();
		if(sqlSession==null) {
			sqlSession=ssf.openSession();
			threadlocal.set(sqlSession);
		}
		log.info("创建数据库连接："+sqlSession);
		return sqlSession;
	}
	/**
	 * 关闭数据库连接
	 */
	public static void closeConn() {
		SqlSession sqlSession=threadlocal.get();
		log.info("关闭数据库连接："+sqlSession);
		if(sqlSession!=null) {
			sqlSession.commit();
			sqlSession.close();
			threadlocal.remove();
		}
	}
}
