import * as React from 'react';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { changeIcon } from 'react-native-change-icon';

export default function App() {
  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => changeIcon('checked')}>
        <Text style={styles.button}>SWITCH TO CHECKED ICON</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => changeIcon('cancel')}>
        <Text style={styles.button}>SWITCH TO CANCEL ICON</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-around',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  button: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
});
