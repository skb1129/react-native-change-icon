import React, {useEffect, useState} from 'react';
import {StyleSheet, View, Text, TouchableOpacity} from 'react-native';
import {changeIcon, getIcon} from 'react-native-change-icon';

export default function App() {
  const [currentIconName, setCurrentIconName] = useState('');

  useEffect(() => {
    getIcon().then(name => setCurrentIconName(name));
  }, []);

  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={() =>
          changeIcon('checked')
            .then(setCurrentIconName)
            .catch(e => console.log(e.message))
        }>
        <Text style={styles.text}>SWITCH TO CHECKED ICON</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={() =>
          changeIcon('cancel')
            .then(setCurrentIconName)
            .catch(e => console.log(e.message))
        }>
        <Text style={styles.text}>SWITCH TO CANCEL ICON</Text>
      </TouchableOpacity>
      <Text style={styles.text}>{'Icon name: ' + currentIconName}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-around',
    alignItems: 'center',
    backgroundColor: '#000000',
  },
  text: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
    color: '#ffffff',
  },
});
