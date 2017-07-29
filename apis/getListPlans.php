<?php
	include 'conn.php';
	if(isset($_POST['id'])){
		$sql_get_all_plans = "SELECT * FROM `lesson_plan` WHERE `user_id`=".$_POST['id'];
		$result_get_plans = mysqli_query($conn,$sql_get_all_plans);
		$plans = array();
		if(mysqli_num_rows($result_get_plans) > 0){
			while($row = mysqli_fetch_assoc($result_get_plans)){
				array_push($plans,$row);
			}
		}
		echo json_encode($plans);
	}else{
		echo json_encode("invalid user");
	}
?>