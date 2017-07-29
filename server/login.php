<?php
require_once './config.php';
session_start();
$username = filter_input(INPUT_POST, "username", FILTER_SANITIZE_STRING);
$pass = filter_input(INPUT_POST, "password", FILTER_SANITIZE_STRING);
$password = md5($pass);

$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);

$sql = "SELECT `password`,`type`,`id` FROM `users` WHERE username = '".$username."'";
$result = $conn->query($sql);
$error = true;
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        if($row['password'] == $password) {
            $error = false;
            $_SESSION['user'] = $username;
            $_SESSION['type'] = $row['type'];
            $_SESSION['user_id'] = $row['id'];
        }
    }
} else {
    $error = true;
}

if ( $error ) {
    header("location: index.php?error=Please check details");
    exit();
} else if ( $_SESSION['type'] == 1 ) {
    header("location: member/index.php?success=Successfully Logged in");
    exit();
} else {
    header("location: admin/index.php?success=Successfully Logged in");
    exit();
}


