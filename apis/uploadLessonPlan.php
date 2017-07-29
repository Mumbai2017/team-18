<?php
	/*include 'conn.php';
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
	}*/
?>
<?php
 header('Content-type : bitmap; charset=utf-8');
 
 if(isset($_POST["encoded_string"])){
 	
	$encoded_string = $_POST["encoded_string"];
	$image_name = $_POST["image_name"];
	
	$decoded_string = base64_decode($encoded_string);
	
	$path = 'images/'.$image_name;
	
	$file = fopen($path, 'wb');
	
	$is_written = fwrite($file, $decoded_string);
	fclose($file);
	
	if($is_written > 0) {
		
		$connection = mysqli_connect('185.120.7.1', 'cfghphost', 'qB9vm{kyMcpr','cfghphos_main');
		$query = "INSERT INTO lesson_plan(image_url,timestamp) values('$image_name','$path');";
		
		$result = mysqli_query($connection, $query) ;
		
		if($result){
			echo "success";
		}else{
			echo "failed";
		}
		
		mysqli_close($connection);
	}
 }
?>