<?php
getContent("../admin/navbar");
$action = getPostVariable ( "action" );
if ($action == "new") {
	echo "<div align='center'>Nouveau club</div>";
	displayClubEditor();
} elseif ($action == "newok") {
	createData("club", $_POST);
	$club = getLastData("club", "id_club");
	echo "Club créé";
	echo "<meta http-equiv='refresh' content='1;URL=club/".$club["id_club"]."'>";
} elseif ($action == "update") {
	$id_club = getPostVariable ( "id" );
	displayClubEditor($id_club);
} elseif ($action == "updateok") {
	$id_club = getPostVariable ( "id" );
	updateData("club", "id_club", $id_club, $_POST);
	echo "Club modifié";
	echo "<meta http-equiv='refresh' content='1;URL=club/".$id_club."'>";
} else {
	$id_club = readNextArgument();
	if (existData ( "club", "id_club", $id_club )) {
		displayClub($id_club);
	}else {
		getContent("404");
	}
}
?>