import * as React from 'react';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { changeIcon, getIcon } from 'react-native-change-icon';

export default function App() {
  const [currentIconName, setCurrentIconName] = React.useState('')
  
  return (
    <View style={styles.container}>
      <TouchableOpacity onPress={() => {
        changeIcon('checked')
        getIcon().then(name=>setCurrentIconName(name))
      }}>
        <Text style={styles.button}>SWITCH TO CHECKED ICON</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => {
        changeIcon('cancel')
        getIcon().then(name=>setCurrentIconName(name))
      }}>
        <Text style={styles.button}>SWITCH TO CANCEL ICON</Text>
      </TouchableOpacity>
        <Text>{'Icon name: ' + currentIconName}</Text>
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
  }
});
