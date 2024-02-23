import { AppRegistry, Platform } from 'react-native';
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { NativeBaseProvider, StatusBar } from "native-base";
import LoginScreen from "./src/Screens/LoginScreen";
import RegisterScreen from "./src/Screens/RegisterScreen";
import BottomNav from "./src/Navigations/BottomNav";
import { LogBox } from "react-native";
import { url2 } from "./constants"
LogBox.ignoreAllLogs(true);

const Stack = createNativeStackNavigator();

export default function App() {

  // données à envoyer
  const data = {
    a: 5,
    b: 2
  };

  // début de la requête
  const xhr = new XMLHttpRequest();
  xhr.open('POST', url2, true);
  xhr.setRequestHeader("Content-type", "application/json");
  xhr.onload = () => {
    if (xhr.status == 200) {
      //fonction à exécuter après avoir reçu une réponse
      const response = JSON.parse(xhr.responseText);
      console.log(response.value);
    } else {
      //fonction à exécuter en cas d'erreur
      console.error('Error sending POST request');
    }
  };
  xhr.send(JSON.stringify(data));

  //geolocation



  return (

    <NativeBaseProvider>
      <NavigationContainer>
        <StatusBar hidden={true} />
        <Stack.Navigator
          initialRouteName="Login"
          screenOptions={{
            headerShown: false,
          }}
        >
          <Stack.Screen name="Login" component={LoginScreen} />
          <Stack.Screen name="Register" component={RegisterScreen} />
          <Stack.Screen name="Bottom" component={BottomNav} />
        </Stack.Navigator>
      </NavigationContainer>
    </NativeBaseProvider>
  );
}

AppRegistry.registerComponent('main', () => App);
