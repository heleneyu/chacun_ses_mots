cpt = 1;
async function addListenersToTour(){
	let reponses = document.getElementsByClassName('carteReponse')
  Array.prototype.forEach.call(reponses,
      element => {
          element.addEventListener('click', event => 
          {
        	  if(cpt = 1 && document.getElementById('r1')!=event.target.getAttribute('id')){
              document.getElementById('carteid').setAttribute('value',event.target.getAttribute('cid'))
              document.getElementById('tour-submit-button').style.display = "block"
              console.log(event.target.getAttribute('cid'))
        	  }
          }
          )
      }
  )
}