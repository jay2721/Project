
<?php
$postnum=trim(strip_tags($_GET['listnum']));
$rid = trim(strip_tags($_POST['rid']));
$rpw = trim(strip_tags($_POST['rpw']));
$rcontent = trim(strip_tags($_POST['rcontent']));



$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}


$sql= "alter table reply auto_increment =1";
$end=mysqli_query($link,$sql) or die("auto error");

if($postnum!=null&& $rid!=null && $rpw!=null && $rcontent!=null){
  $query = "insert into reply(postnum,rid,rpw,rcontent) values(?,?,?,?)";

$stmt = mysqli_prepare($link, $query);

if($stmt === false) {
    echo('insert Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "isss",$postnum,$rid,$rpw,$rcontent);
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
    alert('댓글이 작성되었습니다.');
    location.href='read.php?listnum=$postnum';</script>";
}
else{
echo"<script>
    alert('댓글 작성 실패했습니다.');
    history.back();</script>";
}
//echo $postnum;
//echo $postnum;
//echo $rid;
//echo $rpw;
//echo $rcontent;

?>

