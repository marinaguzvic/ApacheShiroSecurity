<%-- 
    Document   : login
    Created on : Aug 1, 2019, 7:21:42 PM
    Author     : MARINA
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages/messages" />


<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <c:if test="${param.regSucc == true}">
                <div id="status">
                    <div class="alert alert-success" role="alert">
                        <spring:message code="message.regSucc">    
                        </spring:message>
                    </div>
                </div>
            </c:if>
            <c:if test="${param.regError == true}">
                <div id="error">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="message.regError">   
                        </spring:message>
                    </div>
                </div>
            </c:if>
            <c:if test="${param.error != null}">
                <div id="error2">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="message.badCredentials">   
                        </spring:message>
                    </div>
                </div>
            </c:if>
            <form name="f" action="perform_login" method="POST">
                <div class="form-group">
                    <div class="form-label-group">
                        <input name="username" type="text" id="inputEmail" class="form-control" placeholder="Email address" required="required" autofocus="autofocus" value="">
                        <label for="inputEmail">Username</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required="required">
                        <label for="inputPassword">Password</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="true" name="remember-me">
                            Remember Password
                        </label>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Login</button>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/usermgmt/registration">Register an Account</a>
                <a class="d-block small" href="/usermgmt/resetPassword/request">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function validate() {
        if (document.f.username.value == "" && document.f.password.value == "") {
            alert("{noUser} and {noPass}");
            document.f.username.focus();
            return false;
        }
        if (document.f.username.value == "") {
            alert("{noUser}");
            document.f.username.focus();
            return false;
        }
        if (document.f.password.value == "") {
            alert("{noPass}");
            document.f.password.focus();
            return false;
        }
    }
</script>