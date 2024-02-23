import React, { useState, useEffect } from 'react';
import { Platform, Text, View, StyleSheet } from 'react-native';

import * as Location from 'expo-location';
import { baseUrl } from '../constants';





export default function CompoLocation() {
    const [location, setLocation] = useState(null);
    const [errorMsg, setErrorMsg] = useState(null);
    const [errorMsgFetch, setErrorMsgFecth] = useState("");

    const GetLocalisation = async () => {
        const { status } = await Location.requestForegroundPermissionsAsync();
        if (status !== 'granted') {
            setErrorMsg('Permission to access location was denied');
            return;
        }
        const location = await Location.getCurrentPositionAsync({});
        setLocation(location);
        const toStr = JSON.stringify(location);

        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://pact-6.r2.enst.fr/flask/localisation', true);
        xhr.setRequestHeader("Content-type", "application/json");
        xhr.onload = () => {
            if (xhr.status == 200) {
                //fonction à exécuter après avoir reçu une réponse
            } else {
                //fonction à exécuter en cas d'erreur
            }
        };
        xhr.send(toStr);



        /*const user = "1"; // tu met l'id ici 
        const correctUrl = `${baseUrl}/${user}/localisation`
        console.log(correctUrl);
        fetch(correctUrl, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: toStr
        }).then((resp) => {
            console.log(resp.status)
            if (resp.status === 200) {
                setErrorMsgFecth("localisation envoyé mon reuf");

            }
            setErrorMsgFecth("chelou le code de retour : " + resp.status);
        }).catch(() => {
            setErrorMsgFecth("y a un beug dans la requete mek");
        })*/
    }

    useEffect(() => {
        GetLocalisation();
    }, []);

    let text = 'Waiting..';
    if (errorMsg) {
        text = errorMsg;
    }

    return (
        <View>
            <Text style={{ color: "white" }}>{errorMsgFetch}</Text>
        </View>
    );
}