<?php

$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname);

$estadoPGM = mysqli_query($link,"SELECT estado FROM estado_actual_pgm eap JOIN estados_pgm ep ON ep.id_estado = eap.fk_estado_pgm ");
$estadoPGM = mysqli_fetch_array($estadoPGM);
$estadoPGM = $estadoPGM['estado'];

?>


<div class="navbar navbar-default" role="navigation">
<div class="container-fluid">
  <div class="navbar-header">
  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
  </button>
  <a class="navbar-brand" href="#">Bienvenido </a>
  </div>
  <div class="navbar-collapse collapse">
  <ul class="nav navbar-nav">
    <li class="active"><a href="inicio.php">Inicio</a></li>
    <li><a href="fichadas/mis_fichadas.php">Mis fichadas</a></li>
  <?php
  if($_SESSION["permiso"] == 1) {
  ?> <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuarios<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="usuarios.php">Listar</a></li>
            <li><a href="registrarse.php">Nuevo</a></li>
          </ul>
        </li>
  <?php
  }
  ?>
  </ul>
  <ul class="nav navbar-nav navbar-right">
    <?php
    switch ($estadoPGM) {
    case "Excelente":
        ?> <li><a href="#"> Estado PGM: <h6><span class="green">.</span><?php echo $estadoPGM; ?></h6></a></li> <?php
        break;
    case "Bien":
        ?> <li><a href="#"> Estado PGM: <h6><span class="blue">.</span><?php echo $estadoPGM; ?></h6></a></li> <?php
        break;
    case "Lento":
        ?> <li><a href="#"> Estado PGM: <h6><span class="orange">.</span><?php echo $estadoPGM; ?></h6></a></li> <?php
        break;
    case "Bloqueado":
        ?> <li><a href="#"> Estado PGM: <h6><span class="red">.</span><?php echo $estadoPGM; ?></h6></a></li> <?php
        break;
    case "Reiniciando":
        ?> <li><a href="#"> Estado PGM: <h6><span class="grey">.</span><?php echo $estadoPGM; ?></h6></a></li> <?php
        break;
      } ?>
    <li><a href="form_cambiar_clave.php"> Cambiar clave </a></li>
    <li><a href=""> <?php echo $_SESSION["s_nombre_usuario"]; ?> </a></li>
    <li><a href="">Fecha:
    <?php
    // Establecer la zona horaria predeterminada a usar. Disponible desde PHP 5.1
    date_default_timezone_set('UTC');
    //Imprimimos la fecha actual dandole un formato
    echo date("d / m / Y");
    ?></a></li>
    <li><a href="inc/cerrar.php">Salir</a></li>
  </ul>
  </div><!--/.nav-collapse -->
</div><!--/.container-fluid -->
</div>
