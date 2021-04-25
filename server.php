<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "uts-web-service-json";

mysql_connect($server, $username, $password) or die("<h1>Failed to connect mysql: </h1>". mysql_error());
mysql_select_db($database) or die("<h1>Failes to select database: </h1>". mysql_error());

@$operasi = $_GET['operasi'];
switch($operasi){
    case "view":
        $query_tampil_kopi = mysql_query("SELECT*FROM kopi") or die(mysql_error());
        $data_array = array();
        
        while($data = mysql_fetch_assoc($query_tampil_kopi)){
            $data_array[] = $data;
        }

        echo json_decode($data_array);
        break;

    case "insert":
        @$brand = $_GET['brand'];
        @$rasa = $_GET['rasa'];
        @$stok = $_GET['stok'];
        @$harga = $_GET['harga'];

        $query_insert_data = mysql_query("INSERT INTO kopi (brand, rasa, stok, harga)
            VALUES ('$brand', '$rasa', '$stok', '$harga')");
        
        if($query_insert_data){
            echo "Data has been stored.";
        } else{
            echo "Failed to store data.". mysql_error();
        }
        break;

    case "get_kopi_by_id":
        @$id = $_GET['id'];

        $query_tampil_kopi = mysql_query("SELECT*FROM kopi WHERE id = '$id'") or die(mysql_error());

        $data_array = array();
        $data_array = mysql_fetch_assoc($query_tampil_kopi);

        echo "[". json_encode($data_array). "]";
        break;
    
    case "update":
        @$id = $_GET['id'];
        @$brand = $_GET['brand'];
        @$rasa = $_GET['rasa'];
        @$stok = $_GET['stok'];
        @$harga = $_GET['harga'];

        $query_update_kopi = mysql_query("UPDATE kopi SET brand = '$brand', rasa = '$rasa',
            stok = '$stok', harga = '$harga' WHERE id = '$id'");

        if($query_update_kopi){
            echo "Data has been updated.";
        } else{
            echo mysql_error();
        }
        break;

    case 'delete':
        @$id = $_GET['id'];
        
        $query_delete_kopi = mysql_query("DELETE FROM kopi WHERE id = '$id'");

        if($query_delete_kopi){
            echo "Data has been deleted.";
        } else{
            echo mysql_error();
        }
        break;

    default:
        break;
}

?>