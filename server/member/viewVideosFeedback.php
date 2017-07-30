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
    $sql = "SELECT * FROM `videos` WHERE id=".$id." AND user_id = ".$_SESSION['user_id'];
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $id = $row['id'];       
            $youtube = $row['youtube_id'];
            $timestamp = $row['timestamp'];
            $lp = $row['lp_id'];       
            $score = $row['score'];
            $feedBackGiven .= '<div class="listLPShow showData">
                        <div class="header">'.date("d/m/Y H:i:s", $timestamp).'</div>
                        <div class="body">
                            <div class="desc"><div id="player"></div></div>
                            <div class="otherData"><a href="view.php?lp='.$lp.'"><div class="viewLessionPlanBtn">View Lession Plan</div></a></div>
                        </div>
                        <div class="footer">
                            <a href="viewVideosFeedback.php?lp='.$id.'"><div class="submitFeedbackBtn">View Feedback</div></a>
                        </div>
                    </div><br>';
        }
    }
    $feedBackGiven .= '<div class="feedbacks"><br><h4>Feedbacks</h4><br>';
    $sql = "SELECT * FROM `feedback` WHERE t_id=".$id." AND type = 2 ORDER BY timestamp";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $t_id = $row['t_id'];
            $type = $row['type'];
            $timepoint = $row['timepoint'];
            if($row['s_type'] == 1) {
                $feedBackGiven .= '<div style="float: right; text-align: right; background: activecaption;" class="message">'.$row['msg'].'</div>';
            } else {
                $feedBackGiven .= '<div style="float: left; text-align: left; background: bisque;" class="message">(<span style="cursor: pointer;" onclick="player.seekTo('.$timepoint.'); player.pauseVideo();">'.(round($timepoint/60,2)).":".(round($timepoint%60,2)).'</span>)    '.$row['msg'].'</div>';
            }
        }
    }
    $feedBackGiven .= '</div>';
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
        <script>
        var tag = document.createElement('script');
        tag.src = "https://www.youtube.com/iframe_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
        var player;

        function onYouTubeIframeAPIReady() {
            player = new YT.Player('player', {
                height: '390',
                width: '640',
                videoId: '<?php echo $youtube; ?>',
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
        }

        function onPlayerReady(event) {
            event.target.playVideo();
        }

        var done = false;

        function onPlayerStateChange(event) {
            if (event.data == YT.PlayerState.PLAYING && !done) {
                setTimeout(stopVideo, 6000);
                done = true;
            }
        }

        function stopVideo() {
            player.stopVideo();
        }
        </script>
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
                <br><br>
                <form method="post" action="submitFeedback.php">
                    <input type="hidden" name="type" value="<?php echo $type; ?>">
                    <input type="hidden" name="t_id" value="<?php echo $t_id; ?>">
                    <input type="text" name="message" placeholder="Reply to Feedback">
                    <input type="submit" value="Submit your Response">
                </form>
            </div>
        </div>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> <div id="google_translate_element"></div> <script type="text/javascript"> function googleTranslateElementInit() { new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element'); } </script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
    </body>
</html>