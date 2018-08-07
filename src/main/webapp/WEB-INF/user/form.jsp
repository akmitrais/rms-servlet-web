<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:dashboard>
    <jsp:attribute name="headingTitle">User Form</jsp:attribute>
    <jsp:body>
        <c:if test="${errorString != null}">
            <div class="alert alert-danger" role="alert">
                ${errorString}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        <div class="row">
            <div class="col-sm-6">
                <form method="POST" action="">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group row">
                                <label for="inputName" class="col-sm-2 col-form-label">Name</label>
                                <div class="col-sm-10">
                                    <input name="name" value="${user.name}" type="text" class="form-control" id="inputName" placeholder="Name">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
                                <div class="col-sm-10">
                                    <input name="email" value="${user.email}" type="email" class="form-control" id="inputEmail" placeholder="Email">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                                <div class="col-sm-10">
                                    <input name="password" value="${user.password}" type="password" class="form-control" id="inputPassword" placeholder="Password">
                                </div>
                            </div>
                            <fieldset class="form-group">
                                <div class="row">
                                    <label class="col-form-label col-sm-2 pt-0">Role</label>
                                    <div class="col-sm-10">
                                        <select name="role" class="form-control" id="inputRole">
                                            <option value="1" <c:if test="${user.role_id == 1}">selected</c:if>>Employee</option>
                                            <option value="11" <c:if test="${user.role_id == 11}">selected</c:if>>Admin</option>
                                        </select>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div class="card-footer">
                            <div class="form-group row">
                                <div class="col-sm-10">
                                    <a href="${pageContext.request.contextPath}/users" class="btn btn-danger">Cancel</a>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:dashboard>