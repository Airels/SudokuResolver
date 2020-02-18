var messagesLabel;

function init() {
	messagesLabel = document.getElementById('messages');
}

function parseSudoku() {
	messagesLabel.innerHTML = "";
	messagesLabel.style.color = "";

	var predicate = /^[1-9]{1}$/;

	var cases = [];

	for (let i = 0; i < 81; i++) {
		let value = document.getElementById(`case${i+1}`).value;

		if (value == "")
			cases[i] = 0;
		else if (predicate.test(value))
			cases[i] = value;
		else {
			error("ERROR DURING PARSING: NOT GOOD VALUE");
			return;
		}
	}

	resolve(cases);
}

function resolve(cases) {
	var xhr = new XMLHttpRequest();

	xhr.open('POST', '/resolve');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('cases=' + cases);

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 400)
			error(this.responseText);
		if (this.readyState == 4 && this.status == 200) {
			let answer = this.responseText;

			for (let i = 0; i < answer.length; i++) {
				document.getElementById(`case${i+1}`).value = answer.charAt(i);
			} 
		}
	}
}

function error(msg) {
	messagesLabel.innerHTML = msg;
	messagesLabel.style.color = "red";
	console.error(msg);
}

function checkValue(id) {
	var oneCase = document.getElementById(id);

	if (oneCase.value.length > 1)
		oneCase.value -= (oneCase.value[0] + 0);

	if (oneCase.value == 0)
		oneCase.value = "";
}