import {
  Box,
  Heading,
  Image,
  ScrollView,
  HStack,
  View,
  Spacer,
  Text,
} from "native-base";
import React, { useState } from "react";
import Colors from "../color";
import Rating from "../Components/Rating";
import NumericInput from "react-native-numeric-input";
import Buttone from "../Components/Buttone";
import Review from "../Components/Review";
import { useNavigation } from "@react-navigation/native";

function SingleProductScreen({ route }) {
  const [value, setValue] = useState(0);
  const navigation = useNavigation();
  const product = route.params;
  return (
    <Box safeArea flex={1} bg={Colors.white}>
      <ScrollView px={5} showsVerticalScrollIndicator={false}>
        <Image
          source={{ uri: product.image }}
          alt="Image"
          w="full"
          h={300}
          resizeMode="contain"
        />
        <Heading bold fontSize={15} mb={5} lineHeight={25}>
        {product.description}
        </Heading>
        <Text lineHeight={24} fontSize={14}>
          {product.description1}
        </Text>
        <Heading bold fontSize={15} mb={5} lineHeight={70}>
        {product.description2}
        </Heading>
        <Text lineHeight={24} fontSize={14}>
          {product.description3}
        </Text>
        <Heading bold fontSize={15} mb={5} lineHeight={50}>
        {product.description4}
        </Heading>
        <Text lineHeight={24} fontSize={14}>
          {product.description5}
        </Text>


      </ScrollView>
    </Box>
  );
}

export default SingleProductScreen;
