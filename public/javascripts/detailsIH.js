function confirmDel(url){
  var result = confirm("¿Bloquearás a este usuario?");
  if (result==true) {
    window.location.href = url;
  }
}