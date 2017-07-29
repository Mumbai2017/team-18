<?php 
require '../functions.php';
require '../config.php';
session_start();
$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
if (!checkLogin()) {
    header("location: ../index.php?error=Please login");
    exit();
}
?>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CFG Member</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <div style="float: left; margin: 10px; padding: 10px 20px; background: blue; border-radius: 5px;"><a style="color: white;" href="index.php">Home</a></div>
        <div style="float: right; margin: 10px; padding: 10px 20px; background: red; border-radius: 5px;"><a style="color: white;" href="logout.php">Logout</a></div>
        <div class="clearfix"></div>
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
            if(isset($_GET['error'])) {
                $error = filter_input(INPUT_GET, "error", FILTER_SANITIZE_STRING);
                if ($error != "") {
                    ?>
                <div class="alert alert-danger">
                    <strong>Error!</strong> <?php echo $error; ?>
                </div>
                    <?php
                }
            }
            ?>
                <a href="list.php?action=lp"><div class="lpButton listbtn">List My Lesson Plans</div></a>
                <a href="list.php?action=videos"><div class="videoButton listbtn">List My Videos</div></a>
                <br>
                <h3>Stats</h3>
                <br>
                <div class="statsLeft">Submitted Videos </div><div class="statsRight"><?php echo getNumberOfVideos($conn, $_SESSION['user_id']); ?></div>
                <div class="clearfix"></div><br>
                <div class="statsLeft">Submitted Plans  </div><div class="statsRight"><?php echo getNumberOfLP($conn, $_SESSION['user_id']); ?></div>
            </div>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
