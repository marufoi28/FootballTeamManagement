/*
選手一覧画面
*/

/* 検索エリア クリアボタン */
function clearFields(){
	document.querySelector('.search-name-input').value='';
	const checkBoxes = document.querySelectorAll('.search-position-input');
	checkBoxes.forEach(checkBox => checkBox.checked = false);
	
	/* ラジオボタン */
	const radioButtons = document.querySelectorAll('.search-license-input, .search-student-input');
	radioButtons.forEach(radio => radio.checked = false);
	
	/* 指定なしを選択 */
	const radioNoneLicense = document.querySelector('.search-license-input[value=""]');
	if (radioNoneLicense) radioNoneLicense.checked = true;
	
	const radioNoneStudent = document.querySelector('.search-student-input[value=""]');
	if (radioNoneStudent) radioNoneStudent.checked = true;
	
	/* セレクトボックスをリセット */
	const selectBoxes = document.querySelectorAll('.search-age-input');
	selectBoxes.forEach(selectBox => selectBox.selectedIndex = 0);
	
	/* セッション変数をリセットするためのリクエストを送信 */
	fetch('/FootballTeamManagement/ResetSessionServlet', {
	    method: 'POST'
	})
	.then(response => {
	    if (response.ok) {
	        console.log('セッション変数がリセットされました');
	    } else {
	        console.error('サーバーエラー:', response.statusText);
	    }
	})
	.catch(error => {
	    console.error('セッションリセット中にエラーが発生しました', error);
	});
}

/* モーダル */
const opens = document.querySelectorAll('.open');
const closes = document.querySelectorAll('.close');
const modals = document.querySelectorAll('.modal');
const masks = document.querySelectorAll('.mask');

const showKeyframes = {
	opacity: [0, 1],
	visibility: 'visible',
};

const hideKeyframes = {
	opacity: [1,0],
	visibility: 'hidden',
};

const options = {
	duration: 800,
	easing: 'ease',
	fill: 'forwards',
};

/* モーダルウィンドウを開く */
opens.forEach((open, index) => {
	open.addEventListener('click', () => {
		modals[index].animate(showKeyframes, options);
		masks[index].animate(showKeyframes, options);
	});
});

/* モーダルウィンドウを閉じる */
closes.forEach((close, index) => {
	close.addEventListener('click', () => {
		modals[index].animate(hideKeyframes, options);
		masks[index].animate(hideKeyframes, options);
	});
});