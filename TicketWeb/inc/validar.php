<?php
if(($_SESSION["logeado"] != "SI")||($_SESSION["origen"] != "tickets")){
  header ("Location: index.php");
  exit();
}

?>
