package com.shahimart.shahimart.controller;

import com.shahimart.shahimart.scope.ApplicationScopeBean;
import com.shahimart.shahimart.scope.RequestScopeBean;
import com.shahimart.shahimart.scope.SessionScopeBean;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {
    private final RequestScopeBean requestScopeBean;
    private final SessionScopeBean sessionScopeBean;
    private final ApplicationScopeBean applicationScopeBean;
    @GetMapping("/request")
    public ResponseEntity<String> testRequestScope(){
        requestScopeBean.setUserName("John Deere");
        return ResponseEntity.ok().body(requestScopeBean.getUserName());
    }
    @GetMapping("/testRequest")
    public ResponseEntity<String> testScope(){
        return ResponseEntity.ok().body(requestScopeBean.getUserName());
    }

    @GetMapping("/session")
    public ResponseEntity<String> testSessionScope(){
        sessionScopeBean.setUserName("John Deere");
        return ResponseEntity.ok().body(sessionScopeBean.getUserName());
    }
    @GetMapping("/testSession")
    public ResponseEntity<String> testSession(){
        return ResponseEntity.ok().body(sessionScopeBean.getUserName());
    }

    @GetMapping("/application")
    public ResponseEntity<Integer> testApplicationScope(){
        applicationScopeBean.incrementVisitorCount();
        return ResponseEntity.ok().body(applicationScopeBean.getVisitorCount());
    }
    @GetMapping("/testApplication")
    public ResponseEntity<Integer> testApplication(){
        return ResponseEntity.ok().body(applicationScopeBean.getVisitorCount());
    }
}
