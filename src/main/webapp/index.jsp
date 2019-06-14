<html>
<head>
<title>Chat WebSocket</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script type="text/javascript">
	var stompClient = null;

	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility = connected ? 'visible'	: 'hidden';
		document.getElementById('response').innerHTML = '';
	}

	function connect() {
		var socket = new SockJS('/java-trainees-app-chat/chat');
		var channel = document.getElementById('channel').value;
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);

			if (channel == '') {
				stompClient.subscribe('/topic/messages/public', function(
						messageOutput) {
					showMessageOutput(JSON.parse(messageOutput.body));
				});
			} else {
				stompClient.subscribe('/topic/messages/' + channel, function(
						messageOutput) {
					showMessageOutput(JSON.parse(messageOutput.body));
				});
			}
		});
	}

	function disconnect() {
		if (stompClient != null) {
			stompClient.disconnect();
		}
		setConnected(false);
		console.log("Disconnected");
	}

	function sendMessage() {
		var channel = document.getElementById('channel').value;
		var from = document.getElementById('from').value;
		var currentDate = new Date();
		var text = document.getElementById('text').value;

		if (channel == '') {
			stompClient.send("/app/chat/public", {},
			JSON.stringify({
				'conversation' : 0,//Fix this
				'sender' : from,
				'creationDate' : currentDate,
				'text' : text
			}));
		} else {
			stompClient.send("/app/chat/" + channel, {},
			JSON.stringify({
				'conversation' : {'id' : new Number(channel)},
				'sender' : {'id' : new Number(from)},
				'creationDate' : currentDate,
				'text' : text
			}));
		}
	}

	function showMessageOutput(messageOutput) {
		var response = document.getElementById('response');
		var p = document.createElement('p');
		var channel = document.getElementById('channel').value;
		p.style.wordWrap = 'break-word';
		if (channel == '') {
			p.appendChild(document.createTextNode(messageOutput.from
					+ " says : " + messageOutput.text + " ("
					+ messageOutput.time + ")"));
			response.appendChild(p);
		} else {
			p.appendChild(document.createTextNode(messageOutput.from
					+ " says : " + messageOutput.text + " at this channel: "
					+ messageOutput.channel + " (" + messageOutput.time + ")"));
			response.appendChild(p);
		}
	}
</script>
</head>
<body onload="disconnect()">
	<div>
		<div>
			<input type="text" id="from" placeholder="Choose a nickname" />
		</div>
		<div>
			<input type="text" id="channel" placeholder="Choose a channel" />
		</div>
		<br />
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">
				Disconnect</button>
		</div>
		<br />
		<div id="conversationDiv">
			<input type="text" id="text" placeholder="Write a message..." />
			<button id="sendMessage" onclick="sendMessage();">Send</button>
			<p id="response"></p>
		</div>
	</div>
</body>
</html>