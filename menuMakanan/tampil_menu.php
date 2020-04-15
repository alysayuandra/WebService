<?php
	$dbhost = "localhost";
	$dbuser = "root";
	$dbpass = "";
	$dbname = "menu_makanan";
	$conn = mysqli_connect($dbhost,$dbuser,$dbpass,$dbname);
	$response = array();

	$sqltampil = "SELECT * FROM menu";
	$result = mysqli_query($conn, $sqltampil);

if(mysqli_num_rows($result) > 0) {
	$response["menu"] = array();
	while($row = mysqli_fetch_array($result)) {
		$datafield = array();
		$datafield["Id"] = $row["id"];
		$datafield["Nama"] = $row["nama_mkn"];
		$datafield["Harga"] = $row["harga_mkn"];
                $datafield["Deskripsi"] = $row["desk_mkn"];

		array_push($response["menu"], $datafield);
	}
	$response["success"] = 1;
	echo json_encode($response);
}else {
	$response["success"] = 0;
	echo json_encode($response);
}
mysqli_close($conn);

?>