import React from 'react';
import { AppRegistry, Text, View } from 'react-native';
import { name as appName } from './app.json';

console.log('common bundle message');
AppRegistry.registerComponent(appName, () => {
  return () => React.createElement(View, null, React.createElement(Text, null, "Common Bundle"));
});
