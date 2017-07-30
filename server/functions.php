<?php

function checkLogin() {
   if(isset($_SESSION['type'])) {
        return true;
    } else {
        return false;
    } 
}

function checkAdmin() {
    if($_SESSION['type'] == 7) {
        return true;
    } else {
        return false;
    }
}

function checkReviewer() {
    if($_SESSION['type'] == 5) {
        return true;
    } else {
        return false;
    }
}

function checkFeedbackSubmit($conn, $lp_id, $type) {
    $sql = "SELECT `id` FROM `feedback` WHERE type = ".$type." AND t_id = ".$lp_id;
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        return true;
    } else {
        return false;
    }
}

function getUsername($conn, $t_id) {
    $query = "SELECT username FROM users WHERE id=".$t_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['username'];
}

function getTopicName($conn, $t_id) {
    $query = "SELECT topic_name FROM topic WHERE id=".$t_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['topic_name'];
}

function getUnitName($conn, $u_id) {
    $query = "SELECT unit_name FROM unit WHERE id=".$u_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['unit_name'];
}

function getSubjectName($conn, $s_id) {
    $query = "SELECT sub_name FROM subject WHERE id=".$s_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['sub_name'];
}

function getSubjectID($conn, $u_id) {
    $query = "SELECT sub_id FROM unit WHERE id=".$u_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['sub_id'];
}

function getUnitID($conn, $t_id) {
    $query = "SELECT unit_id FROM topic WHERE id=".$t_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['unit_id'];
}

function checkScore($conn, $id, $type) {
    if ($type==1) {
        $query = "SELECT score FROM lesson_plan WHERE id=".$id;
    } else {
        $query = "SELECT score FROM videos WHERE id=".$id;
    }
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['score'];
}

function getYoutubeURL($conn, $id) {
    $query = "SELECT youtube_id FROM videos WHERE id=".$id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['youtube_id'];
}

function getImageURL($conn, $id) {
    $query = "SELECT image_url FROM lesson_plan WHERE id=".$id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['image_url'];
}

function getOcr($imageUrl) {
    $url = 'http://www.bitocr.com/api';
    $fields = array('url' => $imageUrl, 'apikey' => '184108e25758d07e', 'lang' => 'eng');
    $resource = curl_init();
    curl_setopt($resource, CURLOPT_URL, $url);
    curl_setopt($resource, CURLOPT_RETURNTRANSFER, 1);
    curl_setopt($resource, CURLOPT_POST, 1);
    curl_setopt($resource, CURLOPT_POSTFIELDS, $fields);
    $result = json_decode(curl_exec($resource),true);
    curl_close($resource);
    if($result['error']==0){
        echo($result['result']);		
    } else {
        echo("Error #" . $result['error_code'] . " " . $result['error_message']);	
    }
}

function getNumberOfVideos($conn, $user_id) {
    $query = "SELECT count(*) as c FROM videos WHERE user_id=".$user_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['c'];
}

function getNumberOfLP($conn, $user_id) {
    $query = "SELECT count(*) as c FROM lesson_plan WHERE user_id=".$user_id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['c'];
}