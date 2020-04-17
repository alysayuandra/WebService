<?php
   include_once('config.php');

   $id = $_GET['id'];
   $nama_mkn = $_GET['nama'];
   $harga_mkn = $_GET['harga'];
   $desk_mkn = $_GET['deskripsi'];

   $geddata = mysqli_query($conn, "SELECT * FROM menu WHERE id='$id'");
   $rows = mysqli_num_rows($geddata);
    

    $response = array();

    if($rows > 0 ){
        $query = "UPDATE menu SET nama='$nama_mkn', harga='$harga_mkn'
        , deskripsi='$desk_mkn' WHERE id='$id'";
        $exequery = mysqli_query($conn, $query);

        if($exequery){
            $response['success'] = 1;
            $response['message'] = "Update Berhasil";
        } else {
            $response['success'] = 0;
            $response['message'] = "Update Gagal";
        }
    } else {
        $response['success'] = 0;
        $response['message'] = "Update Gagal, data tidak ditemukan";
    }
    

    echo json_encode($response);




?>