var listIndex;

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
	localStorage.removeItem('movieList' + listIndex);
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
	getListIndex();
	console.log(listIndex);
	movieListArray = JSON.parse(localStorage['movieList' + listIndex]);
	for (var i = 0; i < movieListArray.length; i++) {
		addRow(movieListArray[i], i);
	}
});

/*
*	Adds a row to the list of movies
*/
function addRow(movieTitle, index)
{
	$('#myMovieList').append('<li>' + movieTitle + "<button class='removeButton' id='removeButton" + index + "' type='button'>Remove</button>" +  '</li>');
	$("#removeButton" + index).click(function() {
		//console.log("Remove button " + index + " clicked.");
		removeRow(index);
	});
}

/*
*	Removes a row from the list of movies
*/
function removeRow(index)
{
	$("#myMovieList li").eq(index).remove();
	var movieListArray = new Array();
	if (localStorage.getItem("movieList" + listIndex) === null)
	{
	}
	else
	{
		movieListArray = JSON.parse(localStorage['movieList' + listIndex]);
		movieListArray.splice(index, 1);
		localStorage['movieList' + listIndex] = JSON.stringify(movieListArray);
	}
}

/*
*	Gets the value from the input box and adds it the list as well as the localstorage
*/
function addValue()
{
	if ($('#valueToAdd').val() !== "")
	{
		var movieListArray = new Array();
		if (localStorage.getItem("movieList" + listIndex) === null)
		{
			movieListArray = new Array();
			movieListArray.push($('#valueToAdd').val());
			localStorage['movieList' + listIndex] = JSON.stringify(movieListArray);
		}
		else
		{
			movieListArray = JSON.parse(localStorage['movieList' + listIndex]);
			movieListArray.push($('#valueToAdd').val());
			localStorage['movieList' + listIndex] = JSON.stringify(movieListArray);
		}
		addRow($('#valueToAdd').val(), movieListArray.length - 1);
		$('#valueToAdd').val("");
	}
}

/*
*	Get the index of the list to be shown from the get parameters
*/
function getListIndex()
{
	var prmstr = window.location.search.substr(1);
	var prmarr = prmstr.split ("&");
	var params = {};

	for ( var i = 0; i < prmarr.length; i++) {
	    var tmparr = prmarr[i].split("=");
	    params[tmparr[0]] = tmparr[1];
	}
	listIndex = params.listindex;
}

/*
*	Autocompletes movie titles
*/
var apikey = "tzyx8cp2g2au6jhbq3gj43f4";

$("#valueToAdd").autocomplete({
    source: function( request, response ) {
        $.ajax("http://api.rottentomatoes.com/api/public/v1.0/movies.json", {
            data: {
                apikey: apikey,
                q: request.term
            },
            dataType: "jsonp",
            success: function(data) {
                console.log(data);
                response($.map(data.movies, function(movie) {
                    return {
                        label: movie.title,
                        value: movie.title,
                        thumb: movie.posters.thumbnail
                    }
                }));           
            }
        });
    }
}).data( "autocomplete" )._renderItem = function( ul, item ) {
    var img = $("<img>").attr("src", item.thumb);
    var link = $("<a>").text(item.label).prepend(img);
    return $("<li>")
        .data( "item.autocomplete", item )
        .append(link)
        .appendTo(ul);
};