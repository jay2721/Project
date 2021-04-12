<script>


function check_all() {
var obj=document.getElementsByName("sfilter[]");
  for(i=0; i < obj.length; i++)
    obj[i].checked = true;
}

</script>
<head>
<center>
<?php
echo "<center><font color=#46BEFF>";
	
	echo "<h2>검색결과</h2>";
echo "</center></font>";
$myid = "jeonayg1067";
$mypw = "dkdud2721*";
$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}
$user=$_SERVER['REMOTE_ADDR'];
$uscon=trim(strip_tags($_POST['search']));
$udate = date('Y-m-d');

$sql3= "alter table seoul_user auto_increment =1";
$end=mysqli_query($link,$sql3) or die("auto error");

if($user!=null && $uscon!=null){

	$query="INSERT seoul_user (user,search,date) values (?,?,?)";
	$stmt = mysqli_prepare($link, $query);

	if($stmt === false) {
	    echo('insert Statement 생성 실패 : ' . mysqli_error($link));
	    exit();
	}

	$bind = mysqli_stmt_bind_param($stmt, "sss",$user,$uscon,$udate);
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

$sql3= "alter table seoul_pop auto_increment =1";
	$end=mysqli_query($link,$sql3) or die("auto error");


		$sql = "SELECT * FROM seoul_pop where keyword='$uscon'";
		$result = mysqli_query($link, $sql) or die("SQL 에러");
		$count=mysqli_num_rows($result);
		$first=1;
if($count==0){
$query="INSERT seoul_pop (keyword,hit) values (?,?)";

			$stmt = mysqli_prepare($link, $query);
if($stmt === false) {
			    echo('insert Statement 생성 실패 : ' . mysqli_error($link));
			    exit();
			}

$bind = mysqli_stmt_bind_param($stmt, "si",$uscon,$first);
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
		
}
else if($count!=0){
$a=mysqli_fetch_row($result);
			$view=$a[2];
			$view++;
			 $query = "update seoul_pop set hit=? where keyword=?";

			$stmt = mysqli_prepare($link, $query);

			if($stmt === false) {
			    echo('insert Statement 생성 실패 : ' . mysqli_error($link));
			    exit();
			}

			$bind = mysqli_stmt_bind_param($stmt, "is",$view,$uscon);
			if($bind === false) {
			    echo('파라미터 바인드 실패 : ' . mysqli_error($link));
			    exit();
			}

			$exec = mysqli_stmt_execute($stmt);
			if($exec === false) {
			    echo('쿼리 실행 실패 : ' . mysqli_error($link));
			    exit();
			}mysqli_stmt_close($stmt); // statement 해제
}

}


?>
<div>
<table>
<tr>
	
	<td>
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
	<input type="button" name="record" value="검색기록" onclick="location.href='oshistory.php'">
	<input type="button" name="best" value="인기검색어" onclick="location.href='ospop.php'">
<input type="button" name="total" value="전체현황" onclick="location.href='opendata.php'"></td>
</form>
</tr>

</div>
<div>
<br>


</table>

</div>
<?php

	
		$sfil=$_POST['sfilter'];
		$scon=trim(strip_tags($_POST['search']));
		$ss="%".$scon."%";
	

$cnt=count($sfil);

