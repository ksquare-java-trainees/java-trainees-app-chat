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
            	var socket = new SockJS('/chat');
                stompClient = Stomp.over(socket);
                setConnected(true);
                stompClient.connect({
                	name : user
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
            	var toId = document.querySelector('#toID').value.trim();
            	var fromId = document.querySelector('#fromID').value.trim();
            	var chatMessage = {
            			message : document.querySelector('#message').value.trim(),
            			from 	: fromId,
            			to  	: toId
            		};
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