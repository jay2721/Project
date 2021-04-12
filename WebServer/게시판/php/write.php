<head>
<?php
echo "<font color=#7B68EE><center>";
	
	echo "<h2>글 작성</h2>";
echo "</center></font>";

			$myid = "jeonayg1067";
			$mypw = "dkdud2721*";

	$link = new mysqli("localhost",$myid,$mypw,"jeonayg1067");

			if ($link->connect_error) {
			    printf("Connect failed: %s\n", $link->connect_error);
			    exit();
			}
			
	

/*$username = $_POST['myid'];
$userpw = $_POST['mypw'];
$ptitle = $_POST['title'];
$pcontent = $_POST['content'];*/
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
</script>
<body>

	<div>

<form method="post" action="writecheck.php" enctype="multipart/form-data">
<table width="850" height="500" border="1" cellpadding="3" align="center" >
            <tr>
                <td bgcolor=#E6E6FA>제목 : </td>
                <td><input type="text" name="title" maxlength="50" placeholder="제목을 입력하세요." /></td></tr>
 
            <tr><td bgcolor=#E6E6FA>내용 : </td>
            <td><textarea rows="10" cols="100" name="content" placeholder="내용을 입력하세요."></textarea></td></tr>
	
                <td bgcolor=#E6E6FA>ID :</td>
                <td><input type="id" name="myid" maxlength="10" placeholder="아이디를 입력하세요."/></td></tr>
         <tr>
                <td bgcolor=#E6E6FA>PW : </td>
                <td><input type="password" name="mypw" maxlength="10" placeholder="비밀번호를 입력하세요."/></td></tr>

            <tr bgcolor=#E6E6FA><td colspan="2" align="center">
	
                <input type="button" value="작성" onclick='output(this.form)'>
                <input type="reset" value="취소"  onclick="location.href='list.php'"></td> </tr>
</table>
		
	</form>
	</div>


</body>
