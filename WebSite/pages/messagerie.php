<div align="center">
	<table>
		<tr>
			<td>
				<ul id="navbar">
					<li><a href='../../messagerie/inbox'>Messages reçus</a></li>
					<li><a href='../../messagerie/outbox'>Message envoyés</a></li>
				</ul>
			</td>
		</tr>
	</table>
</div>
<?php
$arg = readNextArgument ();
$inbox = getListTableByData ( "message", "id_target", getSessionVariable ( "u.id_user" ), "id_message DESC" );
$outbox = getListTableByData ( "message", "id_author", getSessionVariable ( "u.id_user" ), "id_message DESC" );
?>
<form method='post' action='../../messagerie/outbox'
	style='margin: 0px; padding: 0px; display: inline;'>
	<input type='submit' value='Nouveau' /> <input type='hidden'
		name='action' value='new' />
</form>
<table id='messagerie'>
	<tr>
		<td id='listmessages'>
		<?php
		if ($arg == "outbox") {
			echo "<div align='center'>Messages envoyés<hr/></div>";
			
			if ($inbox !== false) {
				echo "<ul>";
				foreach ( $outbox as $message ) {
					echo "<li><a href='../../messagerie/outbox/" . $message ["id_message"] . "'>" . $message ["title"] . "</a></li>";
				}
				echo "</ul>";
			}
		} else {
			echo "<div align='center'>Messages reçus<hr/></div>";
			if ($outbox !== false) {
				echo "<ul>";
				foreach ( $inbox as $message ) {
					echo "<li><a href='../../messagerie/inbox/" . $message ["id_message"] . "'>" . $message ["title"] . "</a></li>";
				}
				echo "</ul>";
			}
		}
		?>
		</td>
		<td id='message'>
		<?php
		if (getPostVariable ( "action" ) == "new") {
			displayMessageEditor ();
		} elseif (getPostVariable ( "action" ) == "newok") {
			$post = cleanArray ( $_POST );
			$e = array ();
			if (empty ( $post ["id_target"] )) {
				$e [] = "Le message doit avoir un destinataire";
			}
			if (empty ( $post ["title"] )) {
				$e [] = "Le message doit avoir un titre";
			}
			if (empty ( $post ["message"] )) {
				$e [] = "Le contenu du message ne doit pas être vide";
			}
			if (sizeof ( $e ) > 0) {
				echo "<ul>";
				$i = 0;
				foreach ( $e as $erreur ) {
					echo "<li>" . $erreur . "</li>";
					$i ++;
				}
				echo "</ul>";
			} else {
				createData ( "message", cleanArray ( $post ) );
				$m = getLastData ( "message", "id_message" );
				echo "<meta http-equiv='refresh' content='0;URL=../../messagerie/outbox/" . $m ["id_message"] . "'>";
			}
		} else {
			$id_article = readNextArgument ();
			displayMessage ( $id_article );
		}
		?>
		</td>
	</tr>
</table>