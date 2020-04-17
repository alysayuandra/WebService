<?php
    include_once('config.php');

    $id = $_POST['id'];

    $geddata = mysqli_query($conn, "SELECT * FROM menu WHERE id='$id'");
    $rows = mysqli_num_rows($geddata);

    $delete = "DELETE FROM menu WHERE id ='$id'";
    $exedelete = mysqli_query($conn, $delete);
   
    $response = array();

    if($rows > 0){
        if($exedelete){
            $response['success'] = 1;
             $response['message'] = "Delete berhasil";
         }
         else{
            $response['success'] = 0;
            $response['message'] = "Gagal hapus";
        }
    }
    else {
        $response['success'] = 0;
        $response['message'] = "Gagal hapus, data tidak ada";
    }
    


echo json_encode($response);





?>