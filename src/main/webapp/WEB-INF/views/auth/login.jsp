<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta content="Forum for developers" name="description">
    <meta content="JAF Group" name="author">

    <title>Sign in</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-4.6.0-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/auth.css">
</head>

<body>

<div class="container">
    <div class="py-3 text-center mt-5">
        <img alt="JaF" src="${pageContext.request.contextPath}/resources/images/logo_white.png" style="height: 116px; width: 200px;" class="mb-2">
    </div>

    <div class="row">

        <div class="col-md-6 order-md-1 offset-md-3">

            <c:if test="${requestScope.confirmStart != null}">
                <div class="alert alert-info mb-4" role="alert">
                    <h4 class="alert-heading">One more step</h4>
                    <p><c:out value="${requestScope.confirmStart}" /></p>
                </div>
            </c:if>


            <c:if test="${requestScope.confirmError != null}">
                <div class="alert alert-danger mb-4" role="alert">
                    <h4 class="alert-heading">Error!</h4>
                    <p><c:out value="${requestScope.confirmError}" /></p>
                </div>
            </c:if>

            <c:if test="${param.confirm == true}">
                <div class="alert alert-info mb-4" role="alert">
                    <h4 class="alert-heading">One more step</h4>
                    <p>Email address has been confirmed.</p>
                </div>
            </c:if>

            <c:if test="${param.confirm == false}">
                <div class="alert alert-danger mb-4" role="alert">
                    <h4 class="alert-heading">Error!</h4>
                    <p>The link is invalid or broken.</p>
                </div>
            </c:if>

        </div>
    </div>

    <div class="row">

        <div class="col-md-4 order-md-1 offset-md-4">

            <c:if test="${param.error == true}">
                <c:set value="is-invalid" var="errorCssClass" />
            </c:if>

            <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/auth" method="post">

                <div class="mb-2">
                    <label class="sr-only" for="username">Username</label>
                    <input autofocus class="form-control ${errorCssClass}" id="username" name="username" placeholder="Username" required type="text">
                    <div class="invalid-feedback"></div>
                </div>

                <div class="mb-2">
                    <label class="sr-only" for="password">Password</label>
                    <input class="form-control ${errorCssClass}" id="password" name="password" placeholder="Password" required type="password">

                        <div class="invalid-feedback">
                            Wrong username or password
                        </div>
                </div>

                <div class="checkbox mb-3">
                    <label>
                        <input name="rememberMe" type="checkbox" checked> Remember me
                    </label>
                </div>

                <button class="btn btn-info btn-lg btn-block mt-4" type="submit">Sign in</button>

                <div class="btn-group btn-group-sm mt-3 btn-block" role="group">
                    <a aria-disabled="true" class="btn btn-outline-info mr-2" href="${pageContext.request.contextPath}/signup" role="button">Create new
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
