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
                <li class="nav-item dropdown">
                    <a aria-expanded="false" aria-haspopup="true" class="nav-link dropdown-toggle active"
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

    <div class="container py-5">
        <div class="row">
            <c:if test="${requestScope.commentContentError != null}">
                <c:set value="is-invalid" var="invalidCommentContent" />
            </c:if>

            <c:choose>
                <c:when test="${not empty requestScope.errorMessage}">
                    <div class="alert alert-danger mt-5 col-12 col-md-6 offset-md-3" role="alert">
                        <h4 class="alert-heading">Error!</h4>
                        <p><c:out value="${requestScope.errorMessage}" /></p>
                    </div>
                </c:when>

                <c:when test="${not empty requestScope.post}">
                    <c:set value="${requestScope.post}" var="post" />

                    <div class="col-md-10 offset-md-1">

                        <div class="card">
                            <div class="card-body">

                                <div class="d-flex justify-content-between">
                                    <h2 class="card-title"><c:out value="${post.title}"/></h2>

                                    <c:if test="${pageContext.request.userPrincipal.name == post.username}">
                                        <div class="btn-group btn-group-sm">
                                            <button aria-expanded="false" aria-haspopup="true"
                                                    class="btn btn-outline-secondary dropdown-toggle p-0 pb-1 h-50 options"
                                                    data-toggle="dropdown" type="button">
                                                <svg class="bi bi-three-dots-vertical" fill="currentColor" height="16" viewBox="0 0 16 16"
                                                     width="16" xmlns="http://www.w3.org/2000/svg">
                                                    <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"></path>
                                                </svg>
                                            </button>
                                            <div class="dropdown-menu">
                                                <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/del-post?id=${post.id}" method="post">
                                                    <button class="dropdown-item" type="submit">delete</button>
                                                </form>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>

                                <strong><a class="mb-1" href="#" ><c:out value="${post.username}"/></a></strong>
                                <p class="card-subtitle text-muted mb-2"><c:out value="${post.publishedDateTime}"/></p>
                                <p class="card-text"><c:out value="${post.content}"/></p>
                            </div>
                        </div>

                        <div class="my-3 p-3 rounded shadow-sm">

                            <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/new-comment?id=${post.id}" method="post">
                                <div class="input-group mb-3">
                                <textarea class="form-control bg-light ${invalidCommentContent}" id="commnetContent" name="commnetContent" placeholder="Write comment" rows="1"></textarea>
                                    <div class="invalid-feedback">
                                        <p><c:out value="${requestScope.commentContentError}" /></p>
                                    </div>
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-info" type="submit">Add comment</button>
                                    </div>
                                </div>
                            </form>

                            <h6 class="border-bottom border-gray pb-2 mb-0 text-light">Coments</h6>

                            <c:if test="${empty requestScope.comments}">
                                <p class="mt-2 ">There are no comments on this post.</p>
                            </c:if>

                            <c:forEach items="${requestScope.comments}" var="comment">
                                <div class="media pt-3">

                                    <div class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">

                                        <div class="d-flex justify-content-between">

                                            <div>
                                                <p class="badge badge-info text-wrap mb-1"><c:out value="${comment.username}"/></p>
                                                <small class="ml-2 text-muted"><c:out value="${comment.writeDateTime}"/></small>
                                            </div>

                                            <div class="btn-group btn-group-sm">
                                                <button aria-expanded="false" aria-haspopup="true"
                                                        class="btn btn-outline-secondary dropdown-toggle p-0 pb-1 options"
                                                        data-toggle="dropdown" type="button">
                                                    <svg class="bi bi-three-dots-vertical" fill="currentColor" height="16"
                                                         viewBox="0 0 16 16" width="16" xmlns="http://www.w3.org/2000/svg">
                                                        <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"></path>
                                                    </svg>
                                                </button>
                                                <div class="dropdown-menu">
                                                    <form class="needs-validation" role="form" action="${pageContext.request.contextPath}/del-comment?id=${comment.id}" method="post">
                                                        <c:if test="${pageContext.request.userPrincipal.name == comment.username}">
                                                            <button class="dropdown-item" type="submit">delete</button>
                                                        </c:if>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <p class="mb-0"><c:out value="${comment.content}"/></p>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>

                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>

</main>
<%@ include file="../../segments/footer.jspf" %>
<%@ include file="../../segments/scripts.jspf" %>
</body>
</html>
