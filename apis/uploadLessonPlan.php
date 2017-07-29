<?php
	include 'conn.php';
	$t=time();
	if(isset($_POST["image_url"])){
		$updateImageOfLessonPlan = mysqli_query($conn,"INSERT INTO `lesson_plan` (`image_url`,`timestamp`) VALUES ('".$_POST["image_url"]."',".$t.")");
		echo "true";
	}
	else{
		echo "false";
	}
?>