/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, TouchableOpacity} from 'react-native';
import { changeIcon } from 'react-native-change-icon';

export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <TouchableOpacity onPress={() => changeIcon('checked', 'cancel')}>
          <Text style={styles.button}>SWITCH TO CHECKED ICON</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => changeIcon('cancel', 'checked')}>
          <Text style={styles.button}>SWITCH TO CANCEL ICON</Text>
        </TouchableOpacity>
      </View>
    );
  }
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
