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
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text"  minlength="3" class="form-control" id="last_name" name="last_name" placeholder="Nom" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text"  minlength="3" class="form-control" id="first_name" name="first_name" placeholder="Prenom" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Date" class="col-sm-2 control-label">Date de naissance</label>

                                        <div class="col-sm-10">
                                            <input type="Date" class="form-control" id="Date" name="Date" placeholder="Date de naissance" required onblur="VerifAge()">
                                        </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required onblur="VerifEmail()">
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button id='Add' type="submit" class="btn btn-info pull-right">Ajouter</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>

<script>

function VerifAge(){

    var date = new Date(document.getElementById("Date").value);
    var ajd = Date.now();
    var diff = ajd - date;
    var age = Math.floor(diff / 31557600000);

    if (age<18){
        alert("Vous êtes trop jeune. Veuillez vérifier la date de naissance entrée");
        document.getElementById('Add').disabled = true;
    }
    else if(age>18){
    document.getElementById('Add').disabled = false;
    }
}

let Emails = [
<c:forEach  items="${clients}" var="client">
'${client.email}',
</c:forEach>
];

function VerifEmail(){
    var mail = document.getElementById("email").value;
    for (var i =0; i < Emails.length; i++){
        if (mail == Emails[i]){
            document.getElementById('Add').disabled = true;
            alert("Email deja associe a un client");
            break;
        }
        else{
            document.getElementById('Add').disabled = false;
        }
    }
}


</script>
</body>
</html>
