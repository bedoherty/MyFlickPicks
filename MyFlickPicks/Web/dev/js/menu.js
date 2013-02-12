
$('#addItemButton').click(function()
{
	if ($('#valueToAdd').val() !== "")
	{
		var movieListArray = new Array();
		if (localStorage.getItem("movieList") === null)
		{
			movieListArray = new Array();
			movieListArray.push($('#valueToAdd').val());
			localStorage['movieList'] = JSON.stringify(movieListArray);
		}
		else
		{
			movieListArray = JSON.parse(localStorage['movieList']);
			//console.log(localStorage['movieList']);
			movieListArray.push($('#valueToAdd').val());
			localStorage['movieList'] = JSON.stringify(movieListArray);
		}
		$('#myMovieList').append('<li>' + $('#valueToAdd').val() + "<button class='removeButton' type='button'>Remove</button>" + '</li>');
	}
});

$('#clearListButton').click(function()
{
	localStorage.removeItem('movieList');
	$('#myMovieList').empty();
});

$(document).ready(function() {
	movieListArray = JSON.parse(localStorage['movieList']);
	for (var i = 0; i < movieListArray.length; i++) {
		$('#myMovieList').append('<li>' + movieListArray[i] + "<button class='removeButton' type='button'>Remove</button>" +  '</li>');
	}
	$(".removeButton").click(function() {
	console.log("Remove button clicked.");
});
 
});


