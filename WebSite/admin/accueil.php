<div align="center">Bienvenue <?php echo $_SESSION["c.firstName"]." ".$_SESSION["c.lastName"]; ?></div>
<table id="stats">
	<tr>
		<td colspan="8">Statistiques</td>
	</tr>
	<tr>
		<td>Articles</td>
		<td><?php echo countTable("article"); ?></td>
		<td>Membres</td>
		<td><?php echo countTable("user"); ?></td>
		<td>Judokas</td>
		<td><?php echo countTable("judoka"); ?></td>
		<td>Clubs</td>
		<td><?php echo countTable("club"); ?></td>
	</tr>
</table>