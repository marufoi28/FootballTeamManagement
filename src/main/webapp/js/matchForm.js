/**
 * 
 */
document.querySelector('.back').addEventListener('click', function(){
	document.querySelector('form').reset();
	
	let errorMessages = document.querySelectorAll('.error-message');
	errorMessages.forEach(function(error) {
	    error.style.display = 'none';
	});
	
	console.log("戻る");
	window.history.back();
});