<?php

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
    if ($type==1)
        $query = "SELECT score FROM lession_plans WHERE id=".$id;
    else
        $query = "SELECT score FROM videos WHERE id=".$id;
    $result = mysqli_query($conn, $query);
    $row = mysqli_fetch_assoc($result);
    return $row['score'];
}