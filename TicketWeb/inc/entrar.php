<?php
// Configura los datos de tu cuenta
include('config.php');

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');

if ($_POST['nombre_usuario']) {
	//Comprobacion del envio del nombre de usuario y contrasenia
	$nombre_usuario=htmlentities($_POST['nombre_usuario']);
	$contrasenia=md5($_POST['contrasenia']);
	if ($contrasenia==NULL) {
		header ("Location: ../index.php?nopass");
		exit();
	}else{
		$query = mysqli_query($link,"SELECT nombre_usuario,contrasenia,activo FROM usuarios WHERE nombre_usuario = '$nombre_usuario'") or die(mysql_error());
		$data = mysqli_fetch_array($query);
		if($data['contrasenia'] != $contrasenia) {
			//echo "No a introducido una contrasenia correcta";
			header ("Location: ../index.php?errorpass");
			exit();
		}else{
		$query = mysqli_query($link,"SELECT id_usuario,nombre_usuario,contrasenia,fk_permiso FROM usuarios WHERE nombre_usuario = '$nombre_usuario'") or die(mysql_error());
		$row = mysqli_fetch_array($query);
		$nombre_usuario2 = $row['nombre_usuario'];
		$_SESSION["s_nombre_usuario"] = $row['nombre_usuario'];

		$_SESSION["logeado"] = "SI";
		$_SESSION["permiso"] = $row['fk_permiso'];
		$_SESSION["id_usuario"] = $row['id_usuario'];
		/* Si aceptamos recordar los datos */
			if($_POST['recordar']){
				if ($HTTP_X_FORWARDED_FOR == ""){
					$ip = getenv(REMOTE_ADDR);
				}else{
							$ip = getenv(HTTP_X_FORWARDED_FOR);
							}
			$id_extreme = md5(uniqid(rand(), true));
			$id_extreme2 = $nombre_usuario2."%".$id_extreme."%".$ip;
			setcookie('id_extreme', $id_extreme2, time()+7776000,'/');
			$query = mysqli_query("UPDATE usuarios SET id_extreme='".$id_extreme."' WHERE nombre_usuario='".$nombre_usuario2."'") or die(mysql_error());
			}
			if($data['activo'] != 1){
				header("Location: ../index.php?erroractiv");
			}

			header ("Location: ../inicio.php");
			}
	}
}
?>
