<?php
 
$url = 'http://www.bitocr.com/api';
$fields = array('url' => $_GET['image_url'], 'apikey' => '184108e25758d07e', 'lang' => 'eng');
$resource = curl_init();
curl_setopt($resource, CURLOPT_URL, $url);
curl_setopt($resource, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($resource, CURLOPT_POST, 1);
curl_setopt($resource, CURLOPT_POSTFIELDS, $fields);
$result = json_decode(curl_exec($resource),true);
curl_close($resource);
 
if($result['error']==0){
    echo($result['result']);		
}else{
    echo("Error #" . $result['error_code'] . " " . $result['error_message']);	
}
 
?>