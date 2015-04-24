<?php
$servername = "";
$username = "";
$password = "";
$dbname="tweets";

$conn = new mysqli($servername, $username, $password, $dbname);
mysqli_set_charset($conn, 'utf8');
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

//exec('python /home/huffpost/huffpost.py');

if($_GET['tweet']){
  get_tweets_trend();  
}else{
  get_links_trend();
}


function get_tweets_trend(){
	global $conn;
    $sql="SELECT TRENDS.trend,TWEETS_TREND.tweet FROM TRENDS JOIN TWEETS_TREND ON TRENDS.trend_id=TWEETS_TREND.trend_id";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
    $associativeArray=array();
    $tweetArray=array();
    $i=0;
    $j=0;
    $previousTrend="";
    while($row = $result->fetch_assoc()) {
        if($previousTrend!=''){
           if($previousTrend==$row['trend']){
           	 $tweetArray[$i]=$row['tweet'];
           	 $i++;
           }else{
           	 $i=0;
           	 $associativeArray[$j]=array("trend"=>$previousTrend,"tweets"=>$tweetArray);
             $j++;
             $tweetArray=array();
             $tweetArray[$i]=$row["tweet"];
             $i++;
           }
        }else{
           $tweetArray[$i]=$row["tweet"];
           $i++;
        }
        $previousTrend=$row["trend"];
    }
     $associativeArray[$j]=array("trend"=>$previousTrend,"tweets"=>$tweetArray);
} 
//print_r($associativeArray);
echo json_encode($associativeArray);
$conn->close();
}


function get_links_trend(){
	global $conn;
   // echo "inside get_tweets_trends";
    $sql="SELECT TRENDS.trend,LINKS_TREND.link FROM TRENDS JOIN LINKS_TREND ON TRENDS.trend_id=LINKS_TREND.trend_id";
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
    //echo "results>0";
    $associativeArray=array();
    $linkArray=array();
    $i=0;
    $j=0;
    $previousTrend="";
    while($row = $result->fetch_assoc()) {
        if($previousTrend!=''){
           if($previousTrend==$row['trend']){
           	if($row['link']!=null){
           	 $linkArray[$i]=$row['link'];
           	 $i++;
           	}
           	 
           }else{
           	 $i=0;
           	 $associativeArray[$j]=array("trend"=>$previousTrend,"links"=>$linkArray);
             $j++;
             $linkArray=array();
             if($row['link']!=null){
             $linkArray[$i]=$row["link"];
             $i++;
             }
             
           }
        }else{
           if($row['link']!=null){
             $linkArray[$i]=$row["link"];
             $i++;
             }
           
        }
        $previousTrend=$row["trend"];
    }
    $associativeArray[$j]=array("trend"=>$row[$previousTrend],"links"=>$linkArray);

} 
echo json_encode($associativeArray);
$conn->close();
	
}


?>