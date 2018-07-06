<?php
$action = getPostVariable ( "action" );
$id_competition = getPostVariable ( "id_competition" );
if (! $id_competition) {
	$c = getLastData ( "competition", "id_competition" );
	$id_competition = $c ["id_competition"];
}
if (isset ( $action ) && $action == "mark") {
	$id_judoka = getPostVariable ( "id_judoka" );
	$id_opponent = getPostVariable ( "id_opponent" );
	$id_phase = getPostVariable ( "id_phase" );
	$mark = array ();
	$mark ["id_judoka"] = $id_judoka;
	$mark ["id_move"] = getPostVariable ( "id_move" );
	$mark ["id_fight"] = alreadyFight ( $id_judoka, $id_opponent, $id_phase, $id_competition );
	if ($mark ["id_fight"] == false) {
		createData ( "fight", array (
				"id_competition" => $id_competition,
				"id_phase" => $id_phase 
		) );
		$fight = getLastData ( "fight", "id_fight" );
		createData ( "mark", array (
				"id_judoka" => $id_judoka,
				"id_move" => 1,
				"id_fight" => $fight ["id_fight"],
				"number" => 0 
		) );
		createData ( "mark", array (
				"id_judoka" => $id_opponent,
				"id_move" => 1,
				"id_fight" => $fight ["id_fight"],
				"number" => 0 
		) );
		$mark ["number"] = 1;
		$mark ["id_fight"] = $fight ["id_fight"];
	} else {
		$mark ["number"] = getMaxTableByData ( "mark", "number", "id_fight", $mark ["id_fight"] ) + 1;
	}
	createData ( "mark", $mark );
}
echo "Résultats des compétitions";
echo "<form method='post'>";
echo "<select name='id_competition' style='width: 40%;'>";
foreach ( getListTable ( "competition" ) as $compet ) {
	echo "<option ";
	if ($compet ["id_competition"] == $id_competition)
		echo "selected";
	echo " value='" . $compet ["id_competition"] . "'>" . $compet ["name"] . "</option>";
}
echo "</select>";
echo "<input type='submit' value='Sélectionner compétition'>";
echo "</form>";
?>
<table id="listeresultats">
	<tr>
		<td></td>
		<td>Judoka</td>
		<td>Ceinture</td>
		<td>Combats</td>
		<td>Victoires</td>
		<td>Défaites</td>
		<td>Points</td>
	</tr>
<?php
$i = 1;
$ladder = array_sort ( generateLadder ( $id_competition ), "points", SORT_DESC );
foreach ( $ladder as $element ) {
$user = getTableByData("user", "id_contact", $element["id_contact"]);
	echo "<tr>";
	echo "<td>" . $i . "</td>";
	echo "<td><a href='../../membre/" . $user ["id_user"] . "'/>" . $element ["judoka"] . "</a></td>";
	echo "<td>" . $element ["rank"] . "</td>";
	echo "<td>" . $element ["fight"] . "</td>";
	echo "<td>" . $element ["wins"] . "</td>";
	echo "<td>" . $element ["looses"] . "</td>";
	echo "<td>" . $element ["points"] . "</td>";
	echo "</tr>";
	$i ++;
}
?>
</table>
<form method='post'>
	<table>
		<tr>
			<td>Nouveau résultat</td>
		</tr>
		<tr>
			<td>Judoka</td>
			<td><select name='id_judoka'>
<?php
$judokas = array ();
foreach ( getListTableByData ( "participate", "id_competition", $id_competition ) as $participate ) {
	$judokas [] = getTableByData ( "judoka", "id_judoka", $participate ["id_judoka"] );
}

foreach ( $judokas as $judoka ) {
	$contact = getTableByData ( "contact", "id_contact", $judoka ["id_contact"] );
	echo "<option value='" . $judoka ["id_judoka"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</option>";
}
?>
</select></td>
		</tr>
		<tr>
			<td>Adversaire</td>
			<td><select name='id_opponent'>";
<?php
$judokas = array ();
foreach ( getListTableByData ( "participate", "id_competition", $id_competition ) as $participate ) {
	$judokas [] = getTableByData ( "judoka", "id_judoka", $participate ["id_judoka"] );
}

foreach ( $judokas as $judoka ) {
	$contact = getTableByData ( "contact", "id_contact", $judoka ["id_contact"] );
	echo "<option value='" . $judoka ["id_judoka"] . "'>" . $contact ["firstName"] . " " . $contact ["lastName"] . "</option>";
}
?>
</select></td>
		</tr>
		<tr>
			<td>Mouvement</td>
			<td><select name='id_move'>";
<?php
foreach ( getListTable ( "move WHERE points > 0" ) as $move ) {
	echo "<option value='" . $move ["id_move"] . "'>" . $move ["name"] . " - " . $move ["points"] . " points</option>";
}
?>
</select></td>
		</tr>
		<tr>
			<td>Phase</td>
			<td><select name='id_phase'>";
<?php
foreach ( getListTable ( "phase" ) as $phase ) {
	echo "<option value='" . $phase ["id_phase"] . "'>" . $phase ["name"] . "</option>";
}
?>
</select></td>
		</tr>
		<tr>
			<td><input type='hidden' name='action' value='mark' /><input
				type='hidden' name='id_competition'
				value='<?php echo $id_competition; ?>' /><input type='submit' /></td>
		</tr>
	</table>
</form>