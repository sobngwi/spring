package com.sobngwi.hibernate.nsy135.service;

import javax.management.MXBean;
import org.hibernate.stat.Statistics;
/**
* Exposes the {@link Statistics} contract as JMX resource.
*
* @author Alain Narcisse SOBNGWI.
*/
@MXBean
public interface StatisticsService extends Statistics {
}
