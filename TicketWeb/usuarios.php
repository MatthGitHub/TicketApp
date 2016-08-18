<?php
include('inc/config.php');
if($_SESSION["logeado"] != "SI"){
header ("Location: index.php");
exit;
}

// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT id_usuario,nombre_usuario, contrasenia, activo, fk_permiso, CONCAT( nombre,  ' ', apellido ) AS empleado FROM usuarios u JOIN empleados e ON u.fk_empleado = e.id_empleado") or die(mysql_error());


?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Usuarios</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <style type="text/css">
  body{background: #000;}

     .media
    {
        /*box-shadow:0px 0px 4px -2px #000;*/
        margin: 20px 0;
        padding:30px;
    }
    .dp
    {
        border:10px solid #eee;
        transition: all 0.2s ease-in-out;
    }
    .dp:hover
    {
        border:2px solid #eee;
        transform:rotate(360deg);
        -ms-transform:rotate(360deg);
        -webkit-transform:rotate(360deg);
        /*-webkit-font-smoothing:antialiased;*/
    }
  </style>
  <body>
    <br>
        <div class="container">

      <!-- Static navbar -->
     <!-- Static navbar -->
      <?php include('inc/menu.php'); ?>
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
<div class="row">
              <table class="table table-hover">
                	<thead>
                    	<th> Nombre Usuario </th>
                      <th> Clave </th>
                      <th> Empleado </th>
                      <th> Activo </th>
                      <th> Permisos </th>
                      <?php if($_SESSION["permiso"] == 1){?> <th> Eliminar </th> <?php }?>
                    </thead>
                    <tbody>
                    	<?php while($usuario = mysqli_fetch_array($query)){ ?>
                        <?php if($usuario['fk_permiso'] == 1){ ?>
                        <tr class="danger">
                            <td> <?php echo $usuario['nombre_usuario']; ?> </td>
                            <td> <?php if ($usuario['contrasenia'] != ''){ echo $usuario['contrasenia'];}else{ echo "Sin pass";} ?> </td>
                            <td> <?php echo $usuario['empleado']; ?> </td>
                              <td> <?php echo $usuario['activo']; ?> </td>
                            <td> <?php if($usuario['fk_permiso'] == 1){ echo "Administrador";}else{ echo "Normal";} ?> </td>
                             <?php if($_SESSION["permiso"] == 1){?>
                            <td>  <a href="eliminar.php?id=<?php echo $usuario['id_usuario'];?>&tipo=usuario " role="button"  class="btn btn-danger btn-primary btn-block"> Eliminar </a></td>
							<?php }?>
                        </tr>
                        <?php }else{ ?>
                        <tr class="info">
                          <td> <?php echo $usuario['nombre_usuario']; ?> </td>
                          <td> <?php if ($usuario['contrasenia'] != ''){ echo $usuario['contrasenia'];}else{ echo "Sin pass";} ?> </td>
                          <td> <?php echo $usuario['empleado']; ?> </td>
                            <td> <?php echo $usuario['activo']; ?> </td>
                          <td> <?php if($usuario['fk_permiso'] == 1){ echo "Administrador";}else{ echo "Normal";} ?> </td>
                           <?php if($_SESSION["permiso"] == 1){?>
                          <td>  <a href="eliminar.php?id=<?php echo $usuario['id_usuario'];?>&tipo=usuario " role="button"  class="btn btn-danger btn-primary btn-block"> Eliminar </a></td>
							<?php }?>
                        </tr>
                        <?php } ?>
                        <?php } ?>
                    </tbody>
				</table>


</div>
      </div>

    </div> <!-- /container -->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
