package com.sobngwi.hibernate.nsy135.service;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterMbean implements IRegisterMbean  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHibernate.class);
	
	@Autowired
	EntityManagerFactory emf ;
	/**
	 * 
	 */
	public RegisterMbean() {
		super();
		}
	
	public  void registerHibernateMBeans() {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		try {
		MBeanServer mbeanServer
		= ManagementFactory.getPlatformMBeanServer();
		ObjectName on
		= new ObjectName("Hibernate:type=statistics,application=hibernatestatistics");
		StatisticsService mBean = new DelegatingStatisticsService(sessionFactory.getStatistics());
		mBean.setStatisticsEnabled(true); // alternative is to enable it in persistence.xml
		mbeanServer.registerMBean(mBean, on);
		} catch (MalformedObjectNameException ex) {
			LOGGER.error("", ex);
		} catch (InstanceAlreadyExistsException ex) {
			LOGGER.error("", ex);
		} catch (MBeanRegistrationException ex) {
			LOGGER.error("", ex);
		} catch (NotCompliantMBeanException ex) {
			LOGGER.error("", ex);
		}
		}

}
