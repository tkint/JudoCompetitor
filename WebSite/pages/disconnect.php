<?php
if (isSessionStarted()) {
	session_destroy();
	echo "Vous vous êtes correctement déconnecté";
	echo "<meta http-equiv='refresh' content='0;URL=accueil'>";
}