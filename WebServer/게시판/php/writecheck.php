

<?php 
$userip=$_SERVER['REMOTE_ADDR'];
/*$username = $_POST['myid'];
$userpw = $_POST['mypw'];
$ptitle = $_POST['title'];
$pcontent = $_POST['content'];*/
$username = trim(strip_tags($_POST['myid']));
$userpw = trim(strip_tags($_POST['mypw']));
$ptitle = trim(strip_tags($_POST['title']));
$pcontent = trim(strip_tags($_POST['content']));




$pdate = date('Y-m-d');
$pview=0;


$myid = "jeonayg1067";
			$mypw = "dkdud2721*";

	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}

$sql= "alter table board auto_increment =1";
$end=mysqli_query($link,$sql) or die("auto error");

if($username!=null && $userpw!=null && $ptitle!=null && $pcontent!=null){


$query="INSERT board (id,pw,title,content,date,view,userip) values (?,?,?,?,?,?,?)";

$stmt = mysqli_prepare($link, $query);

if($stmt === false) {
    echo('insert Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "sssssis",$username,$userpw,$ptitle,$pcontent,$pdate,$pview,$userip);
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
    alert('작성한 글이 등록되었습니다.');
    location.href='list.php';</script>";
}else{
echo"<script>
    alert('작성 실패했습니다.');
    history.back();</script>";
}

?>

