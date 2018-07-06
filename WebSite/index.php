<meta charset="utf-8" />
<?php
session_start();
ini_set ( 'display_errors', 1 );
function my_autoloader($class) {
	$classFile = 'class/' . $class . '.class.php';
	include $classFile;
}
spl_autoload_register ( "my_autoloader" );
foreach ( glob ( "php/*.php" ) as $page ) {
	include_once $page;
}
processUrlVariables ();
// if(mail("t.kint@hotmail.fr", "Judo Competitor", "Message de test")) {
// 	echo "SUCCESS";
// } else {
// 	echo "FAIL";
// }
$c = getTableByData("contact", "id_contact", getSessionVariable("c.id_contact"));
//var_dump(generateKey($c));
//var_dump($_POST);
?>
<!DOCTYPE html>
<html lang='fr'>
<head>
    <?php head(); ?>
    <title>Judo Competitor</title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body>
	<div id='userdata'><?php echo afficheUserData(); ?></div>
	<div id='actualite'><?php getContent("actualite"); ?></div>
	<table id='page' align='center'>
		<tr>
			<td id='banniere'>Competitor.fr</td>
		</tr>
		<tr>
			<td>
				<?php echo afficheMenuButton(); ?>
			</td>
		</tr>
		<tr>
			<td id='contenu'>
				<?php
				global $menu_nav;
				if (in_array ( $menu, $menu_nav ) || (!empty($arguments [1]) && in_array ( $menu . "/" . $arguments [1], $menu_nav) ) || empty ( $menu ) || (isset ( $_SESSION ["l.level"] ) && $_SESSION ["l.level"] >= VAR_LVL_ADMIN)) {
					if ($menu == "admin") {
						getContent ( "../admin/navbar" );
					}
					getContent ( $menu );
				} else {
					echo "<div align='center'>Vous n'avez pas le droit de consulter cette page.<br />";
					echo "<a href='../accueil'>Revenir à l'accueil</a></div>";
				}
				?>
			</td>
		</tr>
	</table>
</body>
</html>