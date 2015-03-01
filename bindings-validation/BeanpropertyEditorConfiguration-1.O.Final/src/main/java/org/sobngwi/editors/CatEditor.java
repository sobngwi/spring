/**
 * 
 */
package org.sobngwi.editors;

import java.beans.PropertyEditorSupport;

import org.sobngwi.animal.Special;

/**
 * @author Alain Narcisse SOBNGWI
 *
 */
public class CatEditor extends PropertyEditorSupport {
	
	@Override
	 public void setAsText(String text)  {
       
		Special special = new Special() ;
		special.setValue(text);
		setValue(special);
		
    }

}
