<?php
include('inc/config.php');

if(isset($_POST['observacion'])){
  $idTicket = $_POST['id_ticket'];
  $observacion = $_POST['observacion'];
  $usuario = $_SESSION["s_nombre_usuario"];

  $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
	mysqli_set_charset($link,'utf8');
	mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');

  $sql = "UPDATE tickets SET observacion = CONCAT(observacion, '\n', 'Respuesta por $usuario: $observacion') WHERE id_ticket = $idTicket";
  $error = mysqli_query($link,$sql);

  if($error){
    header("Location: detalle_ticket_responder.php?sucess&idTicket={$idTicket}");
    exit();
  }else{
    header("Location: detalle_ticket_responder.php?errordat&idTicket={$idTicket}");
    exit();
  }

}

?>
