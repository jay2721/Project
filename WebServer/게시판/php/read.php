<head>

<?php
echo "<font color=#7B68EE><center>";
	
	echo "<h2>게시글</h2>";
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

$pwriter=$a['id'];
$ptitle=$a['title']; 
$pcontent=$a['content'];
$pdate=$a['date'];
$vcount=$a['view']; 
$vcount++;
$pip=$a['userip'];

}

mysqli_stmt_close($stmt);    // statement 해제

?>

</head>
<script>
function output(form){
form.submit();
};

function postdelete(){
var pd=prompt("비밀번호를 입력하세요.",'');
if(pd==null)
location.href='read.php?listnum=<?php echo $listnum;?>';
else location.href='delete.php?listnum=<?php echo $listnum;?>&userpw='+pd;
};

function rd(rno){
var rdp=prompt("비밀번호를 입력하세요.",'');
if(rdp==null)
location.href='read.php?listnum=<?php echo $listnum?>';

else
location.href="replydelete.php?rpw="+rdp+"&listnum=<?php echo $listnum?>&rno="+rno;

};

function rrr(rno,rcon){
var rrcon=prompt("댓글 수정 폼",rcon);
var rrpw=prompt("비밀번호를 입력하세요.",'');

if(rrcon==null)
location.href='read.php?listnum=<?php echo $listnum?>';
else if(rrpw==null)
location.href='read.php?listnum=<?php echo $listnum?>';
else
location.href="replyrevise.php?rcontent="+rrcon+"&rpw="+rrpw+"&rno="+rno+"&listnum=<?php echo $listnum?>";
};


</script>
<body>

<?php	



$query = "update board set view=? where num=?";


$stmt = mysqli_prepare($link, $query);

if($stmt === false) {
    echo('update Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "ii", $vcount, $listnum);
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

?>
	<div>
		
<table width="850" height="400" border="1" cellpadding="3" align="center">
            <tr height="80">
                <td ><h4>제목 : <?php echo $ptitle;?></h4> <div align="right">조회수:  <?php echo $vcount; ?> 
              / 글쓴이id : <?php echo $pwriter; ?> / 글쓴이ip : <?php echo $pip; ?> /작성일 : <?php echo $pdate; ?> </div></td> </tr>
      
            <tr>
            <td> <?php echo $pcontent; ?><div>
			</div></td></tr>
     
	<form method='post'>
            <tr height="50"><td colspan="2" align="center">
                <input type="button" value="글삭제" onclick="postdelete()">
                			<input type="button" value="글수정" onclick="location.href='revise.php?listnum=<?php echo $listnum;?>'"> <input type="button" value="글목록" onclick="location.href='list.php'"> </td> </tr>
					
</table>
		</form>
	
	</div>


	
<?php 
$sql="select *from reply where postnum=?";

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

//$result = mysqli_stmt_get_result($stmt); // not work

$bindr = mysqli_stmt_bind_result($stmt,$reply['no'],$reply['postnum'],$reply['rid'],$reply['rpw'],$reply['rcontent']);
if(!$bindr) {
    echo('stmt_bind_result fail: ' . mysqli_error($link));
    exit();
}

?>
<div style="margin-left:320; margin-right:320;">
<h3>댓글목록</h3><hr>
<?php
	while(mysqli_stmt_fetch($stmt)){ 
		?>
		<div>
			<div><b><?php echo $reply['rid'];?></b></div>
			<div><?php echo nl2br("$reply[rcontent]"); ?></div>
			<div><?php $rno=$reply['no']; $rcon=$reply['rcontent'];?>

		<form method="post">
				<input type="button" value="댓글수정"  onclick="rrr(<?php echo $rno?>,'<?php echo $rcon?>')">
				<input type="button" value="댓글삭제" onclick="rd(<?php echo $rno?>)">
		</form>
<hr>
		</div>

	<?php } 
mysqli_stmt_close($stmt);    // statement 해제

?>

	<div>
		<form method="post" action="replycheck.php?listnum=<?php echo $listnum ?>">

			<input type="text" name="rid" id="rid" class="rid" size="15" placeholder="아이디">
			<input type="password" name="rpw" id="rpw" class="rpw" size="15" placeholder="비밀번호">
			<div style="margin-top:10px; ">
				<textarea name="rcontent" class="rcontent" id="rcontent" ></textarea>
				<input type="button" value="댓글" onclick="output(this.form)">
			</div>
		</form>
	</div> 
</div>



</body>
