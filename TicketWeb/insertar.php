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
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//*********************************************************************************************************************************************************************************
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
if($tipo == 'ticket'){
    // Si es ticket comprobamos que ning�n campo est� vac�o y que todos los campos existan.
    if(isset($_POST['asunto']) && isset($_POST['servicios']) && isset($_POST['area'])
        && isset($_POST['interno']) && is_numeric($_POST['interno'])
        && $_POST['interno'] > 99 && $_POST['interno'] < 999){
      // Si entramos es que todo se ha realizado correctamente
      $asunto = $_POST['asunto'];
      $area = $_SESSION['area'];
  		$servicio = $_POST['servicios'];
  		$obs = $_POST['observacion']."\r\n"." - Numero de interno: ".$_POST['interno']."\r\n"."Adjunto: ".$_FILES["archivo"]['name'];
      $fecha = date('Y-m-d');
      $time = time();
      $hora = date('Y-m-d H:i:s');
      $usuario = $_SESSION['id_usuario'];
      $adjunto = $_FILES["archivo"]['name'];
      $tipo_archivo = $_FILES["archivo"]['type'];

      //echo "TIPO: ".$tipo_archivo;
      //exit();

      $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
      mysqli_select_db($link,$dbname);
      mysqli_set_charset($link,'utf8');

      // Con esta sentencia SQL insertaremos los datos en la base de datos
      mysqli_query($link,"INSERT INTO tickets (fecha,creador,servicio,observacion,fk_areaSolicitante)
      VALUES ('{$fecha}','{$usuario}','{$servicio}','{$obs}',$area)");
      // Ahora comprobaremos que todo ha ido correctamente
      $my_error = mysqli_error($link);
      $idTicket = mysqli_insert_id($link);
      mysqli_query($link,"INSERT INTO historial_tickets (fk_ticket,fk_usuario,fecha,hora,fk_estado) VALUES ({$idTicket},'{$usuario}','{$fecha}','{$hora}',1)");

      if ((strpos($tipo_archivo, "pdf") || strpos($tipo_archivo, "jpeg") || strpos($tipo_archivo, "doc") || strpos($tipo_archivo, "txt") || strpos($tipo_archivo, "docx"))){


        switch($tipo_archivo){
          case 'application/pdf':
              $adjunto = $idTicket.'.pdf';
              break;
          case 'text/plain':
            $adjunto = $idTicket.'.txt';
            break;
          case 'application/vnd.openxmlformats-officedocument.wordprocessingml.document':
            $adjunto = $idTicket.'.docx';
            break;
          case 'application/msword':
            $adjunto = $idTicket.'.doc';
            break;
          case 'image/jpeg':
            $adjunto = $idTicket.'.jpg';
            break;
          case 'application/vnd.oasis.opendocument.text':
            $adjunto = $idTicket.'.odt';
            break;
          case 'application/vnd.oasis.opendocument.spreadsheet':
            $adjunto = $idTicket.'.ods';
            break;
        }
        mysqli_query($link,"UPDATE tickets SET adjunto = '$adjunto' WHERE id_ticket = $idTicket");

      }

      if(!empty($my_error)) {
          mysqli_close($link);
          header ("Location: ticket_nuevo.php?errordat");

      } else {
        //--------------------------------------------------- Subo adjunto al servidor, dentro de la carpeta de archivos -----------------------------------------------------
        $uploadedfileload="true";
        $uploadedfile_size=$_FILES["archivo"]['size'];


        echo $_FILES["archivo"]['name'];

        if ($_FILES["archivo"]['size']>5000000){
          $msg=$msg."El archivo es mayor que 5000KB, debes reduzcirlo antes de subirlo<BR>";
          $uploadedfileload="false";
        }

       if (!(strpos($tipo_archivo, "pdf") || strpos($tipo_archivo, "jpeg") || strpos($tipo_archivo, "doc")|| strpos($tipo_archivo, "txt"))){
          $msg=$msg." Tu archivo tiene que ser PDF o DOC. Otros archivos no son permitidos<BR>";
          $uploadedfileload="false";
        }

        $file_name=$_FILES["archivo"]['name'];
        $add="archivos/$adjunto";
        if($uploadedfileload=="true"){
          if(copy ($_FILES["archivo"]['tmp_name'], $add)){
            echo " Ha sido subido satisfactoriamente";
          }else{
              echo "Error al subir el archivo";
          }
        }else{
          echo $msg;
        }
        mysqli_close($link);
        header ("Location: tickets_recientes.php");

      }

    } else {
        mysqli_close($link);
         header ("Location: ticket_nuevo.php?errordb");

    }
}


?>
