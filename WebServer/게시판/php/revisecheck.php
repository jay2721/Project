
<?php
/*$username = $_POST['myid'];
$userpw = $_POST['mypw'];
$ptitle = $_POST['title'];
$pcontent = $_POST['content'];*/


$username = trim(strip_tags($_POST['myid']));
$userpw = trim(strip_tags($_POST['mypw']));
$ptitle = trim(strip_tags($_POST['title']));
$pcontent = trim(strip_tags($_POST['content']));


$pdate = date('Y-m-d');
$listnum=trim(strip_tags(addslashes($_GET['listnum'])));	

$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
			
	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}

$sql="select *from board where num=?";

$stmt = mysqli_prepare($link, $sql);
if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "i", $listnum);
if($bind === false) {
    echo('파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}


$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['id'],$a['pw'],$a['title'],$a['content'],$a['date'],$a['view'],$a['userip']);

if(!$bindr) {
    echo('stmt_bind_result fail: ' . mysqli_error($link));
    exit();
}
while(mysqli_stmt_fetch($stmt)){

$aid=$a['id']; 
$apw=$a['pw'];
}
mysqli_stmt_close($stmt);    // statement 해제


if((strcmp($aid,$username)==0)&&(strcmp($apw,$userpw)==0)){
   
$query = "update board set title=?,content=? where num=?";

$stmt = mysqli_prepare($link, $query);

if($stmt === false) {
    echo('update Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "ssi", $ptitle, $pcontent,$listnum);
if($bind === false) {
    echo('파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}

mysqli_stmt_close($stmt); // statement 해제

echo"<script>
    alert('글이 수정되었습니다.');
    location.href='list.php'</script>";

}else{
echo"<script>
    alert('수정 실패했습니다.');
    history.back();</script>";

}

?>

