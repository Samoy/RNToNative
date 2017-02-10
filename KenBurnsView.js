/**
 * Created by Samoy on 2017/2/10.
 */
import { PropTypes } from 'react';
import { requireNativeComponent, View } from 'react-native';

let iface = {
    name: 'KenBurnsView',
    propTypes: {
        imgSource: PropTypes.string,
        ...View.propTypes
    }
};
module.exports = requireNativeComponent('KenBurnsView', iface);