<script>


function check_all() {
var obj=document.getElementsByName("sfilter[]");
  for(i=0; i < obj.length; i++)
    obj[i].checked = true;
}

</script>
<head><?php
echo "<center><font color=#46BEFF>";
	
	echo "<h2>검색기록</h2>";
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
	<input type="button" name="record" value="검색기록" onlick="location.href='oshistory.php'">
	<input type="button" name="best" value="인기검색어"onclick="location.href='ospop.php'">
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


<body>
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
	
$nowuser=$_SERVER['REMOTE_ADDR'];

		
	
?>

<style>
ul{  display:table;
  margin-left: auto;
  margin-right: auto;}
paging {text-align: center;}
li {float:left;display: inline-block; height: 20px; margin: 0 5px; padding: 0 5px; border: 1px solid #666; background: #eee; line-height: 21px;}

</style>

<?php 
			$sql = "SELECT * FROM seoul_user where user='$nowuser' ORDER BY num";
			$result = mysqli_query($link, $sql) or die("SQL 에러");
$count=mysqli_num_rows($result);

if($count==0)
echo "검색기록이 없습니다.";
else if($count!=0){

	$sql = "SELECT * FROM seoul_user where user= ? ORDER BY num desc";


$stmt = mysqli_prepare($link, $sql);

if($stmt === false) {
    echo('aaStatement 생성 실패 : ' . mysqli_error($link));
    exit();
}

$bind = mysqli_stmt_bind_param($stmt,"s",$nowuser);
if($bind === false) {
    echo('aa파라미터 바인드 실패 : ' . mysqli_error($link));
    exit();
}

$exec = mysqli_stmt_execute($stmt);
if($exec === false) {
    echo('aa쿼리 실행 실패 : ' . mysqli_error($link));
    exit();
}

$bindr = mysqli_stmt_bind_result($stmt,$a['num'],$a['user'],$a['search'],$a['date']);
if(!$bindr) {
    echo('aastmt_bind_result fail: ' . mysqli_error($link));
    exit();
}
?>

<div>
	<table width="800" border="1" cellpadding="3" align="center" style="text-align:center" >
				<tr bgcolor=#DCEBFF>
	
			
					<td class="name" height="10">내 아이피</td>
					<td class="addr" height="10" width="400">검색내용</td>
					<td class="seat" height="10" width="120">검색날짜</td>
		
				</tr>
			<?php
				
							while(mysqli_stmt_fetch($stmt))
							{	
								$listnum=$a['num'];

				?>

				<tr>
				
					<td class="name"><?php echo $a['user']?></td>
					<td class="addr"><?php echo $a['search']?></td>
					<td class="phone"><?php echo $a['date']?></td>	
		

				<?php 
					} mysqli_stmt_close($stmt);?>
	</table>
	</div>
<br>


<div class="paging">

				<?php echo $paging;} ?>
			</div>



</center>
</body>

