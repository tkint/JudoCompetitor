var basetext = "Passez votre souris sur les éléments du tableau pour avoir plus de détails.";

function jour_fev(annee) {
	if (annee % 4 == 0) {
		return 29;
	} else {
		return 28;
	}
}

function date_format(date, bdd) {
	if (bdd == true) {
		var jma = date.split(" ")[0], dd = jma.split("-")[2], mois = jma.split("-")[1], year = jma.split("-")[0];
	} else {
		var dd = date.getDate(), mois = date.getMonth() + 1, year = date
				.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}
		if (mois < 10) {
			mois = '0' + mois;
		}
	}
	return dd + '/' + mois + '/' + year;
}

var date = new Date(), jour = date.getDate(), mois = date.getMonth(), annee = date
		.getFullYear(),

select = date;

semaine = [ 'Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi',
		'Dimanche' ];

function draw_detail(competition) {
	var t = "Pas de compétition ce jour-ci";
	if (competition != null) {
		t = '<table width="100%"><tr><td align="center" colspan="2">'
				+ competition.name + '</td></tr>'
				+ '<tr><td rowspan="3">Lieu :</td><td>' + competition.address
				+ '</td></tr>' + '<tr><td>' + competition.postalCode + ' '
				+ competition.city + '</td></tr>' + '<tr><td>'
				+ competition.region + '</td></tr>' + '<tr><td>Du :</td><td>'
				+ date_format(competition.dateStart, true) + '</td></tr>'
				+ '<tr><td>Au :</td><td>'
				+ date_format(competition.dateEnd, true) + '</td></tr>'
				+ '<tr><td>Places :</td><td>Il reste '
				+ (competition.capacity - competition.resas) + ' places sur '
				+ competition.capacity + '</td></tr>';
	}
	document.getElementById("detail").innerHTML = t;
}

function draw_form(competition) {
	var t = '<form method="post">' + '<table border="1" id="resa" width="100%">'
			+ '<tr>'
			+ '<td colspan="2" align="center" ><input type="button" value="'
			+ date_format(select) + '" onclick="annee=' + select.getFullYear()
			+ '; mois=' + select.getMonth()
			+ '; draw_cal(\'calendrier\', annee, mois, competitions);"/></td>' + '</tr>'
			+ '<tr>' + '<td colspan="2" align="center">'
			+ '<input type="hidden" name="action" value="register"/>'
			+ '<input type="hidden" name="id_competition" value="';
	if (competition != undefined)
		t += competition.id_competition;
	t += '"/>' + '<input type="submit" value="S\'inscrire" /></td>'
			+ '</table>' + '</form>';
	document.getElementById("formulaire").innerHTML = t;
}

function isPlanned(day, year, month, competitions) {
	for ( var x in competitions) {
		var date = new Date(competitions[x].dateStart);
		if (day == date.getDate() && year == date.getFullYear()
				&& month == date.getMonth()) {
			return x;
		}
	}
	return null;
}

function draw_cal(id, year, mois, competitions) {
	var monthDays = [ [ "Janvier", 31 ], [ "Février", jour_fev(year) ],
			[ "Mars", 31 ], [ "Avril", 30 ], [ "Mai", 31 ], [ "Juin", 30 ],
			[ "Juillet", 31 ], [ "Août", 31 ], [ "Septembre", 30 ],
			[ "Octobre", 31 ], [ "Novembre", 30 ], [ "Décembre", 31 ] ], table = '<table border="1" id="cal">', d = new Date(
			year, mois), f = (d.getDay() + 6) % 7,
	count = 0, a = 0, l = 0;
	table += '<tr><td colspan="7" align="center">' + monthDays[mois][0] + '   '
			+ year + '</td></tr>';

	for (var i = 0; i < 7; i++) {
		table += '<td>' + semaine[i].substring(0, 3) + '</td>';
	}
	table += '</tr><tr>';

	for (var i = 0; i < f; i++) {
		if (mois == 0) {
			table += '<td class="disable">' + (count - 31 + 2 * 31 - f + 1)
					+ '</td>';
		} else {
			table += '<td class="disable">'
					+ (count - monthDays[mois - 1][1] + 2
							* monthDays[mois - 1][1] - f + 1) + '</td>';
		}
		count++;
	}

	for (var i = 1; i <= monthDays[mois][1]; i++) {
		var compet = isPlanned(i, year, mois, competitions);
		count++;
		if (i == select.getDate() && year == select.getFullYear()
				&& mois == select.getMonth()) {
			table += '<td onclick="select.setDate('
					+ i
					+ '); select.setFullYear('
					+ year
					+ '); select.setMonth('
					+ mois
					+ '); draw_cal(\''
					+ id
					+ '\', select.getFullYear(), select.getMonth(), competitions); draw_form(competitions['
					+ compet + ']);  draw_detail(competitions[' + compet
					+ ']);" id="select">';
			if (compet != null) {
				table += '|';
			}
			table += i;
			if (compet != null) {
				table += '|';
			}

			table += '</td>';
		} else if (count % 7 == 0) {
			table += '<td class="dimanche">';
			if (compet != null) {
				table += '|';
			}
			table += i;
			if (compet != null) {
				table += '|';
			}
			table += '</td>';
		} else {
			table += '<td onclick="select.setDate('
					+ i
					+ '); select.setFullYear('
					+ year
					+ '); select.setMonth('
					+ mois
					+ '); draw_cal(\''
					+ id
					+ '\', select.getFullYear(), select.getMonth(), competitions); draw_form(competitions['
					+ compet + ']); draw_detail(competitions[' + compet
					+ ']);" class="jours">';
			if (compet != null) {
				table += '|';
			}
			table += i;
			if (compet != null) {
				table += '|';
			}

			table += '</td>';
		}
		if (count % 7 == 0) {
			table += '</tr><tr>';
			l++;
		}
	}

	if (monthDays[mois][1] + f <= 35) {
		for (i = monthDays[mois][1] + f; i < 35; i++) {
			a++;
			table += '<td class="disable">' + a + '</td>';
		}
	}

	if (monthDays[mois][1] + f >= 35) {
		for (i = monthDays[mois][1] + f; i < 42; i++) {
			a++;
			table += '<td class="disable">' + a + '</td>';
		}
	}

	if (l <= 4) {
		table += '<tr>';
		for (i = 0; i < 7; i++) {
			a++;
			table += '<td class="disable">' + a + '</td>';
		}
	}

	table += '</tr></table>';
	document.getElementById(id).innerHTML = table;
}