package org.sobngwi.jpa.audit;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareBean implements AuditorAware<String> {
    public String getCurrentAuditor() {
        return "prospring4";
    }
}
