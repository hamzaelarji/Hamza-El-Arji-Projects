import React, { useState } from "react";
import { StyleSheet, View, Text, FlatList } from "react-native";
import { Box, HStack,  Input, List, ListItem, VStack, Pressable } from "native-base";
import Autocomplete from "react-native-autocomplete-input";
import Colors from "../color";
import products from "../data/Products";
import { Ionicons } from "@expo/vector-icons";
import { FontAwesome5 } from "@expo/vector-icons";
import { useNavigation } from "@react-navigation/native";




function HomeSearch() {
  const [searchValue, setSearchValue] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);

  const searchProduct = (value) => {
    setSearchValue(value);
    const filteredProducts = products.filter((product) =>
      product.name.toLowerCase().includes(value.toLowerCase())
    );
    setFilteredProducts(filteredProducts);
  };

  const onSelectProduct = (product) => {
    setSearchValue(product.name);
    setFilteredProducts([]);
  };

  const renderProduct = ({ item }) => (
    <ListItem onPress={() => onSelectProduct(item)}>
      <Text>{item.name}</Text>
    </ListItem>
  );
  const navigation = useNavigation();
  return (
    <HStack
      space={3}
      w="full"
      px={6}
      bg={Colors.main}
      py={4}
      alignItems="center"
      safeAreaTop
    >
      <Input
        placeholder="conseils, recommendations...."
        w="100%"
        bg={Colors.white}
        type="search"
        variant="filled"
        h={12}
        borderWidth={0}
        _focus={{
          bg: Colors.white,
        }}
      />
     
    </HStack>
  );
}

export default HomeSearch;





/**import React, { useState } from "react";
import { StyleSheet, View, Text, FlatList } from "react-native";
import { Input, List, ListItem } from "native-base";
import Autocomplete from "react-native-autocomplete-input";
import Colors from "../color";
import products from "./products";

function HomeSearch() {
  const [searchValue, setSearchValue] = useState("");
  const [filteredProducts, setFilteredProducts] = useState([]);

  const searchProduct = (value) => {
    setSearchValue(value);
    const filteredProducts = products.filter((product) =>
      product.name.toLowerCase().includes(value.toLowerCase())
    );
    setFilteredProducts(filteredProducts);
  };

  const onSelectProduct = (product) => {
    setSearchValue(product.name);
    setFilteredProducts([]);
  };

  const renderProduct = ({ item }) => (
    <ListItem onPress={() => onSelectProduct(item)}>
      <Text>{item.name}</Text>
    </ListItem>
  );

  return (
    <View style={styles.container}>
      <Autocomplete
        data={filteredProducts}
        defaultValue={searchValue}
        onChangeText={searchProduct}
        placeholder="conseils, lifestyle...."
        inputContainerStyle={styles.inputContainer}
        listStyle={styles.list}
        renderItem={renderProduct}
        keyExtractor={(item) => item.id}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: Colors.main,
    paddingTop: 50,
    paddingHorizontal: 10,
  },
  inputContainer: {
    borderWidth: 0,
    backgroundColor: Colors.white,
    borderRadius: 10,
    height: 50,
    paddingHorizontal: 10,
  },
  list: {
    borderWidth: 0,
    backgroundColor: Colors.white,
    borderRadius: 10,
    marginTop: 10,
    maxHeight: 150,
    paddingHorizontal: 10,
  },
});

export default HomeSearch; */