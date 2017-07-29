<?php
	include 'conn.php';
	$sql_get_seek_values = "SELECT * FROM `feedback`";
	$result_get_seek_values = mysqli_query($conn, $sql_get_seek_values);
	$seek_values = array();
	if(mysqli_num_rows($result_get_seek_values)>0){
		while($row = mysqli_fetch_assoc($result_get_seek_values)){
			array_push($seek_values,$row);
		}
	}
	echo json_encode(array("seek_values"=>$seek_values));
?>