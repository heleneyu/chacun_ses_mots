
carteReponseAEnvoyer = {};


async function getCarteReponse(elem) {
    return carteR = {
        id: elem.cid
    }
}


async function animateCR() {

}


async function chargeCarteReponse(event) {

    carteReponseAEnvoyer = { id: event.target.getAttribute("cid") }
    console.log(carteReponseAEnvoyer)

}


async function addListenersToCR(CRAE) {

    let cartesReponses = document.getElementsByClassName('carteReponse')
    Array.prototype.forEach.call(cartesReponses,
        element => {
            element.addEventListener('click', chargeCarteReponse)
        }
    )
}

async function addListenersToVote() {

    let reponses = document.getElementsByClassName('reponse')
    Array.prototype.forEach.call(reponses,
        element => {
            element.addEventListener('click', event => 
            {
                document.getElementById('voteId').setAttribute('value',event.target.getAttribute('cid'))
            }
            )
        }
    )
}

async function addListenersToTour() {

    let reponses = document.getElementsByClassName('carteReponse')
    Array.prototype.forEach.call(reponses,
        element => {
            element.addEventListener('click', event => 
            {
                document.getElementById('carteid').setAttribute('value',event.target.getAttribute('cid'))
                document.getElementById('tour-submit-button').style.display = "block"
                console.log(event.target.getAttribute('cid'))
            }
            )
        }
    )
}


async function addListenersToTour2() {

    let reponses = document.getElementsByClassName('reponsePerso')
    Array.prototype.forEach.call(reponses,
        element => {
            element.addEventListener('keyup', event => 
            {
                if(document.getElementById('carteid').hasAttribute('value') || document.getElementById('reponselibreinput').value !== ''){
                document.getElementById('reponselibre').setAttribute('value',event.target.value)
                document.getElementById('tour-submit-button').style.display = "block"
                console.log(event.target.value)
                }
                else{
                    document.getElementById('tour-submit-button').style.display = "none"
                }
            }
            )
        }
    )
}

//addListenersToCR();
addListenersToTour();
addListenersToTour2();

