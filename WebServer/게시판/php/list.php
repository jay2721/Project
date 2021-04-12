<head>
<?php
echo "<center><font color=#7B68EE>";
	
	echo "<h2>게시판</h2>";
echo "</center></font>";

			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";

			$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}

			
			$sql = "SELECT * FROM board ORDER BY num desc";
			$result = mysqli_query($link, $sql) or die("SQL 에러");

			
			if(isset($_GET['page'])) {
					$page = $_GET['page'];
				} else {
					$page = 1;
				}


			$postnum=mysqli_num_rows($result);
			$onepage=15;

			$allpage=ceil($postnum/$onepage);

			$onesection=10;
			$nowsection=ceil($page/$onesection);
			$allsection=ceil($allpage/$onesection);

			$firstp=($nowsection*$onesection)-($onesection-1);

			if($nowsection==$allsection){
				$lastp=$allpage;}
			else{
				$lastp=$nowsection*$onesection;}


$prevPage = (($nowsection - 1) * $onesection); 
	$nextPage = (($nowsection + 1) * $onesection) - ($onesection - 1);

			$paging='<ul>';		



	if($page != 1) { 
		$paging .= '<li><a href="./list.php?page=1">처음</a></li>';
	}
	
	if($nowsection != 1) { 
		$paging .= '<li><a href="./list.php?page=' . $prevPage . '">이전</a></li>';
	}
	
	for($i = $firstp; $i <= $lastp; $i++) {
		if($i == $page) {
			$paging .= '<li>' . $i . '</li>';
		} else {
			$paging .= '<li><a href="./list.php?page=' . $i . '">' . $i . '</a></li>';
		}
	}
	
	
	if($nowsection != $allsection) { 
		$paging .= '<li><a href="./list.php?page=' . $nextPage . '">다음</a></li>';
	}
	
	
	if($page != $allpage) { 
		$paging .= '<li><a href="./list.php?page=' . $allpage . '">끝</a></li>';
	}

			$nowlimit = ($onepage * $page) - $onepage; 
	$sqlLimit = ' limit ' . $nowlimit . ', ' . $onepage; 
	
	$sql = 'select * from board order by num desc' . $sqlLimit; 


$stmt = mysqli_prepare($link, $sql);

if($stmt === false) {
    echo('aaStatement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt);
if($bind === false) {
    echo('aa파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('aa쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}

$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['id'],$a['pw'],$a['title'],$a['content'],$a['date'],$a['view'],$a['userip']);
if(!$bindr) {
    echo('aastmt_bind_result fail: ' . mysqli_error($link));
    exit();
}


	

?>
</head>
<script>

function output(form){
form.submit();
};
</script>
<body>
<style>
ul{  display:table;
  margin-left: auto;
  margin-right: auto;}
paging {text-align: center;}
li {float:left;display: inline-block; height: 20px; margin: 0 5px; padding: 0 5px; border: 1px solid #666; background: #eee; line-height: 21px;}

</style>
<center>
	<div>
	<table width="1000" border="1" cellpadding="3" align="center" style="text-align:center" >
				<tr bgcolor=#E6E6FA>
				
					<td class="num" height="10" width="80" >번호</td>
					<td class="title" height="10">제목</td>
					<td class="id" height="10" width="150">작성자 id</td>
					<td class="ip" height="10" width="150">작성자 ip</td>
					<td class="date" height="10" width="150">작성일</td>
					<td class="view" height="10" width="100">조회수</td>

				</tr>
				<?php
						$no=$postnum;
						while(mysqli_stmt_fetch($stmt))
							{	
								$listnum=$a['num'];
			
						$number=$no-($page-1)*$onepage;

					
				?>
<form method="post" action="read.php">
<input type="hidden" name="listnum" value="<?php echo $listnum?>">
				<tr>
					<td class="num"> <?php echo $number?></td>
					<td class="title" onclick="location.href='read.php?listnum=<?php echo $a['num'];?>'"><?php "<a href='read.php?listnum=".$a["num"]."'>"; echo $a['title']?></td>
					<td class="id"><?php echo $a['id']?></td>
					<td class="ip"><?php echo $a['userip']?></td>
					<td class="date"><?php echo $a['date']?></td>
					<td class="view"><?php echo $a['view']?></td>
				</tr>
</form>
				<?php $no--;}mysqli_stmt_close($stmt); ?>
	</table>
	</div>
<br>

	<div>
		<form method='POST' action="search.php" >
			<select name="searchfilter">
			<option value='title'>제목
			<option value='writer'>작성자
			</select>
			<input type="text" name="searchcontent">
			<input type="button" value="검색" onclick="output(this.form)">

			<input type="button" value="글쓰기"  onclick="location.href='write.php'">
		</form>
<div class="paging">

				<?php echo $paging ?>
			</div>
	</div>
</center>
</body>
