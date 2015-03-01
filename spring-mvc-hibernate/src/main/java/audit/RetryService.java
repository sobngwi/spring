/**
 * 
 */
package audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.PessimisticLockingFailureException;

import com.sobngwi.hibernate.nsy135.service.ServiceHibernate;

/**
 * @author suse
 *
 */
public class RetryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHibernate.class);

	private static final int DEFAULT_MAX_RETRIES = 10;
    private int maxRetries = DEFAULT_MAX_RETRIES;
    
    public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}
	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable { 
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
           numAttempts++;
           try { 
        	   LOGGER.info(" Re- Trying --- " + numAttempts ) ;
              return pjp.proceed();
           }
           catch(PessimisticLockingFailureException ex) {
              lockFailureException = ex;
           }
        }
        while(numAttempts <= this.maxRetries);
        throw lockFailureException;
     }

}
