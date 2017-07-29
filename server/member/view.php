<?php 
require '../functions.php';
require_once '../config.php';
session_start();
if(!checkLogin()) {
    header("location: ../index.php?error=Not Authorised");
    exit();
}
$feedBackGiven = "";
$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
$id = filter_input(INPUT_GET, "lp", FILTER_SANITIZE_STRING);
if ( $id != "" ) {
    $sql = "SELECT * FROM `lesson_plan` WHERE id=".$id." AND user_id = ".$_SESSION['user_id'];
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $id = $row['id'];       
            $user_id = $row['user_id'];
            $topic = $row['topic_id'];
            $description = $row['description'];
            $imageUrl = $row['image_url'];
            $timestamp = $row['timestamp'];
            $score = $row['score'];
            $topic_name = getTopicName($conn, $id);
            $u_id = getUnitID($conn, $id);
            $unit_name = getUnitName($conn, $u_id);
            $s_id = getSubjectID($conn, $u_id);
            $sub_name = getSubjectName($conn, $s_id);
            $feedBackGiven = '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div style="border-bottom-left-radius: 5px; border-bottom-left-radius: 5px;" class="body">
                            <div class="desc">'.$description.'</div>
                            <div class="otherData">Topic Name : '.$topic_name.'<br>Unit Name : '.$unit_name.'<br>Subject Name : '.$sub_name.'</div>
                        </div>
                    </div><br>';
            
           
        }
    }
}

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CGF Member</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <div style="float: left; margin: 10px; padding: 10px 20px; background: blue; border-radius: 5px;"><a style="color: white;" href="index.php">Home</a></div>
        <div style="float: right; margin: 10px; padding: 10px 20px; background: red; border-radius: 5px;"><a style="color: white;" href="logout.php">Logout</a></div>
        <div class="clearfix"></div>
        <br>
        <div class="container">
            <div class="logo"><img src="../images/logo.png"></div>
            <div class="bodyContainer" align="center">
                <?php 
                    echo $feedBackGiven;
                 ?>
            </div>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
