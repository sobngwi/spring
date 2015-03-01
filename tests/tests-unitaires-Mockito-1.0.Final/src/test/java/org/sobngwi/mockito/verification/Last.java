/**
 * 
 */
package org.sobngwi.mockito.verification;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */


import java.util.Arrays;
import java.util.List;

import org.mockito.exceptions.Reporter;
import org.mockito.exceptions.verification.VerificationInOrderFailure;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.verification.VerificationMode;



public class Last implements VerificationMode {
	
	private final InvocationsFinder finder = new InvocationsFinder();
	private final InvocationMarker marker = new InvocationMarker();
	private final Reporter reporter = new Reporter();
	
	
	public Last() {
		
	}

	public static VerificationMode last( ) {
		return new Last();
	}

	@Override
	public void verify(VerificationData data) {
		List<Invocation> invocations = data.getAllInvocations();
		InvocationMatcher matcher = data.getWanted();
	
		List<Invocation> chunk = finder.findInvocations(invocations, matcher);
        int last = invocations.size()-1  ;
      	if (invocations.size() == 0 || chunk.size() == 0) {
			reporter.wantedButNotInvoked(matcher);
		} else if (!sameInvocation(invocations.get(last), chunk.get(0))) {			
			reportNotLast(chunk.get(0), invocations.get(last));
		}
		marker.markVerified(chunk.get(0), matcher);	

	}

	private boolean sameInvocation(Invocation left, Invocation right) {
		if (left == right) {
			return true;			
		}	
		return left.getMock().equals(right.getMock()) && left.getMethod().equals(right.getMethod()) && Arrays.equals(left.getArguments(), right.getArguments());
	}
	
	private void reportNotLast(Invocation wanted, Invocation unwanted) {
		StringBuilder message = new StringBuilder();
		message.append("\nWanted last :\n").append(wanted).append("\n").append(new LocationImpl());
		message.append("\nInstead got:\n").append(unwanted).append("\n").append(unwanted.getLocation()).append("\n");
		throw new VerificationInOrderFailure(message.toString());
		
	}
}
