<?php 
require '../functions.php';
require '../config.php';
session_start();
$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
    
if(isset($_GET['lp'])) {
    $id = filter_input(INPUT_GET, "lp", FILTER_SANITIZE_STRING);
}
if(checkAdmin() && checkReviewer()) {
    header("location: index.php?error=Not Authorised");
    exit();
}   
if(isset($_POST['desc'])) {
    $id = filter_input(INPUT_POST, "id", FILTER_SANITIZE_STRING);
    $msg = filter_input(INPUT_POST, "desc", FILTER_SANITIZE_STRING);
    $timepoint = filter_input(INPUT_POST, "timepoint", FILTER_SANITIZE_STRING);
    $sql = "INSERT INTO feedback (type,t_id,msg,timepoint,timestamp,s_type) VALUES (2,".$id.",'".$msg."',".$timepoint.",'".time()."',".$_SESSION['type'].")";
    if ($conn->query($sql) === TRUE) {
        header('location: submitVideoFeedback.php?lp='.$id.'&success=Feedback Submitted Successfully');
        exit();
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
            <?php 
            if(isset($_GET['success'])) {
                $success = filter_input(INPUT_GET, "success", FILTER_SANITIZE_STRING);
                if ($success != "") {
                    ?>
                <div class="alert alert-success">
                    <strong>Success!</strong> <?php echo $success; ?>
                </div>
                    <?php
                }
            }
            ?>
                <div id="player"></div>

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
                        videoId: 'M7lc1UVf-VE',
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
                <br><br>
                <form method="post">
                    <input type="hidden" value="<?php echo $id; ?>" name="id">
                    <input id="timepoint" type="text" name="timepoint" placeholder="Time Point will be added here">
                    <textarea name="desc" placeholder="Enter Description" onclick="addYoutube()"></textarea>
                    <input type="submit" value="Submit Feedback">
                </form>
                <?php if(checkScore($conn, $id, 2) == 0) { ?>
                <form method="post" action="submitScore.php">
                    <input type="hidden" value="<?php echo $id; ?>" name="id">
                    <input type="text" name="score" placeholder="Enter Score">
                    <input type="hidden" name="type" value="2">
                    <input type="submit" value="Submit Score">
                </form>
                <?php } ?>
                <a href="list.php?action=videos"><div class="goBack">Go Back</div></a>
            </div>
        </div>
        <script>
        function addYoutube() {
            document.getElementById('timepoint').value = player.getCurrentTime();
        }
        </script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
