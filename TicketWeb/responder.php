<?php
include('inc/config.php');

$idTicket = $_POST['id_ticket'];
if(isset($_POST['observacion'])&&(!empty($_POST['observacion']))){

  $observacion = $_POST['observacion'];
  $usuario = $_SESSION["s_nombre_usuario"];
  $idUsuario = $_SESSION["id_usuario"];

  $fecha = date('d-m-Y');
  $fecha2 = date('Y-m-d');
  $fecha3 = date('Y-m-d H:i:s');

  $link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
	mysqli_set_charset($link,'utf8');
	mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');

  $sql = "UPDATE tickets SET observacion = CONCAT(observacion, '\n','$fecha Respuesta por $usuario: $observacion') WHERE id_ticket = $idTicket";
  $error = mysqli_query($link,$sql);

  if($error){
    $sql = "INSERT INTO historial_tickets (fk_ticket,fk_usuario,fecha,hora,fk_estado) VALUES ($idTicket,$idUsuario,'$fecha2','$fecha3',4)";
    $error = mysqli_query($link,$sql);
    if($error){
      header("Location: detalle_ticket_responder.php?sucess&idTicket={$idTicket}");
      exit();
    }else{
      header("Location: detalle_ticket_responder.php?errordb&idTicket={$idTicket}");
      exit();
    }
  }else{
    header("Location: detalle_ticket_responder.php?errordb&idTicket={$idTicket}");
    exit();
  }

}else{
  header("Location: detalle_ticket_responder.php?errordat&idTicket={$idTicket}");
  exit();
}

?>
