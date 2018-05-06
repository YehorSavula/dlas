<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ru">
    <%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <head>

        <meta contentType="text/html; charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>ХНУРЭ Система Дистанционного Учета Успеваемости</title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value='/resources/vendor/bootstrap/css/bootstrap.min.css'/>" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="<c:url value='/resources/css/shop-item.css'/>" rel="stylesheet">

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#"><img src="<c:url value='/resources/image/Logo_new.png'/>" alt="Система Дистанционного Учета Успеваемости">
                    <a class="navbar-brand" href="#">ХНУРЭ СДУУ</a>
                </div>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/add-course'/>">Добавить курс</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Выход</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">

            <c:choose>
                <c:when test="${not empty submittedCourses and not empty activeCourse}">
                    <div class="row">
                        <div class="col-lg-3">
                            <h2 class="my-4">Система Дистанционного Учета Успеваемости</h1>
                            <div class="list-group">
                                <c:forEach var="item" items="${submittedCourses}" varStatus="myIndex">
                                    <a href="<c:url value='/student?submittedCourseId=${item.id}'/>"
                                       class="${item.id == activeId ? 'list-group-item active' : 'list-group-item'}">
                                            Предмет ${myIndex.count}
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- /.col-lg-3 -->

                        <div class="col-lg-9">

                            <div class="card mt-4">
                                <div class="card-body">
                                    <h3 class="card-title">${activeCourse.courseName}</h3>
                                    <h4>Ссылка на пройденный курс: <a href="${activeCourse.courseUrl}">${activeCourse.courseUrl}</a></h4>
                                    <h4>Ссылка на сертификат: <a href="${activeCourse.certificateUrl}">${activeCourse.certificateUrl}</a></h4>
                                    <h4>Оценка: ${activeCourse.graduate}</h4>
                                    <c:choose>
                                        <c:when test="${activeCourse.accepted}">
                                            <span class="text-success">Курс защитан</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-info">Курс отправлен на проверку преподавателю</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <!-- /.card -->

                        </div>
                        <!-- /.col-lg-9 -->

                    </div>
                </c:when>
                <c:otherwise>
                    <h2 class="my-4">Вы не отправили еще ни одного курса на проверку</h1>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-4 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright &copy; ХНУРЭ Система Дистанционного Учета Успеваемости</p>
            </div>
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="<c:url value='/resources/vendor/jquery/jquery.min.js'/>"></script>
        <script src="<c:url value='/resources/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

    </body>

</html>
