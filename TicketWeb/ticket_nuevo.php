<?php
include('inc/config.php');
include('inc/validar.php');

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
mysqli_set_charset($link,'utf8');
$query = mysqli_query($link,"SELECT DISTINCT id_area,nombre_area FROM asuntos JOIN areas ON fk_area = id_area  WHERE visible = 1") or die(mysql_error());
$query2 = mysqli_query($link,"SELECT * FROM servicios") or die(mysql_error());

$strConsulta = "SELECT id_asuntoP, nombre FROM asuntos";
$result = $link->query($strConsulta);
$opciones = '<option value="0"> Elige una marca</option>';
while( $fila = $result->fetch_array() )
{
  $opciones.='<option value="'.$fila["id_asuntoP"].'">'.$fila["nombre"].'</option>';
}
mysqli_close($link);

?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" type="image/png" href="images/icons/ticket.png" sizes="16x16">
    <title>Nuevo Ticket</title>

    <!-- Bootstrap -->
    <script src="js/jquery-1.12.3.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">


	<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
  <script type="text/javascript" src="js/jquery-1.12.3.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      $("#area").change(function(){
        $.ajax({
          url:"traerAsuntos.php",
          type: "POST",
          data:"idArea="+$("#area").val(),
          success: function(opciones){
            $("#asunto").html(opciones);
          }
        })
      });
      $("#asunto").change(function(){
        $.ajax({
          url:"traerServicios.php",
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
  <body>

<div class="container">
		<br>
          <?php include('inc/menu.php'); ?>
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">

		<h4 class="text-center bg-info">Nuevo Ticket</h4>

	<div class="container">
		<form name="form1" enctype="multipart/form-data" method="post" action="insertar.php?tipo=ticket">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">

						<form class="form form-signup" role="form">
              <div class="form-group">
                  <span class="input-group-addon"><span class="glyphicon glyphicon-chevron-down"> Areas </span></span>
                  <div class="col-xs-15 selectContainer">
                      <select class="form-control" name="area" id="area">
                          <option> Eliga un area </option>
                          <?php while($areas = mysqli_fetch_array($query)){ ?>
                            <option value=<?php echo $areas['id_area'] ?>><?php echo $areas['nombre_area']?></option>
                          <?php } ?>
                      </select>
                  </div>
              </div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fa fa-chevron-down fw" aria-hidden="true"></i> Asuntos</span>
							<div class="col-xs-15 selectContainer">
								<select class="form-control" name="asunto" id="asunto">
								</select>
							</div>
						</div>
						<div class="form-group">
							<span class="input-group-addon"><i class="fa fa-chevron-down fw" aria-hidden="true"></i> Servicios</span>
							<div class="col-xs-15 selectContainer">
								<select class="form-control" name="servicios" id="servicios">
								</select>
							</div>
						</div>

					 <div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-phone fw" aria-hidden="true"></i> Numero interno</span>
								<input type="text" class="form-control"  name="interno" id="interno" value="" />
							</div>
						</div>

						<div class="form-group">
							<div class="input-group">
								<div class='input-group'>
									<span class="btn btn-default btn-file"><i class="fa fa-paperclip fa-fw"></i><input name="archivos[]" type="file" multiple="multiple" hidden>Seleccione un archivo...</span>
								</div>
							</div>
						</div>
						<br><br>

					   <div class="form-group">
						   <span class="input-group-addon"><i class="fa fa-commenting-o fw" aria-hidden="true"></i> Observaciones</span>
						   <textarea class="form-control" rows="5" id="observacion" name="observacion"></textarea>
					  </div>
					</div>

					<input type="submit" name="Submit" value="ENVIAR" class="btn btn-sm btn-primary btn-block">
				  </form>
				</div>
						 <?php
				if(isset($_GET['sucess'])){
				echo "
				<div class='alert alert-success-alt alert-dismissable'>
								<span class='glyphicon glyphicon-ok'></span>
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
								<span class='glyphicon glyphicon-exclamation-sign'></span>
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
								<span class='glyphicon glyphicon-exclamation-sign'></span>
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

  <div class="panel-footer">
			<p class="text-center">Direccion de Sistemas - Municipalidad de Bariloche</p>
	</div>
 </div> <!-- /container -->
 </body>
</html>
