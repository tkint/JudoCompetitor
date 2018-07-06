<?php
$id_get = readNextArgument ();
$id_competition = getPostVariable ( "id_competition" );
if (! $id_competition) {
	$c = getLastData ( "competition", "id_competition" );
	$id_competition = $c ["id_competition"];
}
if ($id_get != false)
	$id_competition = $id_get;

echo "Résultats des compétitions";
echo "<form method='post' action='../resultats'>";
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
	$user = getTableByData ( "user", "id_contact", $element ["id_contact"] );
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