<?php
$uploadedfileload="true";
$uploadedfile_size=$_FILES['uploadedfile'][size];

echo $_FILES[uploadedfile][name];

if ($_FILES[uploadedfile][size]>5000000){
  $msg=$msg."El archivo es mayor que 5000KB, debes reduzcirlo antes de subirlo<BR>";
  $uploadedfileload="false";
}

if (!($_FILES[uploadedfile][type] =="application/pdf" OR $_FILES[uploadedfile][type] =="application/doc")){
  $msg=$msg." Tu archivo tiene que ser PDF o DOC. Otros archivos no son permitidos<BR>";
  $uploadedfileload="false";
}

$file_name=$_FILES[uploadedfile][name];
$add="archivos/$file_name";
if($uploadedfileload=="true"){
  if(move_uploaded_file ($_FILES[uploadedfile][tmp_name], $add)){
    echo " Ha sido subido satisfactoriamente";
  }else{
    echo "Error al subir el archivo";
  }
}else{
  echo $msg;
}
?>
