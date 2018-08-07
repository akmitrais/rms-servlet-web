<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:login>
    <jsp:body>
        <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/login">
            <div class="text-center mb-4">
                <img class="mb-4" src="images/logo.jpg" alt="" width="72" height="72">
                <h1 class="h3 mb-3 font-weight-normal">Account Login</h1>
                <p>Please provide your credential to access the system.</p>
            </div>
            <c:if test="${errorString != null}">
                <div class="alert alert-danger" role="alert">
                    ${errorString}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <div class="form-label-group">
                <input name="email" value="${user.email}" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus onfocus="this.value = this.value;">
                <label for="inputEmail">Email address</label>
            </div>
            <div class="form-label-group">
                <input name="password"  value="${user.password}" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <label for="inputPassword">Password</label>
            </div>
            <div class="checkbox mb-3">
                <label>
                    <input name="rememberMe" value="Y" type="checkbox"> Remember me
                </label>
            </div>
            <input type="hidden" name="from" value="${fn:escapeXml(param.from)}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted text-center">&copy; ${initParam['copyright']}</p>
        </form>
    </jsp:body>
</t:login>