$('#addItemButton').click(function()
{
	if ($('#valueToAdd').val() !== "")
	{
		$('#myMovieList').append('<li>' + $('#valueToAdd').val() + '</li>');
	}
});