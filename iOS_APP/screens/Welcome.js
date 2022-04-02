

import React from 'react'
import { StatusBar } from 'react-native';
import { StyleSheet, Text, View, Image, ImageBackground, TouchableOpacity} from 'react-native';

import Logo from '../assets/logo.png'
import signinup_bg from '../assets/signinup_bg.png'


const Welcome = ({navigation}) => {

  return (
    
    <View style={styles.container}>

      {/* <StatusBar
        animated={true}
        backgroundColor="#61dafb"
         /> */}
     
      <View style={styles.logoContainer}>
        <Text style={styles.mainTitle}>DERMORAX</Text>
        <Image source={Logo}style={styles.logo} />
        <Text style={styles.welcome}>Welcome</Text>
      </View>
      
      <View style={styles.buttonContainer}>
        <ImageBackground source={signinup_bg}style={styles.signinup} >

          <TouchableOpacity style={styles.login_btn} onPress={() => navigation.navigate("LogIN")}>
            <Text style={styles.logintext}>LOGIN</Text>
          </TouchableOpacity>

          <TouchableOpacity style={styles.signup_btn} onPress={() => navigation.navigate("SignUP")}>
            <Text style={styles.signuptext}>SIGN UP</Text>
          </TouchableOpacity>
        
        </ImageBackground>
      </View>

      
    </View>
    
  )
}



export default Welcome

const styles = StyleSheet.create({
  container: {
    flex:1,
    height:'100%',
    width:'100%',
    backgroundColor: '#241332',
    alignItems: 'center',
    justifyContent: 'center'
  },
  mainTitle: {
    color:'#BCBCBC',
    fontSize: 43,
    marginTop:'0%',
    fontWeight:'700',
    
  },
  logo: {
    marginTop: '10%',
    width:280, 
    height:230,
  },
  logoContainer: {
    alignItems: 'center',

  },
  welcome: {
    color:'#b1b1b1',
    fontSize: 30,
    marginTop:'5%',
    marginBottom:'5%',
   
  },
  buttonContainer: {
    
    width: '100%',
    height: '15%',
    marginTop: '0%',

  },
  signinup: {
    marginTop: '11%',
    width: '100%',
    height: '120%',
  },
  login_btn: {
    alignItems: 'center',
    width: '100%',
    height: 60,
    padding: 16
  },
  logintext: {
    fontSize: 17,
    fontWeight:'700',
    color:'#3d3d3d',
  },
  signup_btn: {
    alignItems: 'center',
    width: '100%',
    height: 60,
    padding: 16
  },
  signuptext: {
    fontSize: 17,
    fontWeight:'700',
    color:'#8f8f8f',
  },
  
  
});
