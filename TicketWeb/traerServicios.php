<?php
include("inc/config.php");
include('inc/validar.php');
if(isset($_POST["idAsunto"]))
	{
		$asunto = $_POST['idAsunto'];

		$opciones = '<option value="0"> Elige un servicio </option>';

		$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
		mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
		$strConsulta = "SELECT id_asuntoS,nombre_asuntoS FROM servicios WHERE pertenece = $asunto";
		$result = $link->query($strConsulta);


		while( $fila = $result->fetch_array() )	{
			$opciones.='<option value="'.$fila["id_asuntoS"].'">'.$fila["nombre_asuntoS"].'</option>';
			}

		echo $opciones;
	}
	?>
