
<?php
//--------------------------------Inicio de sesion------------------------

include('inc/config.php');
include('inc/validar.php');


//--------------------------------Fin inicio de sesion------------------------

//Parametros - var - librerias

//Capturo dni encontrado
$idTicket = $_GET['idTicket'];

//#########################################BUSCO TICKET################################
	$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
	mysqli_set_charset($link,'utf8');
	mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');

	$sql = "SELECT MAX(ht.id_historial) AS historial,ht.fk_ticket AS idTicket,t.observacion,u.nombre_usuario AS usuario
					FROM historial_tickets ht
					JOIN tickets t ON t.id_ticket = ht.fk_ticket
					LEFT JOIN usuarios u ON u.id_usuario = ht.fk_usuario
					WHERE fk_ticket = $idTicket";
	$stmt = mysqli_query($link,$sql);

	while ($ticket=mysqli_fetch_array($stmt)){
		$idTicket = ($ticket['idTicket']);
		$observacion = $ticket['observacion'];
		$usuario = $ticket['usuario'];

		//echo "ID:".$ticket['observacion'];
		//exit();
	}

?>

<!DOCTYPE html">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="../images/icons/logo_vet.png" sizes="16x16">
    <title>Detalle ticket</title>

		<!-- Bootstrap -->
    <script src="js/jquery-1.12.3.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">


	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  <script type="text/javascript" src="js/jquery-1.12.3.js"></script>

	<script type="text/JavaScript">

	</script>

</head>


<body >

	<div class="container">
		<br>
			<?php include("inc/menu.php"); ?>

      <!-- Main component for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h4 class="text-center bg-info">Detalle Ticket</h4>
			<div class="container">
				<form name="form1" method="post" action="responder.php" >
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<div class="panel panel-default">
								<div class="panel-body">
									<form class="form form-signup" role="form">

										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-keyboard-o fa-fw"></i> Numero de ticket </span>
												<input name="id_ticket" type="text" class="form-control" id="id_ticket" value="<?php echo $idTicket;?>" readonly="readonly"/>
											</div>
										</div>

										<div class="form-group">
										 <span class="input-group-addon"><i class="fa fa-commenting-o fw" aria-hidden="true"></i> Observacion y mensajes</span>
										 <textarea class="form-control" rows="8" id="observacionV" name="observacionVS"  disabled="disabled"><?php echo $observacion;?></textarea>
									</div>

											<div class="form-group">
											   <div class="input-group">
													<span class="input-group-addon"><i class="fa fa-id-card fa-fw"></i> Usuario receptor</span>
													<input name="usuario" type="text" class="form-control" id="usuario" value="<?php echo $usuario;?>" readonly="readonly"/>
											   </div>
											</div>

											<div class="form-group">
				 						   <span class="input-group-addon"><i class="fa fa-commenting-o fw" aria-hidden="true"></i> Responder </span>
				 						   <textarea class="form-control" rows="5" id="observacion" name="observacion"></textarea>
				 					  </div>

									</form>

									<input id="responder" name="responder" type="submit" action="" class="btn btn-sm btn-primary btn-block" value="Responder" />

								</div>
								<?php
					 if(isset($_GET['sucess'])){
					 echo "
					 <div class='alert alert-success-alt alert-dismissable'>
									 <span class='glyphicon glyphicon-ok'></span>
									 <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
										 ×</button>Listo! Respuesta enviada.</div>
					 ";
					 }else{
					 echo "";
					 }
					 ?>
					 <?php
					 if(isset($_GET['errordat'])){
					 echo "
					 <div class='alert alert-warning-alt alert-dismissable'>
									 <span class='glyphicon glyphicon-exclamation-sign'></span>
									 <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>
										 ×</button>Ha habido un error.</div>
					 ";
					 }else{
					 echo "";
					 }
					 ?>
					 	</div>
						</div>
					</div>
				</form>
				<form method="post" action="tickets_recientes.php" >
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<div class="panel panel-default">
								<div class="panel-body">
									<input id="cerrar" name="cerrar" type="submit"  class="btn btn-sm btn-danger btn-block" value="Cerrar" />
								</div>
							</div>
						</div>
					</div>
				</form>
			</div><!-- Container 2 -->
		</div> <!-- Jumbotron -->
	</div> <!-- Container -->
</body>
</html>
