<?php
	$dbhost = "localhost";
	$dbuser = "root";
	$dbpass = "";
	$dbname = "menu_makanan";
$conn = mysqli_connect($dbhost,$dbuser,$dbpass,$dbname) or die('Gagal Konek');

if($_SERVER['REQUEST_METHOD']== 'POST') {
	$id = $_POST['Id'];
	$nama_mkn = $_POST['Nama'];
	$harga_mkn = $_POST['Harga'];
        $desk_mkn = $_POST['Deskripsi'];
	
$sql = "INSERT INTO menu VALUES('".$id."','".$nama_mkn."','".$harga_mkn."', '".$desk_mkn."')";
$result = mysqli_query($conn, $sql);

if($result) {
    echo "success";
}else {
     echo "failure";
}
mysqli_close($conn);
}

?>