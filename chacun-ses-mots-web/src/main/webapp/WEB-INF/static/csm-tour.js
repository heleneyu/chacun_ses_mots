console.log("coucou");
cpt = 1;
async function addListenersToTour(){
  console.log("addtour");
	let reponses = document.getElementsByClassName('carteReponse');
  Array.prototype.forEach.call(reponses,
      element => {
          element.addEventListener('click', event => 
          {
            console.log("addtourevent")
        	  if(cpt == 1 && document.getElementById('r1') != event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',event.target.getAttribute('id'));
              event.target.style.backgroundColor = "red";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt += 1;
            }
            else if(cpt == 2 && document.getElementById('r1') == event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',"");
              event.target.style.backgroundColor = "wheat";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt -= 1;
            }
            else if(cpt == 2 && document.getElementById('r2') != event.target.getAttribute('id')){
              document.getElementById('r2').setAttribute('value',event.target.getAttribute('id'));
              event.target.style.backgroundColor = "blue";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt += 1;
            }
            else if(cpt == 3 && document.getElementById('r2') == event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',"");
              event.target.style.backgroundColor = "wheat";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt -= 1;
            }
            else if(cpt == 3 && document.getElementById('r3') != event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',event.target.getAttribute('id'));
              event.target.style.backgroundColor = "green";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt += 1;
            }
            else if(cpt == 4 && document.getElementById('r3') == event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',"");
              event.target.style.backgroundColor = "wheat";
              document.getElementById('tour-submit-button').style.display = "block";
              cpt -= 1;
            }
          }
          )
      }
  )
}

addListenersToTour();