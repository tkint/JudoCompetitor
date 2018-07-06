<?php
$id_competition = getPostVariable ( "id" );
$action = getPostVariable ( "action" );
if ($action != false && $id_competition != false && existData ( "competition", "id_competition", $id_competition )) {
	echo "<div align='center'><div id='avert'>";
	$competition = getTableByData ( "competition", "id_competition", $id_competition );
	if ($action == "delete") {
		echo "Vous allez supprimer la compétition " . $competition ["name"] . ".<br />Confirmer?<br />";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Oui' />";
		echo "<input type='hidden' name='action' value='deleteok' />";
		echo "<input type='hidden' name='id' value='" . $id_competition . "' />";
		echo "</form>";
		echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
		echo "<input type='submit' value='Non' />";
		;
		echo "</form>";
	} elseif ($action == "deleteok") {
		deleteTableByData ( "competition", "id_competition", $id_competition );
		echo "L'competition " . $competition ["name"] . " a bien été supprimé.";
	}
	echo "</div></div>";
}
?>
<form method='post' action='../competition'
	style='margin: 0px; padding: 0px; display: inline;'>
	<input type='submit' value='Nouveau' /> <input type='hidden'
		name='action' value='new' />
</form>
<table id="listecompetitions">
	<tr>
		<td>Compétition</td>
		<td>Début</td>
		<td>Fin</td>
		<td width='15%'>Action</td>
	</tr>
<?php
foreach ( getListTable ( "competition" ) as $competition ) {
	echo "<tr>";
	echo "<td><a href='../../resultats/".$competition["id_competition"]."'>" . $competition ["name"] . "</a></td>";
	echo "<td>" . cleanDate ( $competition ["dateStart"] ) . "</td>";
	echo "<td>" . cleanDate ( $competition ["dateEnd"] ) . "</td>";
	echo "<td>";
	echo "<form method='post' action='../competition' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Modifier'>";
	echo "<input type='hidden' name='action' value='update' />";
	echo "<input type='hidden' name='id' value='" . $competition ["id_competition"] . "' />";
	echo "</form>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Supprimer'>";
	echo "<input type='hidden' name='action' value='delete' />";
	echo "<input type='hidden' name='id' value='" . $competition ["id_competition"] . "' />";
	echo "</form>";
	echo "</td>";
	echo "</tr>";
}
?>
</table>