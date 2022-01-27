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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img alt="JaF"
                                              src="${pageContext.request.contextPath}/resources/images/logo_white_narrow.png" style="height: 20px; width: 55px; margin-bottom: 4px;" class="d-inline-block"></a>
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
                <li class="nav-item dropdown active">
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/my-posts">My posts</a>
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

    <section class="title text-center mb-3 jumbotron">
        <div class="container">
            <h1 class="title-heading">Frontend</h1>
            <p class="lead text-muted mb-0">Layout and all about user interaction.</p>
        </div>
    </section>

    <div class="container py-5">
        <div class="row">

            <div class="col-md-8">

                <c:if test="${empty requestScope.frontendPosts}">
                    <div class="alert alert-info mb-4" role="alert">
                        <h4 class="alert-heading">Well</h4>
                        <p>There is no post in this category at the moment.</p>
                    </div>
                </c:if>

                <div class="p-3 rounded shadow-sm">
                    <h6 class="border-bottom text-light border-gray pb-2 mb-0">Posts</h6>

                    <c:forEach items="${requestScope.frontendPosts}" var="post">
                        <div class="media pt-3">
                            <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                <div class="d-flex justify-content-between align-items-center w-100">
                                    <strong class="text-light"><c:out value="${post.title}"/></strong>
                                </div>
                                <span class="d-block"><c:out value="${post.content}"/></span>
                                <div class="d-flex justify-content-between">
                                    <a class="btn btn-sm btn-outline-info align-self-end" href="${pageContext.request.contextPath}/single-post?id=${post.id}">View all</a>
                                    <div class="d-flex flex-column">
                                        <a class="text-muted"><c:out value="${post.username}"/></a>
                                        <small class="text-muted"><c:out value="${post.publishedDateTime}"/></small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>

            </div>

            <aside class="col-md-4 order-first order-md-last mb-4">
                <div class="position-sticky-top-70">
                    <a class="btn btn-outline-success btn-lg btn-block mb-2" href="${pageContext.request.contextPath}/new-post"
                       role="button">Add new post</a>
                    <div class="card bg-secondary">
                        <div class="card-body">
                            <h5 class="card-title">Categories</h5>
                            <div class="card-text">
                                <div class="list-group">
                                    <a href="${pageContext.request.contextPath}/frontend" class="list-group-item list-group-item-action list-group-item-info active">Frontend</a>
                                    <a href="${pageContext.request.contextPath}/backend" class="list-group-item list-group-item-action list-group-item-info">Backend</a>
                                    <a href="${pageContext.request.contextPath}/mobile" class="list-group-item list-group-item-action list-group-item-info">Mobile</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </aside>

        </div>
    </div>

</main>
<%@ include file="../../segments/footer.jspf" %>
<%@ include file="../../segments/scripts.jspf" %>
</body>
</html>
