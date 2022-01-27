<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta content="Forum for developers" name="description">
    <meta content="JAF Group" name="author">

    <title>JaF</title>
    <%@ include file="../../segments/stylesheets.jspf" %>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-info">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img alt="JaF" src="${pageContext.request.contextPath}/resources/images/logo_white_narrow.png" style="height: 20px; width: 55px; margin-bottom: 4px;" class="d-inline-block"></a>
        <button aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation"
                class="navbar-toggler"
                data-target="#navbarCollapse" data-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a aria-expanded="false" aria-haspopup="true" class="nav-link dropdown-toggle"
                       data-toggle="dropdown"
                       href="#" id="submenu" role="button"> Categories </a>

                    <div aria-labelledby="submenu" class="dropdown-menu">

                        <a class="dropdown-item" href="${pageContext.request.contextPath}/frontend"> Frontend</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/backend"> Backend </a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/mobile"> Mobile </a>

                    </div>

                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/my-posts">My posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/about-us">About us</a>
                </li>
            </ul>
            <form class="form-inline mt-2 mt-md-0">
                <c:choose>
                    <c:when test="${empty pageContext.request.userPrincipal}">
                        <a class="btn btn-info mr-2" href="${pageContext.request.contextPath}/login" role="button" >Log in</a>
                        <a class="btn btn-info" href="${pageContext.request.contextPath}/signup" role="button">Sign up</a>
                    </c:when>
                    <c:when test="${not empty pageContext.request.userPrincipal}">
                        <span class="text-light font-weight-bold mr-2">
                            <c:out value="${pageContext.request.userPrincipal.name}" />
                        </span>
                        <a class="btn btn-info" href="${pageContext.request.contextPath}/logout" role="button">Logout</a>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </nav>
</header>

<main role="main">

    <div class="container py-5">
        <div class="row">

            <c:if test="${requestScope.postTitleError != null}">
                <c:set value="is-invalid" var="invalidTitle" />
            </c:if>

            <c:if test="${requestScope.postContentError != null}">
                <c:set value="is-invalid" var="invalidContent" />
            </c:if>

            <c:if test="${requestScope.postCategoryError != null}">
                <c:set value="is-invalid" var="invalidCategory" />
            </c:if>

            <div class="col-12 col-md-8 offset-md-2">
                <div class="card bg-secondary">
                    <div class="card-body">
                        <h5 class="card-title">Add new post</h5>

                        <c:if test="${requestScope.confirmAddPost != null}">
                            <div class="alert alert-success my-4" role="alert">
                                <h4 class="alert-heading">Well done!</h4>
                                <p><c:out value="${requestScope.confirmAddPost}" /></p>
                            </div>
                        </c:if>

                        <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/new-post" method="post">
                            <div class="form-group">
                                <input class="form-control bg-grey ${invalidTitle}" id="title" placeholder="title" type="text" autocomplete="off" name="title" required>
                                <div class="invalid-feedback">
                                    <p><c:out value="${requestScope.postTitleError}" /></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <select name="postCategory" class="form-control bg-grey ${invalidCategory}" id="category">
                                    <c:forEach items="${requestScope.postCategory}" var="category">
                                        <option value="${category}">${category}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">
                                    <p><c:out value="${requestScope.postCategoryError}" /></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea class="form-control bg-grey ${invalidContent}" id="content" placeholder="Write your content" rows="16" name="content" required></textarea>
                                <div class="invalid-feedback">
                                    <p><c:out value="${requestScope.postContentError}" /></p>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-info px-5">Add</button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>

</main>
<%@ include file="../../segments/footer.jspf" %>
<%@ include file="../../segments/scripts.jspf" %>
</body>
</html>
