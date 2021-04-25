<?php
$server = "localhost";
$username = "root";
$password = "";
$database = "uts-web-service-json";
$connect = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];
switch ($operasi) {
    case "view":
        $query_show_kopi = mysqli_query($connect, "SELECT*FROM kopi");
        $data_array = array();
        
        while($data = mysqli_fetch_assoc($query_show_kopi)){
            $data_array[] = $data;
        }

        echo json_encode($data_array);
        break;

    case "insert":
        @$brand = $_GET['brand'];
        @$rasa = $_GET['rasa'];
        @$stok = $_GET['stok'];
        @$harga = $_GET['harga'];

        $query_insert_data = mysqli_query($connect, "INSERT INTO kopi (brand, rasa, stok, harga) VALUES ('$brand', '$rasa', '$stok', '$harga')");
        
        if($query_insert_data){
            echo "Data has been stored.";
        } else{
            echo "Failed to store data.";
        }
        break;

    case "get_kopi_by_id":
        @$id = $_GET['id'];

        $query_tampil_kopi = mysqli_query($connect, "SELECT*FROM kopi WHERE id = '$id'");

        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_kopi);

        echo "[" . json_encode($data_array) . "]";
        break;
    
    case "update":
        @$brand = $_GET['brand'];
        @$rasa = $_GET['rasa'];
        @$stok = $_GET['stok'];
        @$harga = $_GET['harga'];
        @$id = $_GET['id'];

        $query_update_kopi = mysqli_query($connect, "UPDATE kopi SET brand='$brand', rasa='$rasa',
            stok='$stok', harga='$harga' WHERE id='$id'");

        if($query_update_kopi){
            echo "Data has been updated.";
        }
        break;

    case 'delete':
        @$id = $_GET['id'];
        
        $query_delete_kopi = mysqli_query($connect, "DELETE FROM kopi WHERE id = '$id'");

        if($query_delete_kopi){
            echo "Data has been deleted.";
        }
        break;

    default:
        break;
}

?>