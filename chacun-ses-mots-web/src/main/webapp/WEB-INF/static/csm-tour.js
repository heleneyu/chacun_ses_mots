cpt = 1;
async function addListenersToTour(){
	let reponses = document.getElementsByClassName('carteReponse')
  Array.prototype.forEach.call(reponses,
      element => {
          element.addEventListener('click', event => 
          {
        	  if(cpt = 1 && document.getElementById('r1')!=event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',event.target.getAttribute('id'))
              event.target.style.backgroundColor = red;
              document.getElementById('tour-submit-button').style.display = "block";
              cpt += 1;
            }
            if(cpt = 1 && document.getElementById('r1')!=event.target.getAttribute('id')){
              document.getElementById('r1').setAttribute('value',event.target.getAttribute('id'))
              event.target.style.backgroundColor = red;
              document.getElementById('tour-submit-button').style.display = "block";
              cpt += 1;
        	  }
          }
          )
      }
  )
}