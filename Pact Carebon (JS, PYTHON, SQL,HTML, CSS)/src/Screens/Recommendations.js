import React, { useEffect, useState } from 'react';
import { View, StyleSheet, Text, Button, Modal, TouchableOpacity } from "react-native";
import Svg, { G, Circle } from "react-native-svg";
import * as Location from 'expo-location';

var distance = 0;
var position = null;

// Fonction pour convertir des degrés en radians
function toRadians(degrees) {
  return degrees * Math.PI / 180;
}

// Fonction pour calculer la distance entre deux positions géographiques
function calculateDistance(lat1, lon1, lat2, lon2) {
  const earthRadius = 6371; // Rayon de la Terre en kilomètres

  // Convertir les coordonnées degrés décimaux en radians
  const lat1Rad = toRadians(lat1);
  const lon1Rad = toRadians(lon1);
  const lat2Rad = toRadians(lat2);
  const lon2Rad = toRadians(lon2);

  // Calculer la différence entre les longitudes et latitudes
  const deltaLat = lat2Rad - lat1Rad;
  const deltaLon = lon2Rad - lon1Rad;

  // Calculer la distance en utilisant la formule de la distance orthodromique
  const a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
    Math.cos(lat1Rad) * Math.cos(lat2Rad) *
    Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const distance = earthRadius * c;

  return distance;
}

