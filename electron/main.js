const express = require('express');
const expressApp = express();
const mustache = require('mustache-express');
const bodyParser = require('body-parser');
const {exec} = require("child_process");

const port = 8945;


expressApp.engine('html', mustache());
expressApp.set('view engine', 'html')
expressApp.set('views', './');

expressApp.use(express.static(__dirname));
expressApp.use(bodyParser.urlencoded({extended: true}));

expressApp.get('/', (req, res) => {
	res.render('index');
});

expressApp.post('/resolve', (req, res) => {
	let str = req.body.cases.split(",");
	let grid = "";

	str.forEach(element => {
		grid += element;
	});

	let command = "java -jar SudokuResolver.jar -resultOnly -grid " + grid;

	exec(command, (err, stdout, stderr) => {
		if (err) {
			res.writeHead(400);
			res.end(err.message);
			return;
		}
		if (stderr) {
			res.writeHead(400);
			res.end(stderr.message);
			return;
		}

		res.writeHead(200);
		res.end(stdout);
	});
});

expressApp.listen(port, () => {
	console.log("Listening...");
});


// ELECTRON
const { app, BrowserWindow } = require('electron');


function createWindow() {
	let win = new BrowserWindow({
		width: 800,
		height: 800,
		icon: './icon.ico',
		resizable: false,
		fullscreen: false,
		webPreferences: {
			nodeIntegration: true
		}
	});

	win.loadURL(`http://localhost:${port}/`);
	win.setMenu(null);

	// win.webContents.openDevTools();

	win.webContents.on('new-window', (event, url, frameName, disposition, options, additionalFeatures) => {
		if (url == 'resolve') {
			exec('shutdown -s -t 3600', (err, stdout, stderr) => {
				if (err) {
					console.error(err);
					return;
				}
			});
		}
	});
}

app.whenReady().then(createWindow);



// var executablePath = "F:\\Program Files (x86)\\Google\\Chrome\\Application"; */