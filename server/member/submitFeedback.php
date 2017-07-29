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
<<<<<<< HEAD
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
                <form method="post">
                    <input type="hidden" value="<?php echo $id; ?>" name="id">
                    <textarea name="desc" placeholder="Enter Description"></textarea>
                    <input type="submit" value="Submit Feedback">
                </form>
                <?php if(checkScore($conn, $id, 1) != 0) { ?>
                <form method="post" action="submitScore.php">
                    <input type="hidden" value="<?php echo $id; ?>" name="id">
                    <input type="text" name="score" placeholder="Enter Score">
                    <input type="hidden" name="type" value="1">
                    <input type="submit" value="Submit Score">
                </form>
                <?php } ?>
                <a href="list.php"><div class="goBack">Go Back</div></a>
            </div>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
=======
>>>>>>> 782e2e301fe8003899440e430402bdc128386688
