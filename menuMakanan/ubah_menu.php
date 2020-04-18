<?php
   include_once('config.php');

   $id = $_POST['id'];
   $nama_mkn = $_POST['nama_mkn'];
   $harga_mkn = $_POST['harga_mkn'];
   $desk_mkn = $_POST['desk_mkn'];

   $geddata = mysqli_query($conn, "SELECT * FROM menu WHERE id='$id'");
    $rows = mysqli_num_rows($geddata);
    
    $response = array();
  
    if($rows > 0){
        $query = "UPDATE menu SET nama_mkn='$nama_mkn', harga_mkn='$harga_mkn', desk_mkn='$desk_mkn'
        WHERE id='$id'";
         $exequery = mysqli_query($conn, $query);

         if($exequery){
            $response['success'] = 1;
            $response['message'] = "ubah berhasil";
            } else {
            $response['success'] = 0;
            $response['message'] = "ubah gagal";
            }
    } else{
        $response['success'] = 0;
        $response['message'] = "ubah gagal, data tidak ditemukan";
    }
    

    echo json_encode($response);


?>