<%@tag description="Login layout" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%> 
<%@attribute name="css" fragment="true"%>
<%@attribute name="js" fragment="true"%>
<t:layout>
    <jsp:attribute name="css">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
        <jsp:invoke fragment="css"/>
    </jsp:attribute>
    <jsp:attribute name="js">
        <jsp:invoke fragment="js"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:layout>