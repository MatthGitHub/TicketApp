<?php
include('inc/config.php');
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname);

$estadoPGM = mysqli_query($link,"SELECT estado FROM estado_actual_pgm eap JOIN estados_pgm ep ON ep.id_estado = eap.fk_estado_pgm ");
$estadoPGM = mysqli_fetch_array($estadoPGM);
$estadoPGM = $estadoPGM['estado'];

?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="icon" type="image/png" href="images/icons/ticket.png" sizes="16x16">
    <title>Sistema Tickets MSCB</title>

    <!-- Bootstrap core CSS -->
    <script src="js/jquery-1.12.3.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">


    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style type="text/css">
		body
		{
			padding-top: 200px;
		}
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
					<img src="images/icons/ticket.png" alt="Municipalidad Bariloche" align="middle" style="margin:0px 0px 0px 140px" height="52" width="52">
                    <h4 class="text-center bg-info">Sistema de Tickets</h4>
                    <form class="form form-signup" role="form" method="post" action="inc/entrar.php">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input type="text" class="form-control" name="nombre_usuario"  id="nombre_usuario" placeholder="Usuario" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="password" id="contrasenia" name="contrasenia" class="form-control" placeholder="Contrase&ntilde;a" />
                        </div>
                    </div>
                </div>
                <input type="submit" name="Submit" value="INICIAR SESION"  class="btn btn-sm btn-primary btn-block">
               <br>
               </form>
              </div>
<?php
if(isset($_GET['errorpass'])){
echo "
<div class='alert alert-danger-alt alert-dismissable'>
                <span class='glyphicon glyphicon-exclamation-sign'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>Datos Incorrectos, Vuelva a intentarlo.</div>
";
}else{
echo "";
}
?>
<?php
if(isset($_GET['erroractiv'])){
echo "
<div class='alert alert-danger-alt alert-dismissable'>
                <span class='glyphicon glyphicon-exclamation-sign'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button> Usuario del sistema INACTIVO.</div>
";
}else{
echo "";
}
?>

<?php
if(isset($_GET['nopass'])){
echo "
<div class='alert alert-danger-alt alert-dismissable'>
                <span class='glyphicon glyphicon-exclamation-sign'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>No ha introducido una contraseña.</div>
";
}else{
echo "";
}
?>
<?php
if(isset($_GET['sucess'])){
echo "
<div class='alert alert-success-alt alert-dismissable'>
                <span class='glyphicon glyphicon-ok'></span>
                <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
                    ×</button>Su registro ha sido satisfactorio.</div>
";
}else{
echo "";
}
/*
switch ($estadoPGM) {
case "Excelente":
    ?> <li><a href="#"><h3> Estado PGM: </h3><h4><span class="green">.</span><?php echo $estadoPGM; ?></h4></a></li> <?php
    break;
case "Bien":
    ?> <li><a href="#"><h3> Estado PGM: </h3><h4><span class="blue">.</span><?php echo $estadoPGM; ?></h4></a></li> <?php
    break;
case "Lento":
    ?> <li><a href="#"><h3> Estado PGM: </h3><h4><span class="orange">.</span><?php echo $estadoPGM; ?></h4></a></li> <?php
    break;
case "Bloqueado":
    ?> <li><a href="#"><h3> Estado PGM: </h3><h4><span class="red">.</span><?php echo $estadoPGM; ?></h4></a></li> <?php
    break;
case "Reiniciando":
    ?> <li><a href="#"><h3> Estado PGM: </h3><h4><span class="grey">.</span><?php echo $estadoPGM; ?></h4></a></li> <?php
    break;
  }*/
?>
        </div>
    </div>
<div class="panel-footer">
			<p class="text-center">Direccion de Sistemas - Municipalidad de Bariloche</p>
	</div>
</div>
</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
