import {
  Box,
  Button,
  Heading,
  Image,
  Input,
  Pressable,
  Text,
  View,
  VStack,
} from "native-base";
import { React, useState } from "react";
import { MaterialIcons, FontAwesome, Ionicons } from "@expo/vector-icons";
import Colors from "../color";
import Login from "../data/scriptLoginApplication";
import { url3 } from "../../constants";

function RegisterScreen({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');

  const handleSignUp = () => {
    const xhr = new XMLHttpRequest();
    const data = { mail: email, password: password, username: username };
    console.log(data);
    xhr.open("POST", url3, true);
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
        <Heading>SIGN UP</Heading>
        <VStack space={5} pt="6">
          {/* USERNAME*/}
          <Input
            InputLeftElement={
              <FontAwesome name="user" size={20} color={Colors.main} />
            }
            variant="underlined"
            placeholder="John Doe"
            w="70%"
            pl={2}
            onChangeText={setUsername}
            type="text"
            value={username}
            color={Colors.main}
            borderBottomColor={Colors.underline}
          />
          {/* EMAIL */}
          <Input
            InputLeftElement={
              <MaterialIcons name="email" size={20} color={Colors.main} />
            }
            variant="underlined"
            placeholder="user@gmail.com"
            w="70%"
            pl={2}
            onChangeText={setEmail}
            type="text"
            value={email}
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
            onChangeText={setPassword}
            type="password"
            value={password}
            pl={2}
            color={Colors.main}
            borderBottomColor={Colors.underline}
          />
        </VStack>
        <Button
          _pressed={{
            bg: Colors.main,
          }}
          my={30}
          w="40%"
          rounded={50}
          bg={Colors.main}
          onPress={handleSignUp}
        //onPress={() => navigation.navigate("Bottom")}
        >
          SIGN UP
        </Button>
        <Pressable mt={4} onPress={() => navigation.navigate("Login")}>
          <Text color={Colors.deepestGray}>LOGIN</Text>
        </Pressable>
      </Box>
    </Box>
  );
}

export default RegisterScreen;
