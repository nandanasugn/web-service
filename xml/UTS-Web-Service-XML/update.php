<?php
$pendaki = simplexml_load_file('data/pendaki.xml');

if(isset($_POST['submitSave'])) {
    foreach($pendaki->identitas as $identitas){
		if($identitas['id']==$_POST['id']){
			$identitas->nama = $_POST['nama'];
			$identitas->alamat = $_POST['alamat'];
            $identitas->umur = $_POST['umur'];
			break;
		}
	}
	file_put_contents('data/pendaki.xml', $pendaki->asXML());
	header('location:index.php');
}

foreach($pendaki->identitas as $identitas){
	if($identitas['id']==$_GET['id']){
		$id = $identitas['id'];
		$nama = $identitas->nama;
		$alamat = $identitas->alamat;
        $umur = $identitas->umur;
		break;
	}
}

?>

<form method="post">
	<table cellpadding="2" cellspacing="2">
		<tr>
			<td>Id</td>
			<td><input type="text" name="id" value="<?php echo $id; ?>" readonly="readonly"></td>
		</tr>
		<tr>
			<td>Nama</td>
			<td><input type="text" name="nama" value="<?php echo $nama; ?>"></td>
		</tr>
		<tr>
			<td>alamat</td>
			<td><input type="text" name="alamat" value="<?php echo $alamat; ?>"></td>
		</tr>
        <tr>
			<td>umur</td>
			<td><input type="text" name="umur" value="<?php echo $umur; ?>"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Save" name="submitSave"></td>
		</tr>
	</table>
</form>