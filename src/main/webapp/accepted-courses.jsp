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
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.css" rel="stylesheet"/>

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
                            <a class="nav-link" href="<c:url value='/upload-program'/>">Загрузить рабочую программу</a>
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
                <c:when test="${not empty acceptedCourses}">
                    <h3 class="card-title pt-5">Принятые курсы:</h3>
                    <table class="pt-5" data-toggle="table" data-sort-name="name" data-sort-order="desc">
                        <thead>
                            <tr>
                                <th data-field="name" data-sortable="true">Имя курса</th>
                                <th data-field="teacher_name" data-sortable="true">Преподаватель</th>
                                <th data-field="student_name" data-sortable="true">Студент</th>
                                <th data-field="group_name" class="w-10" data-sortable="true">Группа</th>
                                <th data-field="graduate" data-sortable="true">Оценка</th>
                                <th data-field="certificate_url">Ссылка на сертификат</th>
                                <th data-field="course_url">Ссылка на курс</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${acceptedCourses}" var="course">
                                <tr>
                                    <td>${course.courseName}</td>
                                    <td>${course.teacherName}</td>
                                    <td>${course.studentName}</td>
                                    <td>${course.groupName}</td>
                                    <td>${course.graduate}</td>
                                    <td><a href="${course.certificateUrl}">Ссылка</a></td>
                                    <td><a href="${course.courseUrl}">Ссылка</a></td>
                                <tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <h2 class="my-4">Не принято пока еще ни одного курса</h1>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>

    </body>

</html>
