<?php
	include 'conn.php';
	$sql_get_seek_values = "SELECT * FROM `unit` WHERE `sub_id`=".$_POST['id'];
	$result_get_seek_values = mysqli_query($conn, $sql_get_seek_values);
	$subject_values = array();
	if(mysqli_num_rows($result_get_seek_values)>0){
		while($row = mysqli_fetch_assoc($result_get_seek_values)){
			array_push($subject_values,$row);
		}
	}
	echo json_encode($subject_values);
?>