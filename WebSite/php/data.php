<?php
/**
 * Fonction qui ouvre la connexion à la base de données
 * @return PDO
 */
function bdd() {
	try {
		$url = "mysql:host=" . DB_IP . ";dbname=" . DB_NAME;
		return new PDO ( $url, DB_USER, DB_PASSWORD, array (
				PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
		) );
	} catch ( Exception $e ) {
		die ( 'Erreur : ' . $e->getMessage () );
	}
}
function getListTable($table, $order = null) {
	$db = bdd ();
	if ($order != null) {
		$o = " ORDER BY " . $order;
	} else {
		$o = "";
	}
	$req = $db->query ( "SELECT * FROM " . $table . $o );
	$res = $req->fetchAll ();
	$req->closeCursor ();
	return $res;
}
function getListTableTop($table, $limit, $order = null) {
	$db = bdd ();
	if ($order != null) {
		$o = " ORDER BY " . $order;
	} else {
		$o = "";
	}
	$req = $db->query ( "SELECT * FROM " . $table . $o . " LIMIT " . $limit );
	$res = $req->fetchAll ();
	$req->closeCursor ();
	return $res;
}
function getListTableByData($table, $key, $value, $order = null) {
	$db = bdd ();
	if ($order != null) {
		$o = " ORDER BY " . $order;
	} else {
		$o = "";
	}
	$req = $db->query ( "SELECT * FROM " . $table . "
				WHERE " . $key . " = '" . $value . "'" . $o );
	$res = $req->fetchAll ();
	$req->closeCursor ();
	return $res;
}
function getTableColumns($table) {
	$db = bdd ();
	$req = $db->query ( "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS 
			WHERE TABLE_NAME =  '" . $table . "'" );
	$res = $req->fetchAll ();
	$req->closeCursor ();
	return $res;
}
function getTableByData($table, $key, $value) {
	$db = bdd ();
	$req = $db->query ( "SELECT * FROM " . $table . "
				WHERE " . $key . " = '" . $value . "'" );
	$res = $req->fetch ();
	$req->closeCursor ();
	return $res;
}
function getLastData($table, $order) {
	$db = bdd ();
	$req = $db->query ( "SELECT * FROM " . $table . " ORDER BY " . $order . " DESC LIMIT 1" );
	$res = $req->fetch ();
	$req->closeCursor ();
	return $res;
}
function getMaxTableByData($table, $max, $key, $value) {
	$db = bdd ();
	$req = $db->query ( "SELECT MAX(" . $max . ") AS m FROM " . $table . " WHERE " . $key . " = " . $value );
	$res = $req->fetch ();
	$req->closeCursor ();
	return $res["m"];
}
function createData($table, $listValue) {
	$db = bdd ();
	$listValue = cleanArray ( $listValue );
	$statement = " (";
	foreach ( $listValue as $k => $v ) {
		if (existColumn ( $table, $k )) {
			$statement .= $k . ", ";
		}
	}
	$statement = substr ( $statement, 0, - 2 );
	$statement .= ") VALUES (";
	foreach ( $listValue as $k => $v ) {
		if (existColumn ( $table, $k )) {
			$statement .= "'" . $v . "', ";
		}
	}
	$statement = substr ( $statement, 0, - 2 );
	$statement .= ")";
	$req = $db->prepare ( "INSERT INTO " . $table . $statement );
	$req->execute ( $listValue );
	$req->closeCursor ();
}
function updateData($table, $key, $value, $listValue) {
	$db = bdd ();
	$listValue = cleanArray ( $listValue );
	$statement = " SET ";
	foreach ( $listValue as $k => $v ) {
		if (existColumn ( $table, $k )) {
			$statement .= $k . " = '" . $v . "', ";
		}
	}
	$statement = substr ( $statement, 0, - 2 );
	$req = $db->prepare ( "UPDATE " . $table . $statement . " 
			WHERE " . $key . " = '" . $value . "'" );
	$req->execute ();
	$req->closeCursor ();
}
function deleteTableByData($table, $key, $value) {
	$db = bdd ();
	$req = $db->prepare ( "DELETE FROM " . $table . "
				WHERE " . $key . " = '" . $value . "'" );
	$req->execute ();
	$req->closeCursor ();
}
function countTable($table) {
	$db = bdd ();
	$req = $db->query ( "SELECT COUNT(*) AS NB FROM " . $table );
	$res = $req->fetch ();
	$req->closeCursor ();
	return $res ["NB"];
}
function countTableByData($table, $key, $value) {
	$db = bdd ();
	$req = $db->query ( "SELECT COUNT(*) AS NB FROM " . $table . " 
				WHERE " . $key . " = '" . $value . "'" );
	$res = $req->fetch ();
	$req->closeCursor ();
	return $res ["NB"];
}