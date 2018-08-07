<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:dashboard>
    <jsp:attribute name="headingTitle">User List</jsp:attribute>
    <jsp:attribute name="headingButton">
        <div class="btn-toolbar mb-2 mb-md-0">
            <div class="btn-group mr-2">
                <a href="${pageContext.request.contextPath}/users/create" class="btn btn-sm btn-outline-secondary">Add User</a>
            </div>
        </div>
    </jsp:attribute>
    <jsp:body>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <thead>
                    <tr>
                      <th>#</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Role</th>
                      <th>Created</th>
                      <th>Updated</th>
                      <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>${user.createdAt}</td>
                        <td>${user.updatedAt}</td>
                        <td><a href="${pageContext.request.contextPath}/users/update?id=${user.id}">Edit</a><c:if test="${user.id != loggedinUser.id}"> | <a href="${pageContext.request.contextPath}/users/delete?id=${user.id}" onclick="return confirm('Are you sure?')">Delete</a></c:if></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:dashboard>