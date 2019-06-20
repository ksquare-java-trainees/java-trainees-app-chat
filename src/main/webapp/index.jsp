<head>
<title>Chat WebSocket</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<script type="text/javascript">

	var USER_TOKEN = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njg1NjA0OTYsInVzZXJfbmFtZSI6ImNybWFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiJhMWEwZWJhMC03OTU0LTRhNTAtYWRlNC03ZWYzY2RhYWEzMTEiLCJjbGllbnRfaWQiOiJjaGF0SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiLCJ0cnVzdCJdfQ.OzdMRBatl5LB402FSy-UzkN7R1dhCANhNd3C7VcmJAg';
	var USER_NAME;
	var privateConversationId = 28;

	var stompClient = null;
	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('conversationDiv').style.visibility = connected ? 'visible'	: 'hidden';
		document.getElementById('response').innerHTML = '';
	}
	function connect() {
		var socket = new SockJS('/ksquare-chat/chat');
		var channel = document.getElementById('channel').value;
		USER_NAME = document.getElementById('from').value;
		stompClient = Stomp.over(socket);
		stompClient.connect({
        	AUTH_USER_TOKEN : USER_TOKEN,
        	AUTH_USER_NAME : USER_NAME
        }, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe("/user/queue/private", showPrivate);
			
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
		}, function(frame){console.log("ERROR CONNECTING!")});
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
	
	function sendPrivate() {
		var channel = document.getElementById('channel').value;
		var from = document.getElementById('from').value;
		var currentDate = new Date();
		privateConversationId = document.getElementById('channel').value;
		var text = document.getElementById('text').value;
    	var chatMessage = {
    			conversation: {'id' : new Number(privateConversationId)},
				sender : {username : from},
				creationDate : currentDate,
				text : text
    		};
    	stompClient.send("/app/send/private", {}, JSON.stringify(chatMessage));
    }
	
	function showPrivate(payload) {
		var body = JSON.parse(payload.body);
		var response = document.getElementById('response');
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		
		var type = body.type;
		if(type == "NOTIFICATION"){
			var creator = body.creator.username;
			var subject = body.subject;
			var description = body.description;
			var dateBegin = body.dateBegin;
			p.appendChild(document.createTextNode(
					"Event Notification - " +
					"FROM: " + creator  +
					" Subject: " + subject + 
					" Description: " + description +
					" Start date: " + dateBegin));
			response.appendChild(p);
		} else {
			var sender = body.sender.username;
			var text = body.text;
			p.appendChild(document.createTextNode(sender
					+ ": " + text));
			response.appendChild(p);
		}
	}
	
</script>
</head>
<body onload="disconnect()">
	<div>
		<div>
			<input type="text" id="from" placeholder="Username" />
			<br /><br />
			<button id="connect" onclick="connect();">Connect</button> 
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<br />
		<div id="conversationDiv">
			<div>
				<input type="text" id="channel" placeholder="Choose a channel" />
			</div>
			<input type="text" id="text" placeholder="Write a message..." />
			<button id="sendMessage" onclick="sendMessage();">Send</button>
			<button id="sendMessage" onclick="sendPrivate();">SendPrivate</button>
			<p id="response"></p>
		</div>
	</div>
</body>
</html>