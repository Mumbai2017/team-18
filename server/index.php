<!DOCTYPE html>
<?php
session_start();
if(isset($_SESSION['user'])) {
    header("location: member/index.php");
    exit();
}
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>CFG Admin</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <div class="container">
            <div class="logo"><img src="images/logo.png"></div>
            <form method="post" action="login.php"> 
                <div class="login" align="center">
                <?php 
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
                <?php 
                if(isset($_GET['success'])) {
                    $error = filter_input(INPUT_GET, "success", FILTER_SANITIZE_STRING);
                    if ($error != "") {
                        ?>
                    <div class="alert alert-success">
                        <strong>Success!</strong> <?php echo $error; ?>
                    </div>
                        <?php
                    }
                }
                ?>
                    <input type="text" placeholder="Enter your Username" name="username"><br>
                    <input type="password" placeholder="Enter your Password" name="password"><br>
                    <input type="submit" value="Login">
                </div>
            </form>
            <center><a href="https://paypal.me/harshitpeer"><img width="120px" src="images/donate.png"></a></center>
        </div>
        <br><br><script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script> <div id="google_translate_element"></div> <script type="text/javascript"> function googleTranslateElementInit() { new google.translate.TranslateElement({pageLanguage: 'en', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element'); } </script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>
    </body>
</html>
