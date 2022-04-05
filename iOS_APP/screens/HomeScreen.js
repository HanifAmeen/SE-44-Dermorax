import { StyleSheet, Text, View, TouchableOpacity, Animated, Dimensions } from 'react-native'
import React from 'react'
import { LinearGradient } from 'expo-linear-gradient'
import 'react-native-gesture-handler';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { MaterialIcons } from '@expo/vector-icons';
import { Feather } from '@expo/vector-icons';


import NewsScreen from './NewsScreen';
import PreviousPredictionsScreen from './PreviousPredictionsScreen';
import MoleMapScreen from './MoleMapScreen';
import NewPredictionScreen from './NewPredictionScreen';
import { useRef } from 'react';

const Tab = createBottomTabNavigator();


const HomeScreen = ({navigation}) => {

    const tabOffsetValue = useRef (new Animated.Value(0)).current;

    return (

    <NavigationContainer independent={true}>

        <Tab.Navigator 
            screenOptions={({ route }) => ({
                headerShown: false,
                tabBarShowLabel:false,
                tabBarStyle: {
                    height: 52,
                    paddingHorizontal: 5,
                    borderTopWidth:0,
                    backgroundColor: 'rgba(36,34,42,1)',
                    position: 'absolute',
                    bottom:16,
                    marginHorizontal:13,
                    borderRadius:18,
                    shadowColor:'#000',
                    
                },
            })}>
            
            <Tab.Screen name={"Home"} component={Home} options={{
                tabBarIcon:({focused}) => (
                    <View>
                        <Feather name="home" size={25} color={focused? '#542B75' : '#59505c'} />
                    </View>
                )
            }}  listeners={({navigation,route}) => ({
                //on press update...
                tabPress: e=>{
                    Animated.spring(tabOffsetValue,{
                        toValue:getWidth()*0.02,
                        useNativeDriver: true
                    }).start();
                }
            })}></Tab.Screen>
            <Tab.Screen name={"News"} component={NewsScreen} options={{
                tabBarIcon:({focused}) => (
                    <View>
                        <Feather name="layers" size={25} color={focused? '#542B75' : '#59505c'} />
                    </View>
                )
            }}  listeners={({navigation,route}) => ({
                //on press update...
                tabPress: e=>{
                    Animated.spring(tabOffsetValue,{
                        toValue:getWidth()*1.43,
                        useNativeDriver: true
                    }).start();
                }
            })}></Tab.Screen>
            
             <Tab.Screen name={"NewPrediction"} component={NewPredictionScreen} options={{
                
                tabBarIcon:({focused}) => (
                    
                    <View style={{
                        width:55,
                        height:54,
                        borderRadius:50,
                        backgroundColor:'#542B75',
                        alignItems:'center',
                        justifyContent:'center',
                        marginBottom:35
                    }}>
                        <Feather name="plus" size={27} color={focused? '#B681E1' : '#101010'} />
                    </View>
                    
                )
            }} listeners={({navigation,route}) => ({
                //on press update...
                tabPress: e=>{
                    Animated.spring(tabOffsetValue,{
                        toValue:getWidth()*2.85,
                        useNativeDriver: true
                    }).start();
                }
            })}></Tab.Screen>
           
            <Tab.Screen name={"Predictions"} component={PreviousPredictionsScreen} options={{
                tabBarIcon:({focused}) => (
                    <View>
                        <Feather name="user-check" size={25} color={focused? '#542B75' : '#59505c'} />
                    </View>
                )
            }}  listeners={({navigation,route}) => ({
                //on press update...
                tabPress: e=>{
                    Animated.spring(tabOffsetValue,{
                        toValue:getWidth()*4.22,
                        useNativeDriver: true
                    }).start();
                }
            })}></Tab.Screen>
            <Tab.Screen name={"MoleMap"} component={MoleMapScreen} options={{
                tabBarIcon:({focused}) => (
                    <View>
                        <MaterialIcons name="location-searching" size={27} color={focused? '#542B75' : '#59505c'} />  
                    </View>
                )
            }} listeners={({navigation,route}) => ({
                //on press update...
                tabPress: e=>{
                    Animated.spring(tabOffsetValue,{
                        toValue:getWidth()*5.71,
                        useNativeDriver: true
                    }).start();
                }
            })}></Tab.Screen>

        </Tab.Navigator>

        <Animated.View style={{
            width: getWidth(),
            height: 3,
            backgroundColor:'#542B75',
            position: 'absolute',
            borderRadius:100,
            bottom: 68,
            left:13+15,
            transform: [{translateX :tabOffsetValue}]

        }}></Animated.View>


    </NavigationContainer>
  )
}

export default HomeScreen

function getWidth() {
    let width = Dimensions.get('window').width
    width = (width-36)/5
    width = width*0.7

    return width
}


function Home() {
    return (
        <View style={styles.container}>

            <LinearGradient colors ={['#241332','#141414']} style={styles.userContainer}>
    
                <Text style={styles.userName}>Lucas Yayden</Text>
    
            </LinearGradient>
          
    
    
        </View>
    );
  }


const styles = StyleSheet.create({

    container: {
        flex:1,
        height:'100%',
        width:'100%',
        backgroundColor: '#241332',
        alignItems: 'center',
    },
    userContainer:{
        width:'100%',
        height:'38%',
        alignItems: 'center',
        justifyContent: 'center',

    },
    userName: {
        marginTop:'45%',
        color: 'white',
        fontSize:35,
        fontWeight: 'bold',
    }

})