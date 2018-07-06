<?php
$id_user = readNextArgument ();
if ($id_user != false) {
	$update = getPostVariable ( "update" );
	if ($update == "ok") {
		$user = getTableByData ( "user", "id_user", $id_user );
		
		updateData ( "user", "id_user", $id_user, $_POST );
		updateData ( "contact", "id_contact", $user ["id_contact"], $_POST );
		if (existData ( "judoka", "id_contact", $user ["id_contact"] )) {
			updateData ( "judoka", "id_contact", $user ["id_contact"], $_POST );
		} elseif ($_POST ["id_club"] != "aucun") {
			createData ( "judoka", $_POST );
		}
		
		if ($user ["id_user"] == getSessionVariable ( "u.id_user" )) {
			sessionUpdate ( $user );
		}
		echo "<meta http-equiv='refresh' content='0;URL=../membre/" . $id_user . "'>";
	}
	
	$user = getTableByData ( "user", "id_user", $id_user );
	$contact = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
	$judoka = getTableByData ( "judoka", "id_contact", $user ["id_contact"] );
	$level = getTableByData ( "level", "id_level", $user ["id_level"] );
	$rank = getTableByData ( "rank", "id_rank", $judoka ["id_rank"] );
	$club = getTableByData ( "club", "id_club", $judoka ["id_club"] );
	$region = getTableByData ( "region", "id_region", $club ["id_region"] );
	if ($_SESSION ["u.id_user"] != $id_user && $_SESSION ["l.level"] != VAR_LVL_ADMIN) {
		$disabled = "disabled";
	} else {
		$disabled = "";
	}
	?>
<form method='post' action='../membre/<?php echo $id_user; ?>'>
	<table id='membre'>
		<tr>
			<td style='width: 30%;'>
				<table class='membre_part'>
					<tr>
						<td colspan='2' align='center'>Contact</td>
					</tr>
					<tr>
						<td>Nom</td>
						<td><input type='text' name='lastName'
							value='<?php echo $contact ["lastName"]."' ".$disabled; ?>'></input></td>
					
					</tr>
					<tr>
						<td>Prénom</td>
						<td><input type='text' name='firstName'
							value='<?php echo $contact ["firstName"]."' ".$disabled; ?>'></input></td>
					
					</tr>
					<tr>
						<td>E-Mail</td>
						<td><input type='text' name='eMail'
							value='<?php echo $contact ["eMail"]."' ".$disabled; ?>'></input></td>
					
					</tr>
				<?php if ($_SESSION ["u.id_user"] == $id_user || $_SESSION["l.level"] == VAR_LVL_ADMIN) { ?>
				<tr>
						<td>Adresse</td>
						<td><input type='text' name='address'
							value='<?php echo $contact ["address"]."' ".$disabled; ?>'></input></td>
					
					</tr>
					<tr>
						<td>Code Postal</td>
						<td><input type='text' name='postalCode'
							value='<?php echo $contact ["postalCode"]."' ".$disabled; ?>'></input></td>
					
					</tr>
				<?php } ?>
			</table>
			</td>
			<td style='width: 30%;'>
				<table class='membre_part'>
					<tr>
						<td colspan='2' align='center'>Judoka</td>
					</tr>
				<?php if ($_SESSION ["u.id_user"] == $id_user || $_SESSION["l.level"] == VAR_LVL_ADMIN) { ?>
				<tr>
						<td>Licence</td>
						<td><input type='text' name='licence'
							value='<?php echo $judoka ["licence"]."' ".$disabled; ?>'></input></td>
					
					</tr>
				<?php } ?>
				<tr>
						<td>Grade</td>
						<td><select name='id_rank' <?php echo $disabled; ?>>
					<?php
	
	foreach ( getListTable ( "rank", "level" ) as $g ) {
		echo "<option value='" . $g ["id_rank"] . "'";
		if ($g ["id_rank"] == $rank ["id_rank"]) {
			echo " selected";
		}
		echo ">" . $g ["name"] . "</option>";
	}
	?>
					</select></td>
					</tr>
				</table>
				<?php
	if (getSessionVariable ( "l.level" ) >= VAR_LVL_ADMIN) {
		?>
				<table class='membre_part'>
					<tr>
						<td colspan='2' align='center'>Membre</td>
					</tr>
					<tr>
						<td><select name='id_level'>
						<?php
		foreach ( getListTable ( "level", "level DESC" ) as $lvl ) {
			echo "<option value='" . $lvl ["id_level"] . "'";
			if ($lvl ["id_level"] == $user ["id_level"]) {
				echo " selected ";
			}
			echo ">" . $lvl ["level"] . " - " . $lvl ["name"] . "</option>";
		}
		?>
						</select></td>
					</tr>
				</table>
				<?php } ?>
			</td>
			<td style='width: 30%;'>
				<table class='membre_part'>
					<tr>
						<td colspan='2' align='center'>Club</td>
					</tr>
					<tr>
						<td>Nom</td>
						<td><select name='id_club' <?php echo $disabled; ?>>
					<?php
	if (! $judoka) {
		echo "<option value='aucun' selected>Aucun</option>";
	}
	foreach ( getListTable ( "club", "name" ) as $c ) {
		echo "<option value='" . $c ["id_club"] . "'";
		if ($c ["id_club"] == $club ["id_club"]) {
			echo " selected";
		}
		echo ">" . $c ["name"] . "</option>";
	}
	?>
					</select></td>
					</tr>
					<tr>
						<td>Adresse</td>
						<td><input type='text' name=''
							value='<?php echo $club ["address"]; ?>' disabled /></td>

					</tr>
					<tr>
						<td>Code Postal</td>
						<td><input type='text' name=''
							value='<?php echo $club ["postalCode"]; ?>' disabled /></td>

					</tr>
					<tr>
						<td>Ville</td>
						<td><input type='text' name=''
							value='<?php echo $club ["city"]; ?>' disabled /></td>

					</tr>
					<tr>
						<td>Région</td>
						<td><input type='text' name=''
							value='<?php echo $region ["name"]; ?>' disabled /></td>

					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan='3' align='center'><?php if ($disabled =="") {?>
			<input type='hidden' name='id_contact'
				value='<?php echo $user["id_contact"]; ?>' /><input type='hidden'
				name='update' value='ok' /><input type='submit'
				value='Actualiser les données' /><?php } ?></td>
		</tr>
	</table>
</form>
<?php } else { ?>
<div align='center'>
	Liste des membres<br /> <br />
</div>
<table id='liste'>
	<tr>
		<td></td>
		<td>Prénom</td>
		<td>Nom</td>
		<td>Rang</td>
		<td>Club</td>
	</tr>
<?php
	foreach ( getListTable ( "user" ) as $user ) {
		$contact = getTableByData ( "contact", "id_contact", $user ["id_contact"] );
		$judoka = getTableByData ( "judoka", "id_contact", $user ["id_contact"] );
		$level = getTableByData ( "level", "id_level", $user ["id_level"] );
		$rank = getTableByData ( "rank", "id_rank", $judoka ["id_rank"] );
		$club = getTableByData ( "club", "id_club", $judoka ["id_club"] );
		if (! $rank) {
			$rank = getTableByData ( "rank", "level", 1 );
		}
		echo "<tr>";
		echo "<td><img src='../img/belt/belt_" . $rank ["level"] . ".png' /></td>";
		echo "<td><a href='../membre/" . $user ["id_user"] . "'>" . $contact ["firstName"] . "</a></td>";
		echo "<td>" . $contact ["lastName"] . "</td>";
		echo "<td>" . $level ["name"] . "</td>";
		echo "<td>" . $club ["name"] . "</td>";
		echo "</tr>";
	}
	?>
</table>

<?php
}
?>