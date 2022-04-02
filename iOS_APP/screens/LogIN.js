
import React, {useState} from 'react'
import { StatusBar } from 'react-native';
import { StyleSheet, Text, View, Button, TouchableOpacity, TextInput} from 'react-native';
import {MaterialCommunityIcons} from '@expo/vector-icons'
import {MaterialIcons} from '@expo/vector-icons'

import ContinueBtn from '../buttons/continueBtn'
import RoundColorBtn from '../buttons/roundcolorButton'
import RoundBtn from '../buttons/roundButton'

const LogIN = ({navigation}) => {

  const [name, setName] = useState('');
  const [password, setPassword] = useState('');
  const [show,setShow] = useState(false);

  return (
    
    <View style={styles.container}>
     
      <View style={styles.titleContainer}>
        <Text style={styles.mainTitle}>DERMORAX</Text>
      </View>
      
      <View style={styles.authCotainer}>

        <View style={styles.buttonRow}>

          <View style={styles.roundBtn}>
            <RoundColorBtn text='LOGIN'/>
          </View>

          <View style={styles.roundBtn}>
            <RoundBtn text='SIGNUP' onPress={() => navigation.navigate('SignUP')}/>
          </View>
        
        </View>

        <View style={styles.linearInputArea}>
          <MaterialIcons
            name={'person-outline'}
            size={20}
            color= '#BCBCBC'
            style={styles.inputIcon}
          />

          <TextInput style={styles.textInput} 
                      placeholder='Username'
                      placeholderTextColor='#cbb6cc'
                      onChangeText={(val) => setName(val)}
                      />

        </View>
        
        <View style={styles.linearInputArea}>
          <MaterialIcons
            name={'lock-outline'}
            size={20}
            color= '#BCBCBC'
            style={styles.inputIcon}
          />
          <TextInput style={[styles.textInput,{width:'91%'}]} 
                      placeholder='Password'
                      placeholderTextColor='#cbb6cc'
                      secureTextEntry={!show}
                      onChangeText={(val) => setPassword(val)}
                      />
          
          <MaterialCommunityIcons
            name={show===false ? 'eye-outline' : 'eye-off-outline'}
            size={18}
            color= '#BCBCBC'
            style={styles.eyeIcon}
            onPress={() => setShow(!show)}
            
          />
          
        </View>

        <View style={styles.forgotPassword}>
          <Text style={styles.forgotPasswordTxt}>Forgot Password? </Text>
          <Text style={styles.resetTxt}>Reset</Text>
        </View>

      

      </View>


      <View style={styles.continueBtn}>
        <ContinueBtn text='Continue' color='white'/>
      </View>

      
    </View>
    
  )
}



export default LogIN


const styles = StyleSheet.create({
  container: {
    flex:1,
    height:'100%',
    width:'100%',
    backgroundColor: '#241332',
    alignItems: 'center',
    
  },
  mainTitle: {
    color:'#BCBCBC',
    fontSize: 23,
    marginTop:'0%',
    fontWeight:'700',
    
  },

  titleContainer: {
    marginTop: '11%',
    alignItems: 'center',
    justifyContent: 'center',

  },

  authCotainer:{
    width: '100%',
    height: '50%',
    alignItems: 'center',
  },
  buttonRow:{
    marginTop: '3%',
    height: '33%',
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
   
    
  },
  roundBtn:{
    width: '28%',
    height: '36%',
    marginStart: 30,
    marginEnd:30,
  },

  linearInputArea:{
    flexDirection: 'row',
    width: '70%',
    height: 50,
    marginTop: '10%',
    marginBottom: '2%',
    borderColor: '#BCBCBC',
    borderBottomWidth: 2,
  },

  inputIcon:{
    position: 'absolute',
    top: 14,
  },

  textInput:{
    paddingLeft: 30,
    fontSize: 17,
    width: '100%',
    color: '#BCBCBC',

  },
  eyeIcon:{
    position: 'absolute',
    top: 16,
    right: '0%'
  },

  forgotPassword:{
    flexDirection: 'row',
    marginTop: '8%',
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  forgotPasswordTxt:{
    color: '#BCBCBC'
  },
  resetTxt:{
    color:'#D29BC5'
  },
  

  continueBtn: {
    
    width: '70%',
    height: '8%',
    marginTop: '30%',

  },
  
  
});
