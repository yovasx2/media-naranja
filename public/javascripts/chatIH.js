// verify web browser compatibility
var support = "MozWebSocket" in window ? 'MozWebSocket' : ("WebSocket" in window ? 'WebSocket' : null);
if (support == null) {
  alert("Tu navegador no puede hacer uso del chat porque no soporta WebSockets");
  window.history.back();
}

var messages;
messages = new window[support]('ws://'+window.location.host+'/message');

messages.onmessage=function(evt) { 
  output=document.getElementById("output");
  output.innerHTML=output.innerHTML+evt.data+'\n';
  output.scrollTop=output.scrollHeight;
};

function resetForm(){
  m=document.getElementById("message");
  m.value="";
}

function submitForm(user){
  m=document.getElementById("message");
  error=document.getElementById("errorDD");
  if(m.value==""){
    if(error==null){
      var error=document.createElement("dd");
      error.setAttribute("id","errorDD");
      error.setAttribute("class","error");
      error.innerHTML='Escribe algo por favor';
      container.appendChild(error);
    }
    return;
  }else{
    error=document.getElementById("errorDD");
    if(error!=null){
      container=document.getElementById("container");
      container.removeChild(error);
    }
    messages.send(user+m.value);
    m.value="";
  }
}

$(document).ready(function() {
  $('.submitOnEnter').keydown(function(event) {
    if (event.keyCode == 13) {
      document.getElementById('submitButton').click();
      return false;
    }
  });
});