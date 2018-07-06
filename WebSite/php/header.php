<?php
function includeCssFile() {
	foreach ( glob ( PATH_CSS . "/*.css" ) as $cssFile ) {
		$content = file_get_contents ( $cssFile );
		echo "<style>";
		echo $content;
		echo "</style>";
	}
}
function includeJsFile() {
	foreach ( glob ( PATH_JS . "/*.js" ) as $jsFile ) {
		$content = file_get_contents ( $jsFile );
		echo "<script type='text/javascript'>";
		echo $content;
		echo "</script>";
	}
}
function head() {
	includeJsFile();
	includeCssFile ();
}