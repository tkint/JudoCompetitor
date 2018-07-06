<?php
// Variables related to PATH
const PATH_CSS = "css";
const PATH_JS = "js";
const PATH_PAGE = "pages";
const PATH_ADMIN = "admin";
const PATH_HTML = "html";
// Variables related to pages
const PAGE_ERROR="404";
const PAGE_DEFAULT="accueil";
// Variables related to users
const VAR_LVL_VISITEUR = 0;
const VAR_LVL_MEMBRE = 1;
const VAR_LVL_ADMIN = 10;
// Menu
$menu_nav = array(
		"Connexion" => "connect",
		"Inscription" => "register",
		"Déconnexion" => "disconnect"
);
$menu_def = array (
		"Accueil" => "accueil",
		"Archives" => "archives",
		"Compétitions" => "competition"
);

$menu_on = array(
		"Résultats" => "resultats",
		"Messagerie" => "messagerie",
		"Membres" => "membre",
		"Administration" => "admin/accueil"
);

$menu_off = array(
		
);
// Niveau
$level = array(
		"accueil" => VAR_LVL_VISITEUR,
		"archives" => VAR_LVL_VISITEUR,
		"competition" => VAR_LVL_VISITEUR,
		"resultats" => VAR_LVL_MEMBRE,
		"membre" => VAR_LVL_MEMBRE,
		"messagerie" => VAR_LVL_MEMBRE,
		"admin/accueil" => VAR_LVL_ADMIN
);
$arguments=array();
$menu=false;
const VAR_MENU = "url";
// DATABASE
const DB_USER="root";
const DB_PASSWORD="EpreuveE4";
const DB_NAME="competitor";
const DB_IP="sql";

//$bdd = new pdoObject();