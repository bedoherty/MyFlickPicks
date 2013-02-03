<?php

//  Takes a user id and retrieves their movie list


$userID = $_GET['UserID'];//$id_info->id;
$movieTitle = $_GET["MovieTitle"];

if(!$userID) {
    die('Invalid Owner Access token');
}

try
{
    $connection = new Mongo('mongodb://myflickpicks:myflickpicks@ds041387.mongolab.com:41387/testmongo');
    $database   = $connection->selectDB('testmongo');
    $collection = $database->selectCollection('myflickcollections');
    $oldItem = $collection->findOne(array('UserID' => $userID, $movieTitle => 'DATEHERE'));
    unset($oldItem[$movieTitle]);
    $collection->save($oldItem);
    echo json_encode($oldItem);
}
catch(MongoConnectionException $e)
{
    die("Failed to connect to database ".$e->getMessage());
}
?>