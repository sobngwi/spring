/**
 * 
 */
package audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */

@Aspect
public class ExecutionMonitoring {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionMonitoring.class);
	
	@Around("execution(* *(..))")
	public Object mesure(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch sw = new StopWatch(getClass().getSimpleName()) ;
		//long start = (new Date()).getTime() ;
		sw.start(pjp.getSignature().getDeclaringTypeName());
		Object retVal = pjp.proceed() ;
		//long  duration  = (new Date()).getTime() - start  ;
		sw.stop();
		LOGGER.info("[Duration] " +  pjp.getSignature().getDeclaringTypeName() +"."+ pjp.getSignature().getName() + "  took ****  [" + sw.getTotalTimeMillis() + "]  ****   ms" );
		//LOGGER.info(sw.prettyPrint());
		return retVal ;
	}
	

}
