<?php
$id_category = getPostVariable ( "id" );
$action = getPostVariable ( "action" );
if ($action != false) {
	echo "<div align='center'><div id='avert'>";
	if ($id_category != false && existData ( "category", "id_category", $id_category )) {
		$category = getTableByData ( "category", "id_category", $id_category );
		if ($action == "delete") {
			echo "Vous allez supprimer la catégorie " . utf8_encode ( $category ["name"] ) . " ainsi que toutes les données associées.<br />Confirmer?<br />";
			echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
			echo "<input type='submit' value='Oui' />";
			echo "<input type='hidden' name='action' value='deleteok' />";
			echo "<input type='hidden' name='id' value='" . $id_category . "' />";
			echo "</form>";
			echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
			echo "<input type='submit' value='Non' />";
			;
			echo "</form>";
		} elseif ($action == "deleteok") {
			deleteTableByData ( "belongTo", "id_category", $id_category );
			deleteTableByData ( "category", "id_category", $id_category );
			echo "La catégorie " . utf8_encode ( $category ["name"] ) . " ainsi que les toutes les données associées ont bien été supprimés.";
		} elseif ($action == "update") {
			echo "<form method='post'>";
			echo "<input type='text' name='name' value='" . utf8_encode ( $category ["name"] ) . "'/><br />";
			echo "<input type='checkbox' name='clean' value='clean'/>Vider la catégorie";
			echo "<input type='hidden' name='action' value='updateok' />";
			echo "<input type='hidden' name='id' value='" . $id_category . "' />";
			echo "<input type='submit'/>";
			echo "</form>";
		} elseif ($action == "updateok") {
			if (getPostVariable ( "clean" ) == "clean")
				deleteTableByData ( "belongTo", "id_category", $id_category );
			updateData ( "category", "id_category", $id_category, $_POST );
			echo "La catégorie a bien été modifiée";
		}
	} elseif ($action == "new") {
		echo "<form method='post'>";
		echo "<input type='text' name='name'/><br />";
		echo "<input type='hidden' name='action' value='newok' />";
		echo "<input type='submit'/>";
		echo "</form>";
	} elseif ($action == "newok") {
		createData ( "category", $_POST );
		echo "La catégorie a bien été créée";
	}
	echo "</div></div>";
}
?>
<form method='post' style='margin: 0px; padding: 0px; display: inline;'>
	<input type='submit' value='Nouvelle' /> <input type='hidden'
		name='action' value='new' />
</form>
<table id="listeCategories">
	<tr>
		<td>Catégorie</td>
		<td>Articles</td>
		<td>Action</td>
	</tr>
<?php
foreach ( getListTable ( "category" ) as $category ) {
	echo "<tr>";
	echo "<td>" . utf8_encode ( $category ["name"] ) . "</td>";
	echo "<td>" . countTableByData ( "belongTo", "id_category", $category ["id_category"] ) . "</td>";
	echo "<td>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Modifier'>";
	echo "<input type='hidden' name='action' value='update' />";
	echo "<input type='hidden' name='id' value='" . $category ["id_category"] . "' />";
	echo "</form>";
	echo "<form method='post' style='margin: 0px; padding: 0px;display:inline;'>";
	echo "<input type='submit' class='sublink' value='Supprimer'>";
	echo "<input type='hidden' name='action' value='delete' />";
	echo "<input type='hidden' name='id' value='" . $category ["id_category"] . "' />";
	echo "</form>";
	echo "</td>";
	
	echo "</tr>";
}
?>
</table>