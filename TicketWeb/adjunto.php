<?php
include('inc/config.php');
include('inc/validar.php');
$adjunto = $_GET['adjunto'];


header("Location:archivos/$adjunto");
exit();
?>
