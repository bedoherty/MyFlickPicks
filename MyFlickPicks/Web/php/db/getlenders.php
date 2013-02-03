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
    $oldItems = $collection->find(array($movieTitle => 'DATEHERE'));
    $idArray = array();
    foreach ($oldItems as $doc) {
        if ($doc['UserID'] !== $userID)
        {
            array_push($idArray, $doc['UserID']);
        }
        //echo json_encode($doc);
    }
    //echo json_encode($oldItems);
    echo json_encode($idArray);
    /*
    foreach ($oldItems as $item)
    {
        echo json_encode($item);
    }
    */
}
catch(MongoConnectionException $e)
{
    die("Failed to connect to database ".$e->getMessage());
}
?>