if(!window.WebSocket){
  alert("Tu navegador no puede hacer uso del chat porque no soporta WebSockets");
  window.location.href='/';
}

function resetForm(){
  m=document.getElementById("message");
  m.value="";
}

var socket = new WebSocket('ws://'+window.location.host+'/message');
socket.onmessage = function(evt) { 
  var output=document.getElementById("output");
  output.innerHTML=output.innerHTML+evt.data+'\n';
  output.scrollTop = output.scrollHeight;
};


function submitForm(uri, user){
  m=document.getElementById("message");
  container=document.getElementById("container");
  if(m.value==""){
    var error=document.createElement("dd");
    error.setAttribute("id","errorDD");
    error.setAttribute("class","error");
    error.innerHTML='Escribe algo por favor';
    container.appendChild(error);
  }else{
    error=document.getElementById("errorDD");
    if(error!=null)
      container.removeChild(error);
  }
  m=document.getElementById("message");
  socket.send(user+m.value);
  m.value="";
}