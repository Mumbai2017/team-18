<?php 
require '../functions.php';
require_once '../config.php';
session_start();
if(checkAdmin() && checkReviewer()) {
    header("location: ../index.php?error=Not Authorised");
    exit();
}
$teachers = "";
$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
$feedBackGiven = "";
$feedBackNotGiven = "";
$action = filter_input(INPUT_GET, "action", FILTER_SANITIZE_STRING);
if ( $action == "lp") {
    $sql = "SELECT * FROM `lesson_plan`";
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
            if(checkFeedbackSubmit($conn, $id, 1)) {
                $feedBackGiven .= '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div class="body">
                            <div class="img"><a href="'.$imageUrl.'" target="_blank"><img src="'.$imageUrl.'"></a></div>
                            <div class="desc">'.$description.'</div>
                            <div class="otherData">Topic Name : '.$topic_name.'<br>Unit Name : '.$unit_name.'<br>Subject Name : '.$sub_name.'</div>
                        </div>
                        <div class="footer">
                            Score : '.$score.'
                            <br><a href="submitFeedback.php?lp='.$id.'"><div class="submitFeedbackBtn">Submit More Feedback</div></a>
                        </div>
                    </div><br>';
            } else {
                $feedBackNotGiven .= '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div class="body">
                            <div class="desc">'.$description.'</div>
                            <div class="otherData">Topic Name : '.$topic_name.'<br>Unit Name : '.$unit_name.'<br>Subject Name : '.$sub_name.'</div>
                        </div>
                        <div class="footer">
                            <a href="submitFeedback.php?lp='.$id.'"><div class="submitFeedbackBtn">Submit Feedback</div></a>
                        </div>
                    </div><br>';
            }
        }
    }
} else if ( $action == "teachers" && $_SESSION['type'] == 7 ) {
    $sql = "SELECT * FROM `users` WHERE type = 1";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $username = $row['username'];       
            $phone_no = $row['phone_no'];
            $loc_lang = $row['loc_lang'];
            $loc_lat = $row['loc_lat'];
            $teachers .= '<div class="listLPShow showData">
                        <div class="header">'.$username.' - '.$phone_no.'</div>
                        <div class="footer">
                            <a href="https://www.google.co.in/maps/place/'.$loc_lat.', '.$loc_lang.'" target="_blank"><div class="submitFeedbackBtn">Show Location</div></a>
                        </div>
                    </div><br>';
        }
    }
} else if ( $action == "videos" ) {
    $sql = "SELECT * FROM `videos`";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $id = $row['id'];       
            $youtube = $row['youtube_id'];
            $timestamp = $row['timestamp'];
            $lp = $row['lp_id'];       
            $score = $row['score'];
            if(checkFeedbackSubmit($conn, $id, 2)) {
                $feedBackGiven .= '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div class="body">
                            <div class="desc"><iframe src="https://www.youtube.com/embed/'.$youtube.'" frameborder="0" allowfullscreen></iframe></div>
                            <div class="otherData"><a href="view.php?lp='.$lp.'"><div class="viewLessionPlanBtn">View Lession Plan</div></a></div>
                        </div>
                        <div class="footer">
                            Score : '.$score.'
                            <a href="submitVideoFeedback.php?lp='.$id.'"><div class="submitFeedbackBtn">Submit More Feedback</div></a>
                        </div>
                    </div><br>';
            } else {
                $feedBackNotGiven .= '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div class="body">
                            <div class="desc"><iframe src="https://www.youtube.com/embed/'.$youtube.'" frameborder="0" allowfullscreen></iframe></div>
                            <div class="otherData"><a href="view.php?lp='.$lp.'"><div class="viewLessionPlanBtn">View Lession Plan</div></a></div>
                        </div>
                        <div class="footer">
                            <a href="submitVideoFeedback.php?lp='.$id.'"><div class="submitFeedbackBtn">Submit Feedback</div></a>
                        </div>
                    </div><br>';
            }
        }
    }
}

?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CGF Admin</title>
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
                <?php if ( $action == "lp") { ?>
                <?php if ($feedBackNotGiven != "") {
                    echo '<h4>Awaiting Feedback</h4><br>';
                    echo $feedBackNotGiven;
                } ?>
                <hr>
                <?php if ($feedBackGiven != "") {
                    echo '<h4>Feedback Submitted</h4><br>';
                    echo $feedBackGiven;
                } ?>
                <?php } else if ( $action == "teachers") { ?>
                <?php echo $teachers; ?>
                <?php } else if ( $action == "videos") { ?>
                 <?php if ($feedBackNotGiven != "") {
                    echo '<h4>Awaiting Feedback</h4><br>';
                    echo $feedBackNotGiven;
                } ?>
                <hr>
                <?php if ($feedBackGiven != "") {
                    echo '<h4>Feedback Submitted</h4><br>';
                    echo $feedBackGiven;
                } ?>
                <?php } else { ?>
                <h4>Unauthorized Access</h4>
                <?php } ?>
            </div>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
