<html>
<title>
	Google
</title>
<head>
<table bgcolor="black">
<tr><td>
	<Font Color="grey">
	   +Search   Image   Maps   Play   YouTube   News   Gmail   Drive   Calendar   More
</td></tr>
</table>
</head>

<form name="form1" method="POST">
<body>
<table align="center">

<?php
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";
echo "<br>";

?>

<tr><td align="center">
	<Img src="pic.jpg" width="350" Height="125" border="1">
</td></tr>
<tr><td>
<?php
echo "<br>";
?>
	<Input Type="Text" Name="Textbox1" size="65" Maxlength="12522:44 11-03-2013">
</td></tr>

</table>
<table align="center">
<tr><td>
	<Input type="submit" name="button1" value="Google Search">
	<Input Type="submit" Name="button2" value="I'M Feeling Lucky">
</td></tr>
</table>
</form>
</body>
</html>
<?php
if(isset($_POST['button1']))
{
	echo "<script> location.href='link_page.php' </script>";
}
if(isset($_POST['button2']))
{
	echo "<script> location.href='link_page.php' </script>";
}
?>
