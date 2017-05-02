<?php
include('inc/config.php');
include('inc/validar.php');
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


      $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
      mysqli_select_db($link,$dbname);

      // Con esta sentencia SQL insertaremos los datos en la base de datos
      mysqli_query($link,"INSERT INTO tickets (fecha,hora,creador,servicio,observacion)
      VALUES ('{$fecha}','{$hora}','{$usuario}','{$servicio}','{$obs}')");
      // Ahora comprobaremos que todo ha ido correctamente
      $my_error = mysqli_error();
      $idTicket = mysqli_insert_id($link);
      mysqli_query($link,"INSERT INTO historial_tickets (fk_ticket,fk_usuario,fecha,fk_estado) VALUES ({$idTicket},'{$usuario}','{$fecha}',1)");

      if(!empty($my_error)) {

          header ("Location: ticket_nuevo.php?errordat");

      } else {
        //--------------------------------------------------- Subo adjunto al servidor, dentro de la carpeta de archivos -----------------------------------------------------
        $uploadedfileload="true";
        $uploadedfile_size=$_FILES["archivo"]['size'];
        $tipo_archivo = $_FILES["archivo"]['type'];

/*        echo $_FILES['uploadedfile'][name];

        if ($_FILES[uploadedfile][size]>5000000){
          $msg=$msg."El archivo es mayor que 5000KB, debes reduzcirlo antes de subirlo<BR>";
          $uploadedfileload="false";
        }

       if (!(strpos($tipo_archivo, "pdf") || strpos($tipo_archivo, "jpeg") || strpos($tipo_archivo, "png")|| strpos($tipo_archivo, "txt"))){
          $msg=$msg." Tu archivo tiene que ser PDF o DOC. Otros archivos no son permitidos<BR>";
          $uploadedfileload="false";
        }*/

        $file_name=$_FILES["archivo"]['name'];
        $add="archivos/$file_name";
        if($uploadedfileload=="true"){
          if(copy ($_FILES["archivo"]['tmp_name'], $add)){
            echo " Ha sido subido satisfactoriamente";
          }else{
              echo "Error al subir el archivo";
          }
        }else{
          echo $msg;
        }
        header ("Location: tickets_recientes.php");

      }

    } else {

         header ("Location: ticket_nuevo.php?errordb");

    }
}


?>
