function submitForm(){
  document.getElementById("form").submit();
}

function resetForm(){
  document.getElementById("form").reset();
}

function previousPhoto(){
  list=document.getElementsByClassName("visible");
  img=list[0];
  img.setAttribute("class","image hidden");
  nextId=(parseInt(img.id, 10)+4)%5;
  nextImg=document.getElementById(nextId);
  nextImg.setAttribute("class","image visible");
}

function nextPhoto(){
  list=document.getElementsByClassName("visible");
  img=list[0];
  img.setAttribute("class","image hidden");
  nextId=(parseInt(img.id, 10)+1)%5;
  nextImg=document.getElementById(nextId);
  nextImg.setAttribute("class","image visible");
}