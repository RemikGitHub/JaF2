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
                <li class="nav-item active">
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
            <h1 class="title-heading"><c:out value="${pageContext.request.userPrincipal.name}" /></h1>
            <p class="lead text-muted mb-0">These are your posts.</p>
        </div>
    </section>

    <div class="container-fluid py-5">
        <div class="row">

            <aside class="col-md-4 col-lg-3 col-xl-2 order-md-last mb-4">
                <div class="position-sticky-top-70">

                    <a class="btn btn-outline-success btn-lg btn-block mb-2" href="${pageContext.request.contextPath}/new-post"
                       role="button">Add new post</a>

                    <div class="card bg-secondary">
                        <div class="card-body">
                            <h5 class="card-title text-center">Your account</h5>
                            <hr>
                            <div class="card-text">
                                <p class="mb-0 text-light">Username:</p>
                                <p class="mb-2"><c:out value="${pageContext.request.userPrincipal.name}" /></p>


                                <p class="mb-0 text-light">Email:</p>
                                <p class="mb-2"><c:out value="${requestScope.userEmail}" /></p>


                                <p class="mb-0 text-light">Number of posts:</p>
                                <p class="mb-2"><c:out value="${requestScope.numberOfPosts}" /></p>

                                <p class="mb-2 text-light">Settings:</p>
                                <a class="btn btn-outline-info btn-block mb-2" href="${pageContext.request.contextPath}/change-password" role="button">Change
                                    password</a>
                                <button class="btn btn-outline-danger btn-block" data-toggle="modal"
                                        data-target="#deleteUser" attr="data-target='#deleteUser'+${requestScope.userId}"
                                        type="button">Delete account
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
            </aside>

            <div class="col-md-8 col-lg-9 col-xl-10 px-0">
                <div class="container-fluid">
                    <div class="row">

                        <c:if test="${empty requestScope.myPosts}">
                            <div class="alert alert-info mt-4 mt-md-0 col-md-6 offset-md-3" role="alert">
                                <h4 class="alert-heading">Well</h4>
                                <p>You have no posts. If you want to fill this space, click on "Add new post".</p>
                            </div>
                        </c:if>

                        <c:forEach items="${requestScope.myPosts}" var="post">
                            <div class="col-md-12 col-lg-6 col-xl-4">
                                <div class="card bg-secondary mb-4">

                                    <div class="d-flex justify-content-between bg-info">

                                        <h5 class="card-header"><c:out value="${post.title}" /></h5>

                                        <c:if test="${pageContext.request.userPrincipal.name == post.username}">
                                            <div class="btn-group btn-group-sm">
                                                <button aria-expanded="false" aria-haspopup="true"
                                                        class="btn btn-outline-secondary dropdown-toggle p-0 pb-1 options bg-transparent text-white"
                                                        data-toggle="dropdown" type="button">
                                                    <svg class="bi bi-three-dots-vertical" fill="currentColor" height="16"
                                                         viewBox="0 0 16 16"
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

                                    <div class="card-body">
                                        <p class="card-text"><c:out value="${post.content}" /></p>
                                        <div class="d-flex justify-content-between ">
                                            <div>
                                                <a class="btn btn-outline-info" href="${pageContext.request.contextPath}/single-post?id=${post.id}">View all</a>
                                            </div>
                                            <div class="d-flex align-content-center align-items-end flex-column">
                                                <small class="text-muted"><c:out value="${post.username}" /></small>
                                                <small class="text-muted"><c:out value="${post.publishedDateTime}" /></small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Modal -->
<div aria-hidden="true" aria-labelledby="deleteUserLabel" class="modal fade" role="dialog"
     tabindex="-1" attr="id='deleteUser'+${requestScope.userId}" id="deleteUser">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content text-light" style="background-color: #1b2127;">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteUserLabel">Delete Account</h5>
                <button aria-label="Close" class="close" data-dismiss="modal" type="button">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete your account?
            </div>
            <div class="modal-footer">
                <button class="btn btn-info" data-dismiss="modal" type="button">No</button>

                <form role="form" action="${pageContext.request.contextPath}/del-user?id=${requestScope.userId}" method="post">
                    <button class="btn btn-danger" type="submit">Yes</button>
                </form>

            </div>
        </div>
    </div>
</div>
<%@ include file="../../segments/footer.jspf" %>
<%@ include file="../../segments/scripts.jspf" %>
<script>
    $('#deleteUser').on('shown.bs.modal', function () {
        $('#deleteUser').trigger('focus')
    })
</script>
</body>
</html>
