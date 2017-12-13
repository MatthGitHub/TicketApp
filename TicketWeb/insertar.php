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
  		$obs = $_POST['observacion']."\r\n"." - Numero de interno: ".$_POST['interno'];
      $fecha = date('Y-m-d');
      $time = time();
      $hora = date('Y-m-d H:i:s');
      $usuario = $_SESSION['id_usuario'];
      $adjuntos = $_FILES["archivos"]['name'];
      $tipos = $_FILES["archivos"]['type'];
      $tamanios = $_FILES["archivos"]['size'];
      $cantidadAdjuntos = count($adjuntos);
      $direcciones = $_FILES["archivos"]['tmp_name'];
      $i = 0;

      $mes = date('n');
      $anio = date('Y');
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

      while($i < $cantidadAdjuntos){
        $adjunto = str_replace(" ","",$adjuntos[$i]);
        $tipo_archivo = $tipos[$i];
        $tamanio = $tamanios[$i];
        $direccion = $direcciones[$i];
        //echo "Cantidad: $cantidadAdjuntos Numero: $i Adjunto: $adjunto Tipo: $tipo_archivo Tamanio: $tamanio Direccion: $direccion";


      //exit();
        /*switch($tipo_archivo){
          case 'application/pdf':
              $adjunto = $adjunto.'.pdf';
              break;
          case 'text/plain':
            $adjunto = $adjunto.'.txt';
            break;
          case 'application/vnd.openxmlformats-officedocument.wordprocessingml.document':
            $adjunto = $adjunto.'.docx';
            break;
          case 'application/msword':
            $adjunto = $adjunto.'.doc';
            break;
          case 'image/jpeg':
            $adjunto = $adjunto.'.jpg';
            break;
          case 'application/vnd.oasis.opendocument.text':
            $adjunto = $adjunto.'.odt';
            break;
          case 'application/vnd.oasis.opendocument.spreadsheet':
            $adjunto = $adjunto.'.ods';
            break;
        }*/
          $sql = "INSERT INTO tickets_adjuntos VALUES ($idTicket,'$adjunto',$anio,$mes)";
          $adjerror = mysqli_query($link,$sql);

          if(!$adjerror){
            echo "Error al adjuntar en BD: $adjerror -- SQL: $sql";
            exit();
          }


        if(!empty($my_error)) {
            mysqli_close($link);
            header ("Location: ticket_nuevo.php?errordat");

        } else {
          //--------------------------------------------------- Subo adjunto al servidor, dentro de la carpeta de archivos -----------------------------------------------------
          $uploadedfileload="true";
          $uploadedfile_size=$tamanio;

          //echo $_FILES["archivo"]['name'];

          if ($uploadedfile_size > 5000000){
            $msg=$msg."El archivo es mayor que 5000KB, debes reduzcirlo antes de subirlo<BR>";
            $uploadedfileload="false";
          }

         /*if (!(strpos($tipo_archivo, "pdf") || strpos($tipo_archivo, "jpeg") || strpos($tipo_archivo, "doc")|| strpos($tipo_archivo, "txt")|| strpos($tipo_archivo, "sql"))){
            $msg=$msg." Tu archivo tiene que ser PDF o DOC. Otros archivos no son permitidos<BR>";
            $uploadedfileload="false";
          }*/


          $carpetaAnio = "archivos/$anio";
          $carpetaMes = "archivos/$anio/$mes";
          $carpetaTicket = "archivos/$anio/$mes/$idTicket";

          //Verifico si existe la carpeta con el año
          if (!file_exists($carpetaAnio)) {
              mkdir($carpetaAnio, 0777, true);
          }else{
            //Verifico si existe la carpeta con el mes
            if (!file_exists($carpetaMes)) {
                mkdir($carpetaMes, 0777, true);
            }else{
              //Verifico si existe la carpeta con el numero de ticket
              if (!file_exists($carpetaTicket)) {
                  mkdir($carpetaTicket, 0777, true);
              }
            }
          }

          //Verifico si existe la carpeta con el mes
          if (!file_exists($carpetaMes)) {
              mkdir($carpetaMes, 0777, true);
          }else{
            //Verifico si existe la carpeta con el numero de ticket
            if (!file_exists($carpetaTicket)) {
                mkdir($carpetaTicket, 0777, true);
            }
          }

          //Verifico si existe la carpeta con el numero de ticket
          if (!file_exists($carpetaTicket)) {
              mkdir($carpetaTicket, 0777, true);
          }


          $add="archivos/$anio/$mes/$idTicket/$adjunto";
          if($uploadedfileload=="true"){
            if(copy ($direccion, $add)){
              //echo "Adjunto: $adjunto Tipo: $tipo_archivo Tamanio: $tamanio Direccion: $direccion Final: $add";
              //exit();
              echo " Ha sido subido satisfactoriamente";
            }else{
                echo "Error al subir el archivo";
            }
          }else{
            echo $msg;
          }

        }
        //echo "Cantidad: $cantidadAdjuntos Numero: $i Adjunto: $adjunto Tipo: $tipo_archivo Tamanio: $tamanio Direccion: $direccion";
        $i++;
      }//Aca termina el FOR
      mysqli_close($link);
      header ("Location: tickets_recientes.php");

    } else {
        mysqli_close($link);
         header ("Location: ticket_nuevo.php?errordb");

    }
}


?>
