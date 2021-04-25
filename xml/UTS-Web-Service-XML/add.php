<?php
if(isset($_POST['submitSave'])){
    $pendaki = simplexml_load_file('data/pendaki.xml');
    $identitas = $pendaki -> addChild('identitas');
    $identitas -> addAttribute('id', $_POST['id']);
    $identitas -> addChild('nama', $_POST['nama']);
    $identitas -> addChild('alamat', $_POST['alamat']);
    $identitas -> addChild('umur', $_POST['umur']);
    file_put_contents('data/pendaki.xml', $pendaki -> asXML());
    header('location:index.php');
}
?>

<form method="post">
    <table cellpadding="2" cellspacing="2">
        <tr>
            <td>Id</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr>
            <td>Nama</td>
            <td><input type="text" name="nama"></td>
        </tr>
        <tr>
            <td>Alamat</td>
            <td><input type="text" name="alamat"></td>
        </tr>
        <tr>
            <td>Umur</td>
            <td><input type="text" name="umur"></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="save" name="submitSave"></td>
        </tr>
    </table>
</form>