import React from "react";
import Colors from "../color";
import { MaterialIcons, Ionicons } from "@expo/vector-icons";
import Location from "../Location";
import Login from "../data/scriptLoginApplication";

function signIn(mail, password) {
  const xhr = new XMLHttpRequest();
  const data = { mail: mail, password: password };
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.onload = () => {
    if (xhr.status == 200) {
      //fonction à exécuter après avoir reçu une réponse
      const response = JSON.parse(xhr.responseText);
      if (response.authorized) {
        sessionStorage.setItem("id", response.id);
        document.location.href = "profile.html";
      } else {
        // message d'erreur
        console.log("pas le bon mdp ou identifiant")
      }
    } else {
      console.log("Error sending POST request");
    }
  };
  xhr.send(JSON.stringify(data));
}

function signUp(name, mail, password) {
  const url = "http://pact-6.r2.enst.fr/flask/createAccount";

  const xhr = new XMLHttpRequest();
  const data = { username: name, mail: mail, password: password };
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.onload = () => {
    if (xhr.status == 200) {
      //fonction à exécuter après avoir reçu une réponse
      const response = JSON.parse(xhr.responseText);
      if (response.authorized) {
        sessionStorage.setItem("id", response.id);
        document.location.href = "profile.html";
      } else {
        const popup = document.getElementById("popup_ca");

        popup.classList.remove("open-popup");
      }
    } else {
      //fonction à exécuter en cas d'erreur
      console.error("Error sending POST request");
    }
  };
  xhr.send(JSON.stringify(data));
}

