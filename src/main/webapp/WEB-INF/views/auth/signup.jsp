<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta content="Forum for developers" name="description">
    <meta content="JAF Group" name="author">

    <title>Sign up</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
</head>

<body>

<div class="container">
    <div class="py-4 text-center mt-md-0">
        <h2>Create new account</h2>
    </div>

    <div class="row">

        <div class="col-md-4 order-md-1 offset-md-4">

            <c:if test="${requestScope.usernameError != null}">
                <c:set value="is-invalid" var="invalidUsername" />
            </c:if>

            <c:if test="${requestScope.emailError != null}">
                <c:set value="is-invalid" var="invalidEmail" />
            </c:if>

            <c:if test="${requestScope.passwordError != null}">
                <c:set value="is-invalid" var="invalidPassword" />
            </c:if>

            <c:if test="${requestScope.confirmPasswordError != null}">
                <c:set value="is-invalid" var="invalidConfirmPassword" />
            </c:if>

            <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/signup" method="post">

                <div class="mb-2">
                    <label for="username">Username</label>
                    <input class="form-control ${invalidUsername}" id="username" name="username" type="text" required>
                        <div class="invalid-feedback">
                            <p><c:out value="${requestScope.usernameError}" /></p>
                        </div>
                </div>

                <div class="mb-2">
                    <label for="email">Email</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">@</span>
                        </div>
                        <input class="form-control ${invalidEmail}" id="email" name="email" type="email" required>
                        <div class="invalid-feedback" style="width: 100%;">
                            <p><c:out value="${requestScope.emailError}" /></p>
                        </div>
                    </div>
                </div>


                <hr class="mb-2">

                <div class="mb-2">
                    <label for="password">Password</label>
                    <input class="form-control ${invalidPassword}" id="password" name="password" type="password" required>
                    <div class="invalid-feedback">
                        <p><c:out value="${requestScope.passwordError}" /></p>
                    </div>
                </div>

                <div class="mb-2">
                    <label for="confirmPassword">Confirm password</label>
                    <input class="form-control ${invalidConfirmPassword}" id="confirmPassword" name="confirmPassword" required type="password">
                    <div class="invalid-feedback">
                        <p><c:out value="${requestScope.confirmPasswordError}" /></p>
                    </div>
                </div>


                <button class="btn btn-info btn-lg btn-block mt-4" type="submit">Create</button>

                <div class="btn-group btn-group-sm mt-3 btn-block" role="group">
                    <a aria-disabled="true" class="btn btn-outline-info mr-2" href="${pageContext.request.contextPath}/login" role="button">I have an
                        account</a>
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
