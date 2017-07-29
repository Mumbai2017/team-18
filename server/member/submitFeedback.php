<?php
require_once '../config.php';
session_start();

$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);

if(isset($_POST['message'])) {
    $type = filter_input(INPUT_POST, "type", FILTER_SANITIZE_STRING);
    $t_id = filter_input(INPUT_POST, "t_id", FILTER_SANITIZE_STRING);
    $msg = filter_input(INPUT_POST, "message", FILTER_SANITIZE_STRING);
    
    $sql = "INSERT INTO feedback (type,t_id,msg,timestamp,s_type) VALUES (".$type.",".$t_id.",'".$msg."','".time()."',".$_SESSION['type'].")";
    if ($conn->query($sql) === TRUE) {
        if($type == 2) {
            header('location: viewVideosFeedback.php?lp='.$t_id.'&success=Feedback Submitted Successfully');
            exit();
        } else {
            header('location: viewFeedback.php?lp='.$t_id.'&success=Feedback Submitted Successfully');
            exit();
        }
    }
}
