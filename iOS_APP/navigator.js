import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StatusBar } from 'react-native';

import WelcomeScreen from './screens/Welcome.js';
import SignupScreen from './screens/SignUP.js';
import LoginScreen from './screens/LogIN.js';
import HomeScreen from './screens/HomeScreen.js';

const Stack = createNativeStackNavigator();

function AppNavigator() {
    
  return (
      
    <NavigationContainer>
    {/* <StatusBar
        animated={true}
        backgroundColor="#61dafb"
         /> */}
    
      <Stack.Navigator screenOptions={{headerShown: false, statusBarStyle:'dark',}} initialRouteName="Welcome">
        
        <Stack.Screen name="Welcome" component={WelcomeScreen} />
        <Stack.Screen name="LogIN" component={LoginScreen} />
        <Stack.Screen name="SignUP" component={SignupScreen} />
        <Stack.Screen name="Home" component={HomeScreen} />

      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default AppNavigator;
