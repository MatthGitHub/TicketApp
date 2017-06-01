<?php
include("inc/config.php");
include('inc/validar.php');
if(isset($_POST["idArea"]))
	{
		$area = $_POST['idArea'];

		$opciones = '<option value="0"> Elige un asunto </option>';

		$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
		mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
		$strConsulta = "SELECT id_asuntoP,nombre FROM asuntos WHERE fk_area = $area AND visible = 0";
		$result = $link->query($strConsulta);


		while( $fila = $result->fetch_array() )	{
			$opciones.='<option value="'.$fila["id_asuntoP"].'">'.$fila["nombre"].'</option>';
			}

		echo $opciones;
	}
	?>
