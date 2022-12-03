## Spring secure

```
authentication - org.springframework.security.authentication.RememberMeAuthenticationToken
#paramName Method parameter name
#this - ru.sifox.util.spring.auth.ExtendedMethodSecurityExpressionRoot
#root - ru.sifox.util.spring.auth.ExtendedMethodSecurityExpressionRoot
principal - 
this - Controller object 
method - Controller interface method 
@PreAuthorize ("isAuthenticated() and hasPermission(#model, 'BackupWidgetServiceController.list', 'read') and @someAuth.someFunction(authentication, #model, #this, #root, principal, this, method)")
@PreAuthorize ("@someAuth.someFunction(this, new Object[] {})")
```