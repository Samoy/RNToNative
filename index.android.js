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

import KenBurnsView from './KenBurnsView';

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
                    <Text>Press here to open contacts!</Text>
                </TouchableOpacity>
                <KenBurnsView style={{width:200,height:200}}/>
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
    interface: {
        width: 200,
        height: 200
    }
});

AppRegistry.registerComponent('RNToNative', () => RNToNative);
