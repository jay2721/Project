<?php
	$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}


$postnum=trim(strip_tags($_GET['listnum']));
$rno = trim(strip_tags($_GET['rno'])); 
$rpw = trim(strip_tags($_GET['rpw']));

$sql = "select * from reply where no=?";

$stmt = mysqli_prepare($link, $sql);
if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "i", $rno);
if($bind === false) {
    echo('파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}

$bindr = mysqli_stmt_bind_result($stmt,$r['no'],$r['postnum'],$r['rid'],$r['rpw'],$r['rcontent']);

if(!$bindr) {
    echo('stmt_bind_result fail: ' . mysqli_error($link));
    exit();
}
while(mysqli_stmt_fetch($stmt)){
$cpw=$r['rpw'];
}
mysqli_stmt_close($stmt);    // statement 해제

if(strcmp($cpw,$rpw)==0){		

$sql2="delete from reply where no=?";

$stmt = mysqli_prepare($link, $sql2);
if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "i", $rno);
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
    alert('댓글이 삭제되었습니다.');
 location.href='read.php?listnum=".$postnum."';</script>";
}else{
echo"<script>
    alert('비밀번호가 틀립니다');
    history.back();</script>";
}

	
?>



