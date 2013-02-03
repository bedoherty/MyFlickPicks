<?php

//  Takes a user id and retrieves their movie list

if (empty($_GET)) die();

if (array_key_exists("UserID", $_GET) == false) {
    echo "Missing value for UserID";
    die();
}

if (!isset($_GET['UserID']))
{
    die("Invalid User ID");
}
$userID = $_GET['UserID'];//$id_info->id;
$movieTitle = $_GET["MovieTitle"];

if(!$userID) {
    die('Invalid Owner Access token');
}

try
{
    $connection = new Mongo('mongodb://myflickpicks:myflickpicks@ds041387.mongolab.com:41387/testmongo');
    $database   = $connection->selectDB('testmongo');
    $collection = $database->selectCollection('myflickpicks');
    $oldItem = $collection->findOne(array('UserID' => $userID));
    echo json_encode($oldItem);
}
catch(MongoConnectionException $e)
{
    die("Failed to connect to database ".$e->getMessage());
}
?>