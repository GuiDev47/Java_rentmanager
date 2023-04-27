<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">

     <section class="content-header">
                <h1>
                    Reservations du vehicule :  " ${car.constructeur} - ${car.nb_places} places "
                </h1>
            </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Client</th>
                                            <th>Date de debut</th>
                                            <th>Date de fin</th>
                                        </tr>
                                        <c:forEach items="${resas}" var="resa">
                                            <c:if test="${resa.vehicle_id eq car.ID}">
                                            <tr>
                                                <td>${resa.ID}</td>
                                                <c:forEach items="${clients}" var="client">
                                                    <c:if test="${client.ID eq resa.client_id}">
                                                        <td>${client.nom} ${client.prenom}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td>${resa.debut}</td>
                                                <td>${resa.fin}</td>
                                            </tr>
                                            </c:if>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
