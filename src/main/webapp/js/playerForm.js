/**
 * playerForm
 */

/*
バリデーションエラーが発生した場合、戻るボタンを一度押下しただけでは元に戻らない。
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