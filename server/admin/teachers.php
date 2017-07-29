<?php
require '../functions.php';
require_once '../config.php';
session_start();
function parseToXML($htmlStr) {
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
return $xmlStr;
}
header("Content-type: text/xml");
if(checkAdmin() && checkReviewer()) {
    header("location: ../index.php?error=Not Authorised");
    exit();
}
$teachers = "";
$conn = mysqli_connect($dbConfig['dbhost'],$dbConfig['dbuser'],$dbConfig['dbpass'],$dbConfig['dbname']);
$sql = "SELECT * FROM `users` WHERE type = 1";
    $result = $conn->query($sql);
   echo '<markers>';
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
             echo '<marker ';
                echo 'id="' . $row['id'] . '" ';
                echo 'number="' . parseToXML($row['phone_no']) . '" ';
                //echo 'address="' . parseToXML($row['address']) . '" ';
                echo 'lat="' . $row['loc_lat'] . '" ';
                echo 'lng="' . $row['loc_lang'] . '" ';
                echo 'type="' . $row['type'] . '" ';
                echo '/>';
        }
    }
    echo '</markers>';