<?php
include('inc/config.php');
include('inc/menu.php');
if($_SESSION["logeado"] != "SI"){
  header ("Location: index.php");
exit;
}

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT * FROM asuntos") or die(mysql_error());
$query2 = mysqli_query($link,"SELECT * FROM servicios") or die(mysql_error());

$juliano= gregoriantojd (4,28,2016);
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nuevo Ticket</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <style type="text/css">
  body{background: #000;}

     .media
    {
        /*box-shadow:0px 0px 4px -2px #000;*/
        margin: 20px 0;
        padding:30px;
    }
    .dp
    {
        border:10px solid #eee;
        transition: all 0.2s ease-in-out;
    }
    .dp:hover
    {
        border:2px solid #eee;
        transform:rotate(360deg);
        -ms-transform:rotate(360deg);
        -webkit-transform:rotate(360deg);
        /*-webkit-font-smoothing:antialiased;*/
    }
body
{
    background-color: #1b1b1b;
}

.alert-purple { border-color: #694D9F;background: #694D9F;color: #fff; }
.alert-info-alt { border-color: #B4E1E4;background: #81c7e1;color: #fff; }
.alert-danger-alt { border-color: #B63E5A;background: #E26868;color: #fff; }
.alert-warning-alt { border-color: #F3F3EB;background: #E9CEAC;color: #fff; }
.alert-success-alt { border-color: #19B99A;background: #20A286;color: #fff; }
.glyphicon { margin-right:10px; }
.alert a {color: gold;}

.input-group-addon
{
    background-color: rgb(50, 118, 177);
    border-color: rgb(40, 94, 142);
    color: rgb(255, 255, 255);
}
.form-control:focus
{
    background-color: rgb(50, 118, 177);
    border-color: rgb(40, 94, 142);
    color: rgb(255, 255, 255);
}
.form-signup input[type="text"],.form-signup input[type="password"] { border: 1px solid rgb(50, 118, 177); }
  </style>
  <body>
    <br>
        <div class="container">

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">


<div class="container">
	<form name="form1" method="post" action="insertar_lote.php">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h5 class="text-center">
                        Nuevo Ticket</h5>
                    <form class="form form-signup" role="form">
                    <div class="form-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-chevron-down"> Asuntos </span></span>
                        <div class="col-xs-15 selectContainer">
                            <select class="form-control" name="idProveedor">
                                <?php while($asuntos = mysqli_fetch_array($query)){ ?>
                                <option value=<?php echo $asuntos['id_asuntoP'] ?>><?php echo $asuntos['nombre']?></option>
                                <?php } ?>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-chevron-down"> Asuntos </span></span>
                        <div class="col-xs-15 selectContainer">
                            <select class="form-control" name="idProveedor">
                                <?php while($servicios = mysqli_fetch_array($query2)){ ?>
                                <option value=<?php echo $servicios['id_asuntoS'] ?>><?php echo $servicios['nombre_asuntoS']?></option>
                                <?php } ?>
                            </select>
                        </div>
                    </div>
                 </div>
				         <div class="form-group">
                           <div class="input-group">
                               <span class="input-group-addon"><span class="glyphicon glyphicon-cloud-download"></span></span>
                               <input name="nIngreso" type="text" class="form-control"  id="nIngreso" placeholder="Numero de ingreso del dia" />
                           </div>
                       </div>
                    <div class="form-group">
                           <div class="input-group">
                               <span class="input-group-addon"><span class="glyphicon glyphicon-stats"></span></span>
                               <input name="cantidad" type="text" class="form-control"  id="cantidad" value="" placeholder="Cantidad en KG" />
                           </div>
                       </div>
                <input type="submit" name="Submit" value="Registrar"  class="btn btn-sm btn-primary btn-block">
 </form>
            </div>
                     <?php
if(isset($_GET['sucess'])){
echo "
<div class='alert alert-success-alt alert-dismissable'>
                <span class='glyphicon glyphicon-certificate'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>Listo! Tu registro fue hecho satisfactoriamente.</div>
";
}else{
echo "";
}
?>
<?php
if(isset($_GET['errordat'])){
echo "
<div class='alert alert-warning-alt alert-dismissable'>
                <span class='glyphicon glyphicon-certificate'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>Ha habido un error al insertar los valores. Asegurese de usar punto y no coma para la cantidad</div>
";
}else{
echo "";
}
?>
<?php
if(isset($_GET['errordb'])){
echo "
<div class='alert alert-danger-alt alert-dismissable'>
                <span class='glyphicon glyphicon-certificate'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>Error, no ha introducido todos los datos.</div>
";
}else{
echo "";
}
?>
        </div>
    </div>
</div>
</form>
</div>



      </div>

    </div> <!-- /container -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
   <script src="js/moment.min.js"></script>
   <script src="js/bootstrap-datetimepicker.min.js"></script>
   <script src="js/bootstrap-datetimepicker.es.js"></script>
   <script type="text/javascript">
	 $('#divMiCalendario').datetimepicker({
		  format: 'YYYY-MM-DD'
	  });
	  $('#divMiCalendario').data("DateTimePicker").show();
   </script>
  </body>
</html>
