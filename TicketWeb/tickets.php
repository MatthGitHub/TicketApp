<?php
include('inc/config.php');
if($_SESSION["logeado"] != "SI"){
header ("Location: index.php");
exit;
}

$id_usuario =  $_SESSION["id_usuario"];
// Conectar a la base de datos
$link = mysqli_connect ($dbhost, $dbusername, $dbuserpass);
mysqli_select_db($link,$dbname) or die('No se puede seleccionar la base de datos');
$query = mysqli_query($link,"SELECT id_ticket,fecha,hora,respuesta,nombre FROM tickets t JOIN estados e ON t.fk_estado = e.id_estado WHERE t.fk_usuario_emisor = $id_usuario") or die(mysql_error());


?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lotes</title>

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
      <?php include('inc/menu.php'); ?>
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
<div class="row">
              <table class="table table-hover">
                	<thead>
                    	<th> Numero de ticket </th>
                      <th> Fecha </th>
            					<th> Hora </th>
            					<th> Respuesta </th>
                      <th> Estado </th>
                  </thead>
                    <tbody>
                    	<?php while($tickets = mysqli_fetch_array($query)){ ?>
                        <tr class="success">
                            <td> <?php echo $tickets['id_ticket']; ?> </td>
                            <td> <?php echo $tickets['fecha']; ?> </td>
                            <td> <?php echo $tickets['hora']; ?> </td>
                            <td> <?php echo $tickets['respuesta']; ?> </td>
                            <td> <?php echo $tickets['nombre']; ?> </td>
                            <td>  <a href="seguimiento_lote.php?idTicket=<?php echo $tickets['id_ticket'];?>" role="button"  class="btn btn-info btn-primary btn-block"> Buscar </a></td>
                        </tr>
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