const App = () => {
  const radius = 70;
  const circleCircumference = 2 * Math.PI * radius;
  let var1;

  const [foyerStrokeDashoffset, setFoyer] = useState(0);
  const [achatsStrokeDashoffset, setAchat] = useState(0);
  const [regularStrokeDashoffset, setTransport] = useState(0);
  const [foyerAngle, setFoyerAngle] = useState(0);
  const [achatsAngle, setAchatsAngle] = useState(0);
  const [regularAngle, setRegularAngle] = useState(0);
  const [total, setTotal] = useState(0);
  const [buttonText, setButtonText] = useState("Démarrer le trajet");

  let backgroundTimerId = null;
  var location = null;
  var distance = 0;
  let isTravelling = false;

  const handleStart = () => {

    if (!isTravelling) {
      isTravelling = true;
      setButtonText("Arrêter le trajet");
      distance = 0;
      const startBackgroundTimer = async () => {
        const newlocation = await Location.getCurrentPositionAsync({});
        if (newlocation) {
          if (location != null) {
            distance += calculateDistance(location.coords.latitude, location.coords.longitude, newlocation.coords.latitude, newlocation.coords.longitude);
          }
          location = newlocation;
          //console.log("coucou");
        }
        else { console.log("j'ai pas trouvé") }

        // Redémarrer le minuteur
        backgroundTimerId = setTimeout(startBackgroundTimer, 10000);
      };

      backgroundTimerId = setTimeout(startBackgroundTimer, 10000);
    } else {
      clearTimeout(backgroundTimerId);
      isTravelling = false;
      setButtonText("Démarrer le trajet");
      //console.log(distance);
      data = { distance: distance, moyen_transport: selectedOption.id, date: new Date().toJSON().slice(0, 10) }
      const xhr = new XMLHttpRequest();
      xhr.open('POST', 'https://pact-6.r2.enst.fr/flask/deplacement', true);
      xhr.setRequestHeader("Content-type", "application/json");
      xhr.onload = () => {
        if (xhr.status == 200) {
        } else {
        }
      };
      xhr.send(JSON.stringify(data));
    }


  }

  useEffect(() => {
    // Effectuez votre requête XHR ici
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'https://pact-6.r2.enst.fr/flask/getPie');
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var1 = JSON.parse(xhr.responseText)
        const foyer = var1.home;
        const achats = var1.shopping;
        const transports = var1.transport;

        const total = foyer + achats + transports;

        const groceriesPercentage = (foyer / total) * 100;
        const billsPercentage = (achats / total) * 100;
        const regularPercentage = (transports / total) * 100;

        const foyerStrokeDashoffset =
          circleCircumference - (circleCircumference * groceriesPercentage) / 100;
        const achatsStrokeDashoffset =
          circleCircumference - (circleCircumference * billsPercentage) / 100;
        const regularStrokeDashoffset =
          circleCircumference - (circleCircumference * regularPercentage) / 100;

        const foyerAngle = (foyer / total) * 360;
        const achatsAngle = (achats / total) * 360;
        const regularAngle = foyerAngle + achatsAngle;

        setAchatsAngle(achatsAngle);
        setFoyerAngle(foyerAngle);
        setRegularAngle(regularAngle);
        setAchat(achatsStrokeDashoffset);
        setFoyer(foyerStrokeDashoffset);
        setTransport(regularStrokeDashoffset);
        setTotal(total);

      }
    };
    xhr.send();
  }, []);

  const legendData = [
    { label: 'Foyer', color: '#05E905' },
    { label: 'Achats', color: '#6C8EAD' },
    { label: 'Transports', color: '#D5F406' }
  ];
  const [modalVisible, setModalVisible] = useState(false);
  const [selectedOption, setSelectedOption] = useState(null);

  const options = [
    { id: 0, label: 'vélo ou marche' },
    { id: 1, label: 'métro' },
    { id: 2, label: 'vélo (ou trotinette) à assitance électrique' },
    { id: 3, label: 'scooter ou moto légère' },
    { id: 4, label: 'voiture (moteur électrique)' },
    { id: 5, label: 'bus' },
    { id: 6, label: 'voiture (moteur thermique)' },
  ];

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
    setModalVisible(false);
  };


  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', paddingHorizontal: 20 }}>
      <View style={[styles.graphWrapper, { marginBottom: 20 }]}>
        <Button
          title={buttonText}
          onPress={handleStart}
        />
        <View style={{ marginTop: 10 }}>
          <Button title="Choisir une option" onPress={() => setModalVisible(true)} />
        </View>

        <Modal visible={modalVisible} animationType="slide" transparent>
          <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center', backgroundColor: 'rgba(0, 0, 0, 0.5)' }}>
            <View style={{ backgroundColor: 'white', borderRadius: 10 }}>
              {options.map((option) => (
                <TouchableOpacity key={option.id} onPress={() => handleOptionSelect(option)}>
                  <Text style={{ fontSize: 16, marginBottom: 5 }}>{option.label}</Text>
                </TouchableOpacity>
              ))}

              <TouchableOpacity onPress={() => setModalVisible(false)}>
                <Text style={{ fontSize: 16, color: 'red', marginBottom: 5 }}>Annuler</Text>
              </TouchableOpacity>
            </View>
          </View>
        </Modal>

        {selectedOption && (
          <Text>Option sélectionnée : {selectedOption.label}</Text>
        )}

        <View style={{ marginTop: 20 }}>
          <Svg height="160" width="160" viewBox="0 0 180 180">
            <G rotation={-90} originX="90" originY="90">
              {total === 0 ? (
                <Circle
                  cx="50%"
                  cy="50%"
                  r={radius}
                  stroke="#F1F6F9"
                  fill="transparent"
                  strokeWidth="40"
                />
              ) : (
                <>
                  <Circle
                    cx="50%"
                    cy="50%"
                    r={radius}
                    stroke="#05E905"
                    fill="transparent"
                    strokeWidth="40"
                    strokeDasharray={circleCircumference}
                    strokeDashoffset={foyerStrokeDashoffset}
                    rotation={0}
                    originX="90"
                    originY="90"
                    strokeLinecap="round"
                  />
                  <Circle
                    cx="50%"
                    cy="50%"
                    r={radius}
                    stroke="#6C8EAD"
                    fill="transparent"
                    strokeWidth="40"
                    strokeDasharray={circleCircumference}
                    strokeDashoffset={achatsStrokeDashoffset}
                    rotation={foyerAngle}
                    originX="90"
                    originY="90"
                    strokeLinecap="round"
                  />
                  <Circle
                    cx="50%"
                    cy="50%"
                    r={radius}
                    stroke="#D5F406"
                    fill="transparent"
                    strokeWidth="40"
                    strokeDasharray={circleCircumference}
                    strokeDashoffset={regularStrokeDashoffset}
                    rotation={regularAngle}
                    originX="90"
                    originY="90"
                    strokeLinecap="round"
                  />
                </>
              )
              }
            </G>
          </Svg>
        </View>
        <Text style={styles.label}>{total.toFixed(2)} KgCo2</Text>
      </View>
      <View>
        {legendData.map((item, index) => (
          <View key={index} style={{ flexDirection: 'row', alignItems: 'center' }}>
            <View style={{ width: 10, height: 10, backgroundColor: item.color, marginRight: 10 }} />
            <Text>{item.label}</Text>
          </View>
        ))}
      </View>
    </View>
  );
};

export default App;


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  graphWrapper: {
    alignItems: "center",
    justifyContent: "center",
  },
  label: {
    position: "absolute",
    textAlign: "center",
    fontWeight: "700",
    fontSize: 24,
  },
});