<?php
include('inc/config.php');
if($_SESSION["logeado"] != "SI"){
header ("Location: index.php");
exit;
}
$tipo = $_GET['tipo'];

//Comprobamos si vamos a insertar un usuario o un ticket
if($tipo == 'usuario'){
    // Si es usuario comprobamos que ning�n campo est� vac�o y que todos los campos existan.
    if(isset($_POST['username']) && !empty($_POST['username']) &&
    isset($_POST['password']) && !empty($_POST['password']) &&
    isset ($_POST['email']) && !empty($_POST['email']) &&
    isset($_POST['permisos']) && ($_POST['permisos'] != "")){
        // Si entramos es que todo se ha realizado correctamente
		$password = md5($_POST['password']);
		$username = htmlentities($_POST['username']);
		$mail = htmlentities($_POST['email']);
		$permisos = $_POST['permisos'];

        $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
        mysqli_select_db($link,$dbname);

		$queEmp = "SELECT username FROM usuarios WHERE username='$username'";
		$resEmp = mysqli_query($link,$queEmp) or die(mysql_error());
		$totEmp = mysql_num_rows($resEmp);
		if($totEmp > 0){
		echo "Nombre de usuario no disponible";
		exit();
		}

		$queEmp = "SELECT email FROM usuarios WHERE email='$mail'";
		$resEmp = mysqli_query($link,$queEmp) or die(mysql_error());
		$totEmp = mysql_num_rows($resEmp);
		if($totEmp > 0){
		echo "El mail ingresado no esta disponible";
		exit();
		}

        // Con esta sentencia SQL insertaremos los datos en la base de datos
        mysqli_query($link,"INSERT INTO usuarios (username,password,email,permisos)
        VALUES ('{$username}','{$password}','{$mail}','{$permisos}')");

        // Ahora comprobaremos que todo ha ido correctamente
        $my_error = mysql_error($link);

        if(!empty($my_error)) {

            header ("Location: registrarse.php?errordat");

        } else {

             header ("Location: usuarios.php");

        }

    } else {

         header ("Location: registrarse.php?errordb");

    }
}

if($tipo == 'ticket'){
    // Si es ticket comprobamos que ning�n campo est� vac�o y que todos los campos existan.
    if(isset($_POST['asunto']) && isset($_POST['servicios']) && isset($_POST['area'])
        && isset($_POST['interno']) && is_numeric($_POST['interno'])
        && $_POST['interno'] > 99 && $_POST['interno'] < 999){
      // Si entramos es que todo se ha realizado correctamente
      $asunto = $_POST['asunto'];
  		$servicio = $_POST['servicios'];
  		$obs = $_POST['observacion']." - Numero de interno: ".$_POST['interno'];
      $fecha = date('Y-m-d');
      $time = time();
      $hora = date('Y-m-d H:i:s');
      $usuario = $_SESSION['id_usuario'];
      $area = $_SESSION['area'];
      $areaR = $_POST['area'];


      $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
      mysqli_select_db($link,$dbname);

      // Con esta sentencia SQL insertaremos los datos en la base de datos
      mysqli_query($link,"INSERT INTO tickets (fecha,hora,fk_area_emisor,fk_usuario_emisor,fk_area_receptor,asunto,observacion,fk_estado)
      VALUES ('{$fecha}','{$hora}','{$area}','{$usuario}','{$areaR}','{$servicio}','{$obs}','1')");
      // Ahora comprobaremos que todo ha ido correctamente
      $my_error = mysql_error();
      $idTicket = mysqli_insert_id($link);
      mysqli_query($link,"INSERT INTO historial_tickets (fk_ticket,fk_usuario_emisor,fecha,fk_estado) VALUES ({$idTicket},'{$usuario}','{$fecha}',1)");

      if(!empty($my_error)) {

          header ("Location: ticket_nuevo.php?errordat");

      } else {

           header ("Location: tickets.php");

      }

    } else {

         header ("Location: ticket_nuevo.php?errordb");

    }
}


?>
