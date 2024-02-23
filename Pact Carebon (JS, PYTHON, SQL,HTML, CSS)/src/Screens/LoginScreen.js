import {
  Box,
  Button,
  Heading,
  Image,
  Input,
  Pressable,
  Text,
  VStack,
} from "native-base";
import { React, useState } from "react";
import Colors from "../color";
import { MaterialIcons, Ionicons } from "@expo/vector-icons";
import Location from "../Location";
import { url, url2 } from "../../constants";

function LoginScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  /*function handleSignIn() {
    signIn(username, password);
  }*/

  const handleSignIn = () => {
    const xhr = new XMLHttpRequest();
    const data = { mail: username, password: password };
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onload = () => {
      if (xhr.status == 200) {
        //fonction à exécuter après avoir reçu une réponse
        const response = JSON.parse(xhr.responseText);
        console.log(response);
        if (response.authorized) {
          //sessionStorage.setItem("id", response.id);
          //document.location.href = "profile.html";
          navigation.navigate("Bottom");
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

  return (
    <Box flex={1} bg={Colors.black}>
      <Location />
      <Image
        flex={1}
        alt="Logo"
        resizeMode="cover"
        size="lg"
        w="full"
        source={require("../../assets/cover.png")}
      />
      <Box
        w="full"
        h="full"
        position="absolute"
        top="0"
        px="6"
        justifyContent="center"
      >
        <Heading>LOGIN</Heading>
        <VStack space={5} pt="6">
          {/* EMAIL */}
          <Input
            InputLeftElement={
              <MaterialIcons name="email" size={20} color={Colors.main} />
            }
            variant="underlined"
            placeholder="user@gmail.com"
            w="70%"
            pl={2}
            type="text"
            onChangeText={setUsername}
            value={username}
            color={Colors.main}
            borderBottomColor={Colors.underline}
          />
          {/* PASSWORD */}
          <Input
            InputLeftElement={
              <Ionicons name="eye" size={20} color={Colors.main} />
            }
            variant="underlined"
            placeholder="*********"
            w="70%"
            type="password"
            onChangeText={setPassword}
            value={password}
            pl={2}
            color={Colors.main}
            borderBottomColor={Colors.underline}
          />
        </VStack>
        <Button onclick
          _pressed={{
            bg: Colors.main,
          }}
          my={30}
          w="40%"
          rounded={50}
          bg={Colors.main}
          onPress={handleSignIn}
        >
          LOGIN
        </Button>

        <Pressable mt={4} onPress={() => navigation.navigate("Register")}>
          <Text color={Colors.deepestGray}>SIGN UP</Text>
        </Pressable>
      </Box>
    </Box>
  );
}

export default LoginScreen;
