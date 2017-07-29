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
