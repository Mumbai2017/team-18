<?php
require '../config.php';
require '../functions.php';
if(checkAdmin() && checkReviewer()) {
    header("location: index.php?error=Not Authorised");
    exit();
} 
if(isset($_POST['score'])) {
    $id = filter_input(INPUT_POST, "id", FILTER_SANITIZE_STRING);
    $score = filter_input(INPUT_POST, "score", FILTER_SANITIZE_STRING);
    $type = filter_input(INPUT_POST, "type", FILTER_SANITIZE_STRING);
    if ( $type == 2 ) {
        $sql = "UPDATE `videos` SET `score`=".$score." WHERE id = ".$id;
        $conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
        if ($conn->query($sql) === TRUE) {
            header('location: submitVideoFeedback.php?lp='.$id.'&success=Score Submitted Successfully');
            exit();
        }
    } else if ( $type == 1 ) {
        $sql = "UPDATE `lesson_plan` SET `score`=".$score." WHERE id = ".$id;
        $conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
        if ($conn->query($sql) === TRUE) {
            header('location: submitFeedback.php?lp='.$id.'&success=Score Submitted Successfully');
            exit();
        }
    }
}