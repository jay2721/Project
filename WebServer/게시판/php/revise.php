<head>
<?php
echo "<font color=#7B68EE><center>";
	
	echo "<h2>글 수정</h2>";
echo "</center></font>";

			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
	
	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}
$listnum=trim(strip_tags(addslashes($_GET['listnum'])));	
	
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

$ti=$a['title']; 
$con=$a['content'];
}
mysqli_stmt_close($stmt);    // statement 해제


/*$username = trim(strip_tags(addslashes($_POST['myid'])));
$userpw = trim(strip_tags(addslashes($_POST['mypw'])));
$ptitle = trim(strip_tags(addslashes($_POST['title'])));
$pcontent = trim(strip_tags(addslashes($_POST['content'])));*/
$pdate = date('Y-m-d');

?>
</head>
<script>

function output(form){
form.submit();
};

function back(){
history.back();
}
</script>
<body>

	<div>

<form method="post" action="revisecheck.php?listnum=<?php echo $listnum;?>">
<table width="850" height="500" border="1" cellpadding="3" align="center">
            <tr>
                <td>제목 : </td> 
                <td><input type="text" name="title" maxlength="50" value="<?php echo $ti;?>"/></td></tr>
 
            <tr><td>내용 : </td>
            <td><textarea rows="10" cols="100" name="content" ><?php echo $con; ?></textarea></td></tr>
         <tr>
                <td>ID :</td>
                <td><input type="id" name="myid" maxlength="10" /></td></tr>
         <tr>
                <td>PW : </td>
                <td><input type="password" name="mypw" maxlength="10" /></td></tr>

            <tr><td colspan="2" align="center">
	
                <input type="button" value="수정" onclick="output(this.form);">
                <input type="reset" value="취소"  onclick="back()"></td> </tr>
</table>
		
	</form>
	</div>


</body>
