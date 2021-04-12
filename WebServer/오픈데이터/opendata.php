<script>


function check_all() {
var obj=document.getElementsByName("sfilter[]");
  for(i=0; i < obj.length; i++)
    obj[i].checked = true;
}

</script>
<head><?php
echo "<center><font color=#46BEFF>";
	
	echo "<h2>서울시 공연장</h2>";
echo "</center></font>";?>
<center>

<div>
<table>
<tr><td>
<form method="POST" action="osearch.php">
<input type="button" id="chk" name="all" value="전체선택" onclick='check_all();' >
<input type="checkbox" id ="mycheck" name="sfilter[]" value="name" >공연장 이름
<input type="checkbox" id="mycheck"name="sfilter[]" value="address" >주소
<form>
	<input type="text" name="search" placeholder="검색어를 입력하세요." style="width:250px;height:25px;">
 	<input type="submit" value="검색" onclick="output(this.form)" style="height:25px;"></td><td>&nbsp;&nbsp;</td>
</form>
<td>
<form>
	<input type="button" name="record" value="검색기록"  onclick="location.href='oshistory.php'">
	<input type="button" name="best" value="인기검색어" onclick="location.href='ospop.php'">
<input type="button" name="total" value="전체현황" onclick="location.href='opendata.php'"></td>
</form>
</tr>


</div>
<div>
<br>

</table>

</div>
</center>
</head>

<script>

function output(form){
form.submit();
};
</script>

<body>
<br>
<center>
 <style>
ul{  display:table;
  margin-left: auto;
  margin-right: auto;}
paging {text-align: center;}
li {float:left;display: inline-block; height: 20px; margin: 0 5px; padding: 0 5px; border: 1px solid #666; background: #eee; line-height: 21px;}

</style>
<?php
			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";
			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";

			$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}


			$sql = "SELECT * FROM seoul ORDER BY num";
			$result = mysqli_query($link, $sql) or die("SQL 에러");

			if(isset($_GET['page'])) {
					$page = $_GET['page'];
				} else {
					$page = 1;
				}


			$postnum=mysqli_num_rows($result);
			$onepage=25;

			$allpage=ceil($postnum/$onepage);

			$onesection=10;
			$nowsection=ceil($page/$onesection);
			$allsection=ceil($allpage/$onesection);

			$firstp=($nowsection*$onesection)-($onesection-1);

			if($nowsection==$allsection){
				$lastp=$allpage;}
			else{
				$lastp=$nowsection*$onesection;}


$prevPage = (($nowsection - 1) * $onesection); //이전 페이지, 11~20일 때 이전을 누르면 10 페이지로 이동.
	$nextPage = (($nowsection + 1) * $onesection) - ($onesection - 1); //다음 페이지, 11~20일 때 다음을 누르면 21 페이지로 이동.

			$paging='<ul>';		


//첫 페이지가 아니라면 처음 버튼을 생성
	if($page != 1) { 
		$paging .= '<li><a href="./opendata.php?page=1">처음</a></li>';
	}
	//첫 섹션이 아니라면 이전 버튼을 생성
	if($nowsection != 1) { 
		$paging .= '<li><a href="./opendata.php?page=' . $prevPage . '">이전</a></li>';
	}
	
	for($i = $firstp; $i <= $lastp; $i++) {
		if($i == $page) {
			$paging .= '<li>' . $i . '</li>';
		} else {
			$paging .= '<li><a href="./opendata.php?page=' . $i . '">' . $i . '</a></li>';
		}
	}
	
	//마지막 섹션이 아니라면 다음 버튼을 생성
	if($nowsection != $allsection) { 
		$paging .= '<li><a href="./opendata.php?page=' . $nextPage . '">다음</a></li>';
	}
	
	//마지막 페이지가 아니라면 끝 버튼을 생성
	if($page != $allpage) { 
		$paging .= '<li><a href="./opendata.php?page=' . $allpage . '">끝</a></li>';
	}

			$nowlimit = ($onepage * $page) - $onepage; //몇 번째의 글부터 가져오는지
	$sqlLimit = ' limit ' . $nowlimit . ', ' . $onepage; //limit sql 구문
	

	$sql = 'select * from seoul order by num' . $sqlLimit;
	$result = mysqli_query($link, $sql) or die("SQL 에러");
	
	$stmt = mysqli_prepare($link, $sql);

if($stmt === false) {
    echo('Statement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt);
if($bind === false) {
    echo('파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}

$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['register'],$a['date'],$a['name'],$a['address'],$a['phone'],$a['seat'],$a['state'],$a['openstate'],$a['closedate'],$a['rstart'],$a['rfinish']);

if(!$bindr) {
    echo('aastmt_bind_result fail: ' . mysqli_error($link));
    exit();
}
?>

<style>
ul{  display:table;
  margin-left: auto;
  margin-right: auto;}
paging {text-align: center;}
li {float:left;display: inline-block; height: 20px; margin: 0 5px; padding: 0 5px; border: 1px solid #666; background: #eee; line-height: 21px;}

</style>



<div>
	<table width="1200" border="1" cellpadding="3" align="center" style="text-align:center" >
				<tr bgcolor=#DCEBFF>
				
					<td class="num" height="10" width="70" >번호</td>
					<td class="name" height="10">공연장 이름</td>
					<td class="addr" height="10" width="420">주소</td>
					<td class="seat" height="10" width="120">전화번호</td>
					<td class="phone" height="10" width="70">좌석수</td>
					<td class="state" height="10" width="90">영업상태</td>
				</tr>
				<?php
							while(mysqli_stmt_fetch($stmt))
							{	
								$listnum=$a['num'];
					
				?>

				<tr>
					<td class="num"> <?php echo $listnum?></td>
					<td class="name"><?php echo $a['name']?></td>
					<td class="addr"><?php echo $a['address']?></td>
					<td class="phone"><?php echo $a['phone']?></td>	
					<td class="seat"><?php echo $a['seat']?></td>
					<td class="state"><?php echo $a['openstate']?></td>
				</tr>

				<?php
					} mysqli_stmt_close($stmt);?>
	</table>
	</div>
<br>


<div class="paging">

				<?php echo $paging ?>
			</div>



</center>
</body>

