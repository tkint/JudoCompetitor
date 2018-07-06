<?php
echo "<table style='width: 100%;'>";
echo "<tr><td colspan='3' align='center'>Fil d'actualité<hr></td></tr>";
foreach ( getListTableTop ( "article", 5, "id_article DESC" ) as $article ) {
	$level = getTableByData("level", "id_level", $article ["id_level"]);
	if ((! isSessionStarted () && ($level ["level"] == 0)) || (isSessionStarted () && getSessionVariable ( "l.level" ) >= $level ["level"])) {
		$user = getTableByData ( "user", "id_user", $article ["id_user"] );
		$auteur = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
		echo "<tr>";
		echo "<td><a href='../../article/" . $article ["id_article"] . "' title='".$article["title"]."'>" . substr($article ["title"], 0, 22) . "...</a></td>";
		echo "</tr>";
	}
}
echo "</table>";