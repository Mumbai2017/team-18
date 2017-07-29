<?php
	include 'conn.php';
	if(isset($_POST["image_url"])){
		$updateImageOfLessonPlan = mysqli_query($conn,"INSERT INTO `lesson_plan` (`image_url`) VALUES ('".$_POST["image_url"]."')");
		echo "true";
	}
	else{
		echo "false";
	}
?>