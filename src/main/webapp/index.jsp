<html>
    <head>
        <title>Chat WebSocket</title>
	     <!-- <script src="/webjars/sockjs-client/sockjs.min.js"></script>
	    <script src="/webjars/stomp-websocket/stomp.min.js"></script>  --> 
	     <!-- This for my machine -->
	     <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.2/sockjs.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	     
        <script type="text/javascript">
            var stompClient = null;
             
            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility 
                  = connected ? 'visible' : 'hidden';
                document.getElementById('response').innerHTML = '';
            }
             
            function connect() {
            	//This for my machine
                var socket = new SockJS('/java-trainees-app-chat/chat');//This one.
                var topic = document.getElementById('topic').value;//Addthis
                stompClient = Stomp.over(socket);  
                stompClient.connect({}, function(frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    
                    if(topic==''){
                    	stompClient.subscribe('/topic/messages/public', function(messageOutput) {//Change this to suscribe at exact topic
                            showMessageOutput(JSON.parse(messageOutput.body));
                        });
                    }
                    else{
                    	stompClient.subscribe('/topic/messages/'+topic, function(messageOutput) {//Change this to suscribe at exact topic
                            showMessageOutput(JSON.parse(messageOutput.body));
                        });
                    }
                    
                    //stompClient.subscribe('/topic/messages/'+topic, function(messageOutput) {//Change this to suscribe at exact topic
                      //  showMessageOutput(JSON.parse(messageOutput.body));
                    //});
                });
            }
             
            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }
             
            function sendMessage() {
                var from = document.getElementById('from').value;
                var text = document.getElementById('text').value;
                var topic = document.getElementById('topic').value;//Add this
                
                if(topic==''){
                	stompClient.send("/app/chat/public", {}, //Add /+topic
                            JSON.stringify({'from':from, 'text':text}));//Change this
                }
                else{
                	stompClient.send("/app/chat/"+topic, {}, //Add /+topic
                            JSON.stringify({'from':from, 'text':text, 'topic':topic}));//Change this
                }
                
                //stompClient.send("/app/chat/"+topic, {}, //Add /+topic
                  //JSON.stringify({'from':from, 'text':text, 'topic':topic}));//Change this
            }
             
            function showMessageOutput(messageOutput) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                var topic = document.getElementById('topic').value;//Add this
                p.style.wordWrap = 'break-word';
                if(topic==''){
                	p.appendChild(document.createTextNode(messageOutput.from + " says : " 
                             + messageOutput.text + " (" + messageOutput.time + ")"));//Change this
                           response.appendChild(p);
                }else{
                	p.appendChild(document.createTextNode(messageOutput.from + " says : " 
                             + messageOutput.text + " at this topic: "+ messageOutput.topic +" (" + messageOutput.time + ")"));//Change this
                           response.appendChild(p);
                }
                //p.appendChild(document.createTextNode(messageOutput.from + " says : " 
                 // + messageOutput.text + " at this topic: "+ messageOutput.topic +" (" + messageOutput.time + ")"));//Change this
                //response.appendChild(p);
            }
        </script>
    </head>
    <body onload="disconnect()">
        <div>
            <div>
                <input type="text" id="from" placeholder="Choose a nickname"/>
            </div>
            <div>
                <input type="text" id="topic" placeholder="Choose a topic"/><!-- Add this -->
            </div>
            <br />
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">
                    Disconnect
                </button>
            </div>
            <br />
            <div id="conversationDiv">
                <input type="text" id="text" placeholder="Write a message..."/>
                <button id="sendMessage" onclick="sendMessage();">Send</button>
                <p id="response"></p>
            </div>
        </div>
 
    </body>
</html>