function validateusername(username) {
    const reg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return reg.test(String(username).toLowerCase());
}


//page login
if (document.title == "Connexion" || document.title == "Chacun ses mots - Création partie") {
    //on définit les différents input en javascript
    const loginButton = document.getElementById("boutonLogin");
    const username = document.getElementById("username");
    const mdp = document.getElementById("password");
    const save = document.getElementById("remember");
    //quand on clique sur le bouton sur la page login
    loginButton.addEventListener('click', function (event) {
        event.preventDefault();
        //conditions username
        if (username.value != ""){
            valide(true, username);
        }
        else{
            valide(false, username);
        }
        //condition mot de passe
        if (mdp.value != ""){
            valide(true, mdp);
        }
        else{
            valide(false, mdp);
        }
        
        //si toutes les entrées sont validées, on renvoie les datas au json
        if (username.value != "" && mdp.value != "") {
            let data = { //on crée l'objet à envoyer
                "username": username.value,
                "mdp": mdp.value,
                "save": save.value
            }
            const ajax = new Ajax('https://jsonplaceholder.typicode.com');
            ajax.post('/users', data).then(json => console.log(json));
        }

    });
}

//page inscription
if (document.title == "Inscription") {
    const inscriptionBouton = document.getElementById("buttonInscription"); //on définit le bon bouton
    const email = document.getElementById("email");
    const username = document.getElementById("username");
    const mdpIn = document.getElementById("password");
    const mdp2In = document.getElementById("password2");

    const condition = document.getElementById("cgu");

    //quand on clique sur s'inscrire
    inscriptionBouton.addEventListener('click', function (event) {
        event.preventDefault();
        const params = {
            username: username,
            email:email,
            mdp1: mdpIn,
            mdp2: mdp2In,
            condition: condition.checked
        }
        //s'il manque une donnée:
        if (email.value == "" || username.value == "" || mdpIn.value == "" || mdp2In.value == "" ) {
            valideall(false, params);
        }
        else if (email.value != "" && username.value != "" && mdpIn.value != "" && mdp2In.value != "") {
            valideall(true, params);
            //si tout est ok
            if (condition.checked == true) {
                let data = { //on crée l'objet à envoyer
                    "email": email.value,
                    "username": username.value,
                    "mdp": mdpIn.value,
                    "condition": condition.checked,
                }
                const ajax = new Ajax('https://jsonplaceholder.typicode.com');
                ajax.post('/users', data).then(json => console.log(json)); //on l'envoie au json
            }
        }
    });
}

function valide(ok, input) {
    elemId = "error" + input.id;
    text = "Votre " + input.id + " n'est pas valide !";
    if (ok) {
        const elem = document.getElementById(elemId);
        if (elem != null) { //si le message d'erreur est apparu
            elem.remove();
        }
        input.style.borderColor = "green";
        input.style.borderStyle = "solid";
        input.style.borderWidth = "1px";

    }
    else { // en cas d'erreur, faire apparaitre un message
        const elem = document.createElement('p'); //on crée un paragraphe
        elem.id = elemId; //on lui donne un id
        elem.textContent = text; //on lui donne le texte
        input.style.borderColor = "red";
        input.style.borderStyle = "solid";
        input.style.borderWidth = "1px";
        if (document.getElementById(elemId) == null) { //si le message n'était pas encore apparu
            const Parent = input.parentNode;
            Parent.appendChild(elem);
        }
    }
}

function valideall(bool, input) {
    let boolusername = true;
    let boolMdp = true;
    elemId = "msgErreur";
    if (bool) {//si tout les champ sont remplis
        const elem = document.getElementById(elemId);
        if (elem != null) { //si le message d'erreur est apparu
            elem.remove();
        }
        //on met tout en vert
        input.username.style.borderColor = "green";
        input.username.style.borderStyle = "solid";
        input.username.style.borderWidth = "1px";
        //on teste l'email
        boolEmail = validateusername(input.email.value);
        if (boolEmail) {
            input.email.style.borderColor = "green";
            input.email.style.borderStyle = "solid";
            input.email.style.borderWidth = "1px";
        }
        if (input.mdp1.value != input.mdp2.value) {//pour vérifier les deux mots de passes
            boolMdp = false;//mot de passe 2
        }
        else {
            //si tout les champs sont remplis, on met tout en vert
            input.mdp1.style.borderColor = "green";
            input.mdp1.style.borderStyle = "solid";
            input.mdp1.style.borderWidth = "1px";
            input.mdp2.style.borderColor = "green";
            input.mdp2.style.borderStyle = "solid";
            input.mdp2.style.borderWidth = "1px";
            const cond = document.getElementById("cguText");
            cond.style.color = "black";
        }
    } else {
        //si tout les champs ne sont pas remplis
        const div = document.createElement('div');//on crée la boite html pour le paragraphe
        const elem = document.createElement('p'); //on crée un paragraphe
        elem.id = elemId; //on lui donne un id
        elem.textContent = "Veuillez remplir tous les champs"; //on lui donne un texte CS6 -> innerText
        elem.style.style = "bold";
        elem.style.borderColor = "red";
        elem.style.borderStyle = "solid";
        elem.style.borderWidth = "1px";
        //for (let entree in input){ 
        //    console.log(entree);
        //    if (entree.value == null){ //on met en rouge les entrée qu'on a pas remplie
        //        entree.style.borderColor = "red";
        //        entree.style.borderStyle = "solid";
        //        entree.style.borderWidth = "1px";
        //    }
        //}
        if (document.getElementById(elemId) == null) { //si le message n'était pas encore apparu
            //const lieuDiv = document.getElementById("section").prepend(div);
            const lieuDiv = document.getElementById("section");
            const firstChild = lieuDiv.firstChild;
            lieuDiv.insertBefore(div, firstChild);
            div.appendChild(elem); //en CS6 innerHTML
        }
    }
    //si tout les champs sont remplis, mais que
    if (!(boolEmail) && bool) {// l'email est incorrect
        const div = document.createElement('div');//on crée la boite html pour le paragraphe
        const elem = document.createElement('p'); //on crée un paragraphe
        elem.textContent = "Votre email n'est pas valide !"; //on lui donne un texte
        elem.id = elemId; //on lui donne un id
        input.email.style.borderColor = "red";
        input.email.style.borderStyle = "solid";
        input.email.style.borderWidth = "1px";
        if (document.getElementById(elemId) == null) { //si le message n'était pas encore apparu
            const lieuDiv = document.getElementById("section");
            const firstChild = lieuDiv.firstChild;
            lieuDiv.insertBefore(div, firstChild);
            div.appendChild(elem);
        }
    } else if (!boolMdp && bool) { //si le mot de passe n'est pas le meme
        const div = document.createElement('div');//on crée la boite html pour le paragraphe
        const elem = document.createElement('p'); //on crée un paragraphe
        elem.id = elemId; //on lui donne un id
        elem.textContent = "Vos deux mots de passes ne sont pas identiques !"; //on lui donne le texte
        input.mdp2.style.borderColor = "red";
        input.mdp2.style.borderStyle = "solid";
        input.mdp2.style.borderWidth = "1px";
        if (document.getElementById(elemId) == null) { //si le message n'était pas encore apparu
            const lieuDiv = document.getElementById("section");
            const firstChild = lieuDiv.firstChild;
            lieuDiv.insertBefore(div, firstChild);
            div.appendChild(elem);
        }
    } else if (input.condition == false) { //si on a pas coché les conditions
        const div = document.createElement('div');//on crée la boite html pour le paragraphe
        const elem = document.createElement('p'); //on crée un paragraphe
        elem.id = elemId; //on lui donne un id
        elem.textContent = "Vous devez accepter nos conditions d'utilisation"; //on lui donne le texte
        const cond = document.getElementById("cguText");
        cond.style.color = "red";
        
        if (document.getElementById(elemId) == null) { //si le message n'était pas encore apparu
            const lieuDiv = document.getElementById("section");
            const firstChild = lieuDiv.firstChild;
            lieuDiv.insertBefore(div, firstChild);
            div.appendChild(elem);
        }
    }
    
}
