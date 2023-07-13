<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <head>
        <%@include file="Head.jsp" %>
    </head>

    <body>
        <nav class="navbar navbar-expand-xl">
            <%@include file="Header.jsp" %>
        </nav>
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block">Edit Episodes</h2>
                                <c:set value="${requestScope.maxEp}" var="i"/>
                                <c:if test="${i == requestScope.nextEp}">
                                    <a href="deleteEpisode?del_ID=${i}&movieId=${requestScope.detail.movieId}" onclick="return confirm('Do you want to remove this episode?')">
                                        <button type="submit" class="btn btn-info btn-lg" data-toggle="modal" data-target="#deleteModal"
                                                style="background: transparent; border: none; margin: 0px 0px 5px -2px">
                                            <i class="far fa-trash-alt tm-product-delete-icon" style="color: red;"></i>
                                        </button>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                        <form action="EditEpisodes" method="post" class="tm-edit-product-form"> 
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-12 col-lg-12 col-md-12">

                                    <div class="form-group mb-3">
                                        <label for="movieID">Movie ID
                                        </label>
                                        <c:set value="${requestScope.detail}" var="i"/>
                                        <input value="${i.movieId}" id="movieID" name="movieId" type="text" class="form-control validate"  style="background-color: #54657d" readonly/>
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="Episodes Number">Episodes Number
                                        </label>
                                        <c:set value="${requestScope.nextEp}" var="i"/>
                                        <input value="${i}" id="epNum" name="epNum" type="text" class="form-control validate" style="background-color: #54657d " readonly />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="duration">Duration</label>
                                        <c:set value="${requestScope.detail}" var="i"/>
                                        <input id="duration"  value="${i.duration}" name="ep_duration" type="text" class="form-control validate" required />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="movieLink">Movie Link</label>
                                        <c:set value="${requestScope.detail}" var="i"/>
                                        <input id="movieLink" value="${i.movieLink}" name="ep_Link" type="text" class="form-control validate" required />
                                    </div>


                                    <div class="col-12">
                                        <button type="submit" class="btn btn-primary btn-block text-uppercase ">Edit Episodes</button>
                                    </div>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script>

        </script>

    </body>

</html>