/*
*	Button Click Event for the Add Button
*/
$('#addItemButton').click(function()
{
	addValue();
});

/*
*	Button Click Event for the Clear Button
*/
$('#clearListButton').click(function()
{
	localStorage.removeItem('listList');
	$('#myMovieList').empty();
});

/*
*	Triggers the add value event when the enter key is pressed in the input box
*/
$('#valueToAdd').keypress(function(e) {
    if(e.which == 13) {
        addValue();
    }
});

/*
*	On Document Loaded Reload List
*/
$(document).ready(function() {
	movieListArray = JSON.parse(localStorage['listList']);
	for (var i = 0; i < movieListArray.length; i++) {
		addRow(movieListArray[i], i);
	}
});

/*
*	Adds a row to the list of movies
*/
function addRow(movieTitle, index)
{
	$('#myMovieList').append('<li>' + movieTitle + "<button class='viewButton' id='viewButton" +index + "' type='button'>View List</button>" +  "<button class='removeButton' id='removeButton" + index + "' type='button'>Remove</button>" +  '</li>');
	$("#removeButton" + index).click(function() {
		console.log("Remove button " + index + " clicked.");
		removeRow(index);
		window.location.reload();
	});
	$("#viewButton" + index).click(function() {
		console.log("View button " + index + " clicked.");
		window.location = "viewlist.html?listindex=" + index;
	});
}

/*
*	Removes a row from the list of movies
*/
function removeRow(index)
{
	$("#myMovieList li").eq(index).remove();
	var movieListArray = new Array();
	if (localStorage.getItem("listList") === null)
	{
	}
	else
	{
		movieListArray = JSON.parse(localStorage['listList']);
		movieListArray.splice(index, 1);
		localStorage['listList'] = JSON.stringify(movieListArray);
	}
	localStorage.removeItem('movieList' + index);
}

/*
*	Gets the value from the input box and adds it the list as well as the localstorage
*/
function addValue()
{
	if ($('#valueToAdd').val() !== "")
	{
		var movieListArray = new Array();
		if (localStorage.getItem("listList") === null)
		{
			movieListArray = new Array();
			movieListArray.push($('#valueToAdd').val());
			localStorage['listList'] = JSON.stringify(movieListArray);
		}
		else
		{
			movieListArray = JSON.parse(localStorage['listList']);
			movieListArray.push($('#valueToAdd').val());
			localStorage['listList'] = JSON.stringify(movieListArray);
		}
		addRow($('#valueToAdd').val(), movieListArray.length - 1);
		$('#valueToAdd').val("");
	}
}