<?php
$connect = mysqli_connect("localhost", "root", "", "uts-web-service-xml");
$affectedRow = 0;
$xml = simplexml_load_file('data/pendaki.xml');
foreach($xml->children() as $row){
    $nama = $row->nama;
    $alamat = $row->alamat;
    $umur = $row->umur;

    $sql = "INSERT INTO pendaki(nama, alamat, umur) VALUES ('" . $nama . "','" . $alamat . "','" . $umur ."')";
    $result = mysqli_query($connect, $sql);
    if(!empty($result)){
        $affectedRow ++;
    }
}

if(isset($_GET['action'])){
    $pendaki = simplexml_load_file('data/pendaki.xml');
    $id = $_GET['id'];
    $index = 0;
    $i = 0;

    foreach($pendaki -> identitas as $identitas){
        if($identitas['id'] == $id){
            $index = $i;
            break;
        }
        $i++;
    }

    unset($pendaki -> identitas[$index]);
    file_put_contents('data/pendaki.xml', $pendaki -> asXML());
}

$pendaki = simplexml_load_file('data/pendaki.xml');

echo '<h1>UTS Web Service Mengolah Data XML</h1>';
echo '<h2>Daftar Informasi Pendaki</h2>';
echo '<br>Jumlah pendaki: ' . count($pendaki);

?>

<br>
<a href="add.php">Tambah Pendaki Baru</a>
<br>
<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <th>Id</th>
        <th>Nama</th>
        <th>Alamat</th>
        <th>Umur</th>
        <th>Opsi</th>
    </tr>

    <?php foreach($pendaki -> identitas as $identitas) { ?>

    <tr>
        <td><?php echo $identitas['id']; ?></td>
        <td><?php echo $identitas -> nama; ?></td>
        <td><?php echo $identitas -> alamat; ?></td>
        <td><?php echo $identitas -> umur; ?></td>
        <td><a href="update.php?id=<?php echo $identitas['id']; ?>">Edit</a> |
            <a href="index.php?action=delete&id=<?php echo $identitas['id']; ?>" onclick="return confirm('Apakah kamu yakin?')">Delete</a></td>
    </tr>
    <?php } ?>
</table>