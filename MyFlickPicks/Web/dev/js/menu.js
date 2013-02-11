$('#addItemButton').click(function()
{
	$('#myMovieList').append('<li>' + $('#valueToAdd').val() + '</li>');
});