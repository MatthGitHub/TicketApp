<?php
include('inc/config.php');
if($_SESSION["logeado"] != "SI"){
header ("Location: index.php");
exit;
}

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT * FROM asuntos") or die(mysql_error());
$query2 = mysqli_query($link,"SELECT * FROM servicios") or die(mysql_error());

$strConsulta = "SELECT id_asuntoP, nombre FROM asuntos";
$result = $link->query($strConsulta);
$opciones = '<option value="0"> Elige una marca</option>';
while( $fila = $result->fetch_array() )
{
  $opciones.='<option value="'.$fila["id_asuntoP"].'">'.$fila["nombre"].'</option>';
}

?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nuevo Ticket</title>

    <!-- Bootstrap -->
    <script src="js/jquery-1.12.3.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">

	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  <script type="text/javascript" src="js/jquery-1.12.3.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      $("#asunto").change(function(){
        $.ajax({
          url:"procesa.php",
          type: "POST",
          data:"idAsunto="+$("#asunto").val(),
          success: function(opciones){
            $("#servicios").html(opciones);
          }
        })
      });
    });
  </script>

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
          <?php include('inc/menu.php'); ?>
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">


<div class="container">
	<form name="form1" method="post" action="insertar.php?tipo=ticket">
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
                            <select class="form-control" name="asunto" id="asunto">
                                <option> Eliga un asunto </option>
                                <?php while($asuntos = mysqli_fetch_array($query)){ ?>
                                <option value=<?php echo $asuntos['id_asuntoP'] ?>><?php echo $asuntos['nombre']?></option>
                                <?php } ?>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-chevron-down"> Servicios </span></span>
                        <div class="col-xs-15 selectContainer">
                            <select class="form-control" name="servicios" id="servicios">
                            </select>
                        </div>
                    </div>

                 <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span>Numero interno</span>
                            <input type="text" class="form-control"  name="interno" id="interno" value="" placeholder="Interno" />
                        </div>
                    </div>

                   <div class="form-group">
                       <label for="comment">Observacion:</label>
                       <textarea class="form-control" rows="5" id="observacion" name="observacion"></textarea>
                  </div>
                </div>

                <input type="submit" name="Submit" value="Enviar"  class="btn btn-sm btn-primary btn-block">
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
 </body>
</html>
