<?php
	include 'conn.php';
	$t=time();
	
	if(isset($_POST["image_url"])){
		
		$image = $_POST["image_url"];
		$title = array_merge(range('a', 'z'), range('A', 'Z'));
		shuffle($title);
		$title = substr(implode($title), 0, 6);
		$upload_path = "../uploads/".$title.".jpg";
		$image_path = "http://cfg.hphost.in/uploads/".$title.".jpg";
		file_put_contents($upload_path, base64_decode($image));
		$updateImageOfLessonPlan = mysqli_query($conn,"INSERT INTO `lesson_plan` (`image_url`,`timestamp`) VALUES ('".$upload_path."',".$t.")");
		echo "true";
	}
	else{
		echo "false";
	}
?>