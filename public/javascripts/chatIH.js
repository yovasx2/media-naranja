var messages;

if(!window.WebSocket){
  alert("Tu navegador no puede hacer uso del chat porque no soporta WebSockets");
  window.location.href='/';
}
messages=new WebSocket('ws://'+window.location.host+'/message');
messages.onmessage=function(evt) { 
  output=document.getElementById("output");
  output.innerHTML=output.innerHTML+evt.data+'\n';
  output.scrollTop=output.scrollHeight;
};

function resetForm(){
  m=document.getElementById("message");
  m.value="";
}

function submitForm(uri, user, username){
  m=document.getElementById("message");
  container=document.getElementById("container");
  if(m.value==""){
    var error=document.createElement("dd");
    error.setAttribute("id","errorDD");
    error.setAttribute("class","error");
    error.innerHTML='Escribe algo por favor';
    container.appendChild(error);
    return;
  }else{
    error=document.getElementById("errorDD");
    if(error!=null)
      container.removeChild(error);
  }
  m=document.getElementById("message");
  messages.send(user+m.value);
  m.value="";
}

$(document).ready(function() {
  $('.submitOnEnter').keydown(function(event) {
    if (event.keyCode == 13) {
      document.getElementById('submitButton').click();
      return false;
    }
  });
});