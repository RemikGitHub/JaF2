<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta content="Forum for developers" name="description">
    <meta content="JAF Group" name="author">

    <title>Change password</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
</head>

<body>

<div class="container">
    <div class="py-4 text-center mt-md-0">
        <h2>Change password</h2>
    </div>

    <div class="row">

        <div class="col-md-4 order-md-1 offset-md-4">

            <c:if test="${requestScope.oldPasswordError != null}">
                <c:set value="is-invalid" var="invalidOldPassword" />
            </c:if>

            <c:if test="${requestScope.newPasswordError != null}">
                <c:set value="is-invalid" var="invalidNewPassword" />
            </c:if>

            <c:if test="${requestScope.confirmNewPasswordError != null}">
                <c:set value="is-invalid" var="invalidConfirmNewPassword" />
            </c:if>

            <c:if test="${requestScope.success != null}">
                <div class="alert alert-success mb-4" role="alert">
                    <h4 class="alert-heading">Well done!</h4>
                    <p><c:out value="${requestScope.success}" /></p>
                </div>
            </c:if>

            <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/change-password" method="post">

                <div class="mb-2">
                    <label for="oldPassword">Old password</label>
                    <input class="form-control ${invalidOldPassword}" id="oldPassword" name="oldPassword" required type="password">
                    <div class="invalid-feedback">
                        <p><c:out value="${requestScope.oldPasswordError}" /></p>
                    </div>
                </div>

                <hr class="mb-2">

                <div class="mb-2">
                    <label for="newPassword">New password</label>
                    <input class="form-control ${invalidNewPassword}" id="newPassword" name="newPassword" required type="password">
                    <div class="invalid-feedback">
                        <p><c:out value="${requestScope.newPasswordError}" /></p>
                    </div>
                </div>

                <div class="mb-2">
                    <label for="confirmNewPassword">Confirm new password</label>
                    <input class="form-control ${invalidConfirmNewPassword}" id="confirmNewPassword" name="confirmNewPassword" required type="password">
                    <div class="invalid-feedback">
                        <p><c:out value="${requestScope.confirmNewPasswordError}" /></p>
                    </div>
                </div>


                <button class="btn btn-info btn-lg btn-block mt-4" type="submit">Change password</button>

                <div class="btn-group btn-group-sm mt-3 btn-block" role="group">
                    <a aria-disabled="true" class="btn btn-outline-info mr-2" href="${pageContext.request.contextPath}/my-posts" role="button">Back to my posts</a>
                    <a aria-disabled="true" class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/" role="button">Back to home
                        page</a>
                </div>
            </form>
        </div>
    </div>

    <footer class="mb-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">Just a Forum</p>
    </footer>
</div>

<%@ include file="../../segments/scripts.jspf" %>

</body>
</html>
