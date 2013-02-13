
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
		//$('#myMovieList').append('<li>' + $('#valueToAdd').val() + "<button class='removeButton' type='button'>Remove</button>" + '</li>');
		addRow($('#valueToAdd').val(), movieListArray.length - 1);
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
		//$('#myMovieList').append('<li>' + movieListArray[i] + "<button class='removeButton' type='button'>Remove</button>" +  '</li>');
		addRow(movieListArray[i], i);
	}
	//$(".removeButton").click(function() {
	//console.log("Remove button clicked.");
//});
 
});


function addRow(movieTitle, index)
{
	$('#myMovieList').append('<li>' + movieTitle + "<button class='removeButton' id='removeButton" + index + "' type='button'>Remove</button>" +  '</li>');
	$("#removeButton" + index).click(function() {
		console.log("Remove button " + index + " clicked.");
		$("#myMovieList li").eq(index).remove();
	});
}


