/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    NativeModules,
    TouchableOpacity,
    DeviceEventEmitter,
    ToastAndroid
} from 'react-native';

let ExampleInterface = NativeModules.ExampleInterface;

export default class RNToNative extends Component {


    componentWillMount() {
        DeviceEventEmitter.addListener('AndroidToRNMessage', this.handleAndroidMessage);
    }

    handleAndroidMessage = (message) => {
        ToastAndroid.show(message, ToastAndroid.SHORT);
    };

    componentWillUnmount() {
        DeviceEventEmitter.removeListener('AndroidToRNMessage', this.handleAndroidMessage);
    }


    render() {
        return (
            <View style={styles.container}>
                <TouchableOpacity onPress={()=>{
                    ExampleInterface.HandleMessage('RN to Android');
                }}>
                    <Text>Welcome to React Native!</Text>
                </TouchableOpacity>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

AppRegistry.registerComponent('RNToNative', () => RNToNative);
