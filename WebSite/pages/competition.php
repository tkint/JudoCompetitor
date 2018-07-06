<?php
$action = getPostVariable ( "action" );
if (in_array($action, array("new", "newok", "update", "updateok"))) {
	if ($action == "new") {
		displayCompetEditor ();
	} elseif ($action == "newok") {
		$ranks = getPostVariable ( "id_rank" );
		unset ( $_POST ["id_rank"] );
		createData ( "competition", $_POST );
		$competition = getLastData ( "competition", "id_competition" );
		if ($ranks != false) {
			foreach ( $ranks as $rank ) {
				createData ( "concern", array (
						"id_rank" => $rank,
						"id_competition" => $competition ["id_competition"] 
				) );
			}
		}
		echo "Compétition planifiée";
		echo "<meta http-equiv='refresh' content='1;URL=../admin/competitions'>";
	} elseif ($action == "update") {
		$id_competition = getPostVariable ( "id" );
		displayCompetEditor ( $id_competition );
	} elseif ($action == "updateok") {
		$id_competition = getPostVariable ( "id" );
		$ranks = getPostVariable ( "id_rank" );
		unset ( $_POST ["id_rank"] );
		updateData ( "competition", "id_competition", $id_competition, $_POST );
		$competition = getTableByData("competition", "id_competition", $id_competition);
		if (existData ( "concern", "id_competition", $id_competition ))
			deleteTableByData ( "concern", "id_competition", $id_competition );
		if ($ranks != false) {
			foreach ( $ranks as $rank ) {
				createData ( "concern", array (
						"id_rank" => $rank,
						"id_competition" => $competition ["id_competition"] 
				) );
			}
		}
		echo "Compétition modifiée";
		echo "<meta http-equiv='refresh' content='1;URL=../admin/competitions'>";
	}
} else {
	if ($action == "register") {
		// Récupération de la compétition
		$competition = getTableByData ( "competition", "id_competition", getPostVariable ( "id_competition" ) );
		// Récupération des grades concernés par la compétition
		$concern = array ();
		foreach ( getListTableByData ( "concern", "id_competition", $competition ["id_competition"] ) as $concerned ) {
			$concern [] = $concerned ["id_rank"];
		}
		// Récupération des participants par la compétition
		$participate = array ();
		foreach ( getListTableByData ( "participate", "id_competition", $competition ["id_competition"] ) as $participating ) {
			$participate [] = $participating ["id_judoka"];
		}
		// Listing des erreurs
		$e = array ();
		if (! isSessionStarted ()) {
			$e [] = "Vous devez être connecté pour vous inscrire";
		}
		if (! in_array ( getSessionVariable ( "g.id_rank" ), $concern )) {
			$e [] = "Cette compétition ne concerne pas votre ceinture de couleur";
		}
		if (in_array ( getSessionVariable ( "j.id_judoka" ), $participate )) {
			$e [] = "Vous êtes déjà inscrit";
		}
		if ($competition ["capacity"] == countTableByData ( "participate", "id_competition", $competition ["id_competition"] )) {
			$e [] = "Il n'y a plus de place disponible";
		}
		// Résolution des erreurs
		if (sizeof ( $e ) > 0) {
			echo "L'inscription a échoué, il y a " . sizeof ( $e ) . " erreurs: <br />";
			foreach ( $e as $erreur ) {
				echo $erreur . "<br />";
			}
		} else {
			createData ( "participate", array (
					"id_judoka" => getSessionVariable ( "j.id_judoka" ),
					"id_competition" => $competition ["id_competition"] 
			) );
			echo "Inscription réussie";
			echo "<meta http-equiv='refresh' content='1;URL=competition'>";
		}
	} elseif ($action == "registerok") {
	}
	
	$competitions = getListTable ( "competition", "dateStart DESC" );
	foreach ( $competitions as $key => $value ) {
		$region = getTableByData ( "region", "id_region", $value ["id_region"] );
		$places = countTableByData ( "participate", "id_competition", $value ["id_competition"] );
		$competitions [$key] ["region"] = $region ["name"];
		$competitions [$key] ["resas"] = $places;
	}
	foreach ( $competitions as $key => $value ) {
		$competitions [$key] = array_map ( "utf8_encode", $value );
	}
	?>
<script>
	var competitions = <?php echo json_encode($competitions); ?>;
	</script>

<div align="center">
	<table id="reservation">
		<tr>
			<td align="center"><img src="./img/calendar/gauche.png"
				onmouseout="this.src='./img/calendar/gauche.png'"
				onmouseover="this.src='./img/calendar/gauche_hover.png'"
				onclick="if (mois == 0) { annee--; mois = 11; } else {mois--;}; draw_cal('calendrier', annee, mois, competitions);" />
			</td>
			<td align="center" width="252px">Calendrier <img
				src="./img/calendar/actualiser.png"
				onmouseout="this.src='./img/calendar/actualiser.png'"
				onmouseover="this.src='./img/calendar/actualiser_hover.png'"
				onclick="d=new Date(); select=d; annee=select.getFullYear(); mois=select.getMonth();draw_cal('calendrier', annee, mois, competitions); draw_form(); draw_detail();" /></td>
			<td align="center"><img src="./img/calendar/droite.png"
				onmouseout="this.src='./img/calendar/droite.png'"
				onmouseover="this.src='./img/calendar/droite_hover.png'"
				onclick="if (mois == 11) { annee++; mois = 0; } else {mois++;}; draw_cal('calendrier', annee, mois, competitions);" />
			</td>
			<td>Compétition</td>
		</tr>
		<tr>
			<td colspan="3" rowspan="2" align="center">
				<div id="calendrier"></div>
			</td>
			<td width="50%" height="100%">
				<div id="detail"></div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="formulaire"></div>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">draw_cal('calendrier', annee, mois, competitions); draw_form(); draw_detail();</script>
<?php } ?>