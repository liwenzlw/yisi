package com.ethink.msgentry.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMybatis {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		//初始化IOC容器
        ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
        //获取id为person0的bean
        //SqlSessionFactoryBean sqlSessionFactoryBean = (SqlSessionFactoryBean) cxt.getBean("sqlSessionFactory");
       // SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) cxt.getBean("sqlSessionFactory");
        SqlSession openSession = sqlSessionFactory.openSession();
        String statement = "com.ethink.msgentry.dao.DeliciousFoodDao.selectById";//映射sql的标识字符串
      //  DeliciousFood deliciousFood = openSession.selectOne(statement, 1);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        openSession.close();
        //System.out.println("所有的deliciousFood记录：" + deliciousFood.getContent());


	}

}
