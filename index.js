/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

console.log('index bundle message', appName);
AppRegistry.registerComponent(appName, () => App);
