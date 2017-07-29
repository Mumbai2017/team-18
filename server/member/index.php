<?php 
require '../functions.php';
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
        <br>
        <div class="container">
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
                <a href="list.php?action=teachers"><div class="teachersButton listbtn">List Teachers</div></a>
                <a href="list.php?action=lp"><div class="lpButton listbtn">List Lesson Plans</div></a>
                <a href="list.php?action=videos"><div class="videoButton listbtn">List Videos</div></a>
            </div>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </body>
</html>
