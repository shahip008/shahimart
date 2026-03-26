package com.shahimart.shahimart.scope;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@ApplicationScope
@Getter
@Slf4j
public class ApplicationScopeBean {
    private int visitorCount;

    public void incrementVisitorCount() {
        visitorCount++;
    }

    public int getVisitorCount() {
       return visitorCount;
    }

    public ApplicationScopeBean(){
        log.info("ApplicationScopeBean initialized");
    }
}
