<?php

//  Takes a user id and a movie title and adds it to a list of movies

if (empty($_GET)) die();

//$newJSONString = json_encode($_GET);

if (array_key_exists("MovieTitle", $_GET) == false) {
    echo "Missing value for MovieTitle";
    die();
}

if (array_key_exists("UserID", $_GET) == false) {
    echo "Missing value for UserID";
    die();
}

//$id_info = json_decode(file_get_contents('https://graph.facebook.com/me/?fields=id&access_token=' . urlencode($_GET['OwnerToken'])));

$ownerID = "007";//$id_info->id;
$movieTitle = $_GET["MovieTitle"];

if(!$ownerID) {
    die('Invalid Owner Access token');
}

//$dataToInsert = $_GET;
//unset($dataToInsert['OwnerToken']);
//$dataToInsert['OwnerID'] = $ownerID;


try
{
    $connection = new Mongo('mongodb://myflickpicks:myflickpicks@ds041387.mongolab.com:41387/testmongo');
    $database   = $connection->selectDB('testmongo');
    $collection = $database->selectCollection('myflickpicks');
    //$collection->insert($dataToInsert);
    echo $movieTitle;//json_encode($dataToInsert);
}
catch(MongoConnectionException $e)
{
    die("Failed to connect to database ".$e->getMessage());
}
?>