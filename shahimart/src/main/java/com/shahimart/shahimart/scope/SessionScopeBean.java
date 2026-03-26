package com.shahimart.shahimart.scope;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@Setter
@Getter
@SessionScope
public class SessionScopeBean {
    private String userName;
}