if($cnt==1){
		if($sfil[0]=='name'){
	
					$sql = "SELECT * FROM seoul where name like '$ss' order by num desc";
					$result = mysqli_query($link,$sql) or die("SQL 에러");

					$sql = "SELECT * FROM seoul where name like ? order by num desc";
					$stmt = mysqli_prepare($link, $sql);

					if($stmt === false) {
					    echo('aaStatement 생성 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bind = mysqli_stmt_bind_param($stmt,"s",$ss);
					if($bind === false) {
					    echo('aa파라미터 바인드 실패 : ' . mysqli_error($link));
					    exit();
					}

					$exec = mysqli_stmt_execute($stmt);
					if($exec === false) {
					    echo('aa쿼리 실행 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['register'],$a['date'],$a['name'],$a['address'],$a['phone'],$a['seat'],$a['state'],$a['openstate'],$a['closedate'],$a['rstart'],$a['rfinish']);

					if(!$bindr) {
					    echo('aastmt_bind_result fail: ' . mysqli_error($link));
					    exit();
					}
	

		}

		else if($sfil[0]=='address'){
					$sql = "SELECT * FROM seoul where address like '$ss' order by num desc";
					$result = mysqli_query($link,$sql) or die("SQL 에러2");

					$sql = "SELECT * FROM seoul where address like ? order by num desc";
					$stmt = mysqli_prepare($link, $sql);

					if($stmt === false) {
					    echo('aaStatement 생성 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bind = mysqli_stmt_bind_param($stmt,"s",$ss);
					if($bind === false) {
					    echo('aa파라미터 바인드 실패 : ' . mysqli_error($link));
					    exit();
					}

					$exec = mysqli_stmt_execute($stmt);
					if($exec === false) {
					    echo('aa쿼리 실행 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['register'],$a['date'],$a['name'],$a['address'],$a['phone'],$a['seat'],$a['state'],$a['openstate'],$a['closedate'],$a['rstart'],$a['rfinish']);

					if(!$bindr) {
					    echo('aastmt_bind_result fail: ' . mysqli_error($link));
					    exit();
					}
	
		}
		
}

else if($cnt==2){
					$sql = "SELECT * FROM seoul where address like '$ss' or name like '$ss' order by num desc";
					$result = mysqli_query($link,$sql) or die("SQL 에러3");

					$sql = "SELECT * FROM seoul where address like ? or name like ? order by num desc";
					$stmt = mysqli_prepare($link, $sql);

					if($stmt === false) {
					    echo('aaStatement 생성 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bind = mysqli_stmt_bind_param($stmt,"ss",$ss,$ss);
					if($bind === false) {
					    echo('aa파라미터 바인드 실패 : ' . mysqli_error($link));
					    exit();
					}

					$exec = mysqli_stmt_execute($stmt);
					if($exec === false) {
					    echo('aa쿼리 실행 실패 : ' . mysqli_error($link));
					    exit();
					}

					$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['register'],$a['date'],$a['name'],$a['address'],$a['phone'],$a['seat'],$a['state'],$a['openstate'],$a['closedate'],$a['rstart'],$a['rfinish']);

					if(!$bindr) {
					    echo('aastmt_bind_result fail: ' . mysqli_error($link));
					    exit();
					}
	
}

			$postnum=mysqli_num_rows($result);
	

	
?>

</center>
</head>

<body>
<center>

<?php
if($scon==null)
echo "<h3>검색어를 입력하세요.</h3>";
if($sfil==null)
echo "<h3>검색 필드를 선택해주세요.</h3>";
else if($scon!=null && $sfil!=null){
if($postnum==0)
echo "<h3>".$scon."에 대한 검색결과가 없습니다.</h3>";

else if($postnum!=0){
echo "<h3>".$scon."에 대한 총 <font color=purple>".$postnum."</font>개의 검색 결과가 있습니다.</h3>";


?>
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
$no=$postnum;
			while(mysqli_stmt_fetch($stmt)){  
					$listnum=$a['num'];	
				?>

				<tr>
					<td class="num"> <?php echo $no?></td>
					<td class="name"><?php echo $a['name']?></td>
					<td class="addr"><?php echo $a['address']?></td>
					<td class="phone"><?php echo $a['phone']?></td>	
					<td class="seat"><?php echo $a['seat']?></td>
					<td class="state"><?php echo $a['openstate']?></td>
				</tr>

				<?php 
			$no--;} mysqli_stmt_close($stmt); }}?>
	</table>
	</div>

</center>
</body>
