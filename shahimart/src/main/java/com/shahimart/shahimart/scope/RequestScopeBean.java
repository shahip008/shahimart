package com.shahimart.shahimart.scope;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@org.springframework.web.context.annotation.RequestScope
public class RequestScopeBean {
    private String userName;
}
