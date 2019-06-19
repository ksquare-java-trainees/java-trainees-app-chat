<html>
    <head>
        <title>Chat WebSocket</title>
	    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
	    <script src="/webjars/stomp-websocket/stomp.min.js"></script>
        <script type="text/javascript">
            var stompClient = null;
            var user = '';
             
            function connect() {
            	user = document.querySelector('#fromID').value.trim();
            	var socket = new SockJS('/ws/messages');
                stompClient = Stomp.over(socket);
                setConnected(true);
                
                stompClient.connect({
                	AUTH_API_KEY : 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Njg1NjA0OTYsInVzZXJfbmFtZSI6ImNybWFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiJhMWEwZWJhMC03OTU0LTRhNTAtYWRlNC03ZWYzY2RhYWEzMTEiLCJjbGllbnRfaWQiOiJjaGF0SWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiLCJ0cnVzdCJdfQ.OzdMRBatl5LB402FSy-UzkN7R1dhCANhNd3C7VcmJAg',
                	AUTH_USER_NAME : user
                }, openChatSocket);
            }
            
            function openChatSocket(){                
            	stompClient.subscribe("/user/queue/private", onMessage);
            	//stompClient.subscribe("/topic/"+user, onMessage);
            }
			
            function onMessage(payload){
				var body = JSON.parse(payload.body);
				
            	var response = document.getElementById('personalDiv');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode(body.from + ": " 
                  									+ body.message));
                response.appendChild(p);
            }
             
            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }
            
            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility 
                  = connected ? 'visible' : 'hidden';
                document.getElementById('allDiv').innerHTML = '';
                document.getElementById('personalDiv').innerHTML = '';
            }
            
            function sendSocket() {
            	var text = document.querySelector('#message').value.trim();
            	var sender = document.querySelector('#fromID').value.trim();
            	var toId = document.querySelector('#toID').value.trim();
            	var chatMessage = {};
            	/*var chatMessage = {
            			conversation': {'id' : new Number(channel)},
        				sender : {'username' : sender},
        				creationDate : currentDate,
        				text : text
            		};*/
            	//stompClient.send("/app/send/message", {}, JSON.stringify(chatMessage));
            	stompClient.send("/app/send/private", {}, JSON.stringify(chatMessage));
            	document.querySelector('#message').value = '';
            }
                       
        </script>
    </head>
    <body onload="disconnect()">
        <div>
            <div>
                <input type="text" id="fromID" placeholder="Your user ID"/>
            </div>
            <br />
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">
                    Disconnect
                </button>
            </div>
            <br />
            <div>
                <input type="text" id="toID" placeholder="User ID to send the message to"/>
            </div>
            <br/>
            <div>
            	<input type="text" id="message" placeholder="Write a message..."/>
                <button id="sendSocket" onclick="sendSocket();">Send using socket subscription</button>
            </div>
            <div id="conversationDiv" style="{border:1px solid}">
            	<div id="allDiv"></div>
	            <hr>
	            <div id="personalDiv"></div>
            </div>
            
        </div>
 
    </body>
</html>