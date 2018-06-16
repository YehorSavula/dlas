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
                            <a class="nav-link" href="<c:url value='/student'/>">Мои курсы</a>
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
            <form class="form-horizontal pt-5" action="<c:url value='add-course'/>" method="post">
                <h3 class="card-title">Отметьте те компетенции, которые были вами получены:</h3>
                <div class="form-group">
                    <div class="col-sm-10">
                        <c:forEach items="${courseCriteries}" var="criteria" varStatus="myIndex">
                            <div class="checkbox">
                                <input type="checkbox" name="criteries" id="checkbox${myIndex.count}" value="${criteria}">
                                <label for="checkbox${myIndex.count}">
                                    ${criteria}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Отправить курс</button>
                    </div>
                </div>
            </form>
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