<?php

include '/conn.php';

if($_SERVER['REQUEST_METHOD']=='POST')
{
	
	$username =urldecode( $_POST["username"]);
	$password = urldecode( $_POST["password"]);
	

	if(isset($_POST["username"]) && isset($_POST["password"])  )
	{
		$response = array();

		$stmt = $conn->prepare("INSERT INTO users(username,password) VALUES(?,?)");
		$stmt->bind_param("is",$username,$password);
		$result = $stmt->execute();

		if($result)
		{
			$response['result'] = "Inserted";
			$response['username'] = $username;
			$response['password'] = $password;

			echo json_encode($response);
		}

		else
		{
			$response['result'] = "Not Inserted";
			$response['username'] = $username;
			$response['password'] = $password;

			echo json_encode($response);
		}

	}
}

?>