<head>
<?php
echo "<center><font color=#7B68EE>";
	
	echo "<h2>검색결과</h2>";
echo "</center></font>";

			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
		$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}

$scontent=trim(strip_tags($_POST['searchcontent']));
$fil=trim(strip_tags($_POST['searchfilter']));


$ss="%".$scontent."%";
if($scontent!=null){
if($fil=='title'){
	
			$sql = "SELECT * FROM board where title like ? order by num desc";
			$sql2 = "SELECT * FROM board where title like '$ss'";
			$result = mysqli_query($link,$sql2) or die("SQL 에러");
$no=mysqli_num_rows($result);
$stmt = mysqli_prepare($link, $sql);
if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "s", $ss);
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

	
}

else if($fil=='writer'){
			$sql = "SELECT * FROM board where id like ? order by num desc";
		
$sql3 = "SELECT * FROM board where id like '$ss'";
			$result = mysqli_query($link,$sql3) or die("SQL 에러2");
$no=mysqli_num_rows($result);

$stmt = mysqli_prepare($link, $sql);
if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt, "s", $ss);
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

}
$postnum=mysqli_num_rows($result);

?>
</head>

<body>

<center><?php echo"<h3>총 <font color=purple>".$postnum."</font>개의 검색 결과가 있습니다.</h3>";?>
	<div>
	<table width="1000" border="1" cellpadding="3" align="center" style="text-align:center">
				<tr bgcolor=#E6E6FA>
				
					<td class="num" height="10" width="80">번호</td>
					<td class="title" height="10">제목</td>
					<td class="id" height="10" width="150">작성자 id</td>
					<td class="ip" height="10" width="150">작성자 ip</td>
					<td class="date" height="10" width="150">작성일</td>
					<td class="view" height="10" width="100">조회수</td>

				</tr>

				<?php
	
			while(mysqli_stmt_fetch($stmt)){     
			
				?>
<form method="post">
<incput type="hidden" name="listnum" value="<?php echo $listnum?>">
				<tr>
					<td class="num"><?php echo $no?></td>
					<td class="title" onclick="location.href='read.php?listnum=<?php echo $a['num'];?>'"><?php echo $a['title']?></td>
					<td class="id"><?php echo $a['id']?></td>
					<td class="id"><?php echo $a['userip']?></td>
					<td class="date"><?php echo $a['date']?></td>
					<td class="view"><?php echo $a['view']?></td>
				</tr>
</form>
				<?php $no--; } mysqli_stmt_close($stmt);  }else echo "<h3><center>검색어를 입력하세요.</h3></center>";  ?>
	</table>
	</div>
<br>
<center>
	<div>
		<form method='post' >
			<select name="searchfilter">
			<option value='title'>제목
			<option value='writer'>작성자
			</select>
			<label><input type="text" name="searchcontent"></label>
			<input type="submit" value="검색">
		</form>
		<form method='post'>
			<input type="button" value="글쓰기"  onclick="location.href='write.php'">
			<input type="button" value="글목록"  onclick="location.href='list.php'">
		</form>
	</div>
</center>
</body>
