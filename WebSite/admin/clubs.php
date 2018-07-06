<?php
$id_club = getPostVariable ( "id" );
$action = getPostVariable ( "action" );
if ($action != false && $id_club != false && existData ( "club", "id_club", $id_club )) {
	echo "<div align='center'><div id='avert'>";
	$club = getTableByData ( "club", "id_club", $id_club );
	if ($action == "delete") {
		echo "Vous allez supprimer le club " . $club ["name"] . ".<br />Confirmer?<br />";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Oui' />";
		echo "<input type='hidden' name='action' value='deleteok' />";
		echo "<input type='hidden' name='id' value='" . $id_club . "' />";
		echo "</form>";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Non' />";
		;
		echo "</form>";
	} elseif ($action == "deleteok") {
		deleteTableByData ( "club", "id_club", $id_club );
		echo "Le club " . $club ["name"] . " a bien été supprimé.";
	}
	echo "</div></div>";
}
?>
<form method='post' action='../club'
	style='margin: 0px; padding: 0px; display: inline;'>
	<input type='submit' value='Nouveau' /> <input type='hidden'
		name='action' value='new' />
</form>
<table id="listeClubs">
	<tr>
		<td>Nom</td>
		<td>Région</td>
		<td>Judokas</td>
		<td width='15%'>Action</td>
	</tr>
<?php
foreach ( getListTable ( "club" ) as $club ) {
	$region = getTableByData ( "region", "id_region", $club ["id_region"] );
	$judokas = countTableByData ( "judoka", "id_club", $club ["id_club"] );
	echo "<tr>";
	echo "<td><a href='../club/" . $club ["id_club"] . "'>" . $club ["name"] . "</a></td>";
	echo "<td>" . $region ["name"] . "</td>";
	echo "<td align='center'>" . $judokas . "</td>";
	echo "<td>";
	echo "<form method='post' action='../club' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Modifier'>";
	echo "<input type='hidden' name='action' value='update' />";
	echo "<input type='hidden' name='id' value='" . $club ["id_club"] . "' />";
	echo "</form>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Supprimer'>";
	echo "<input type='hidden' name='action' value='delete' />";
	echo "<input type='hidden' name='id' value='" . $club ["id_club"] . "' />";
	echo "</form>";
	echo "</td>";
	echo "</tr>";
}
?>
</table>