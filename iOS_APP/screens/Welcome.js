import { StyleSheet, Text, View } from 'react-native'
import React from 'react'

const Login = () => {
  return (
    <View>

      
      <Text style={styles.mainTitle}>DERMORAX</Text>

      <Image 
        source={require('./assets/logo.png')}
        style={styles.logo}
      />

      <Text style={styles.welcome}>Welcome</Text>

      {/* <Image 
        source={require('./assets/bar.png')}
        style={styles.bar}
      />

      <View style={styles.buttonsContainer}>
      <Button
        title='SIGN IN' color='#9599B3'  style={styles.button}
        /> */}

      

        <Image
          source={require('./assets/bg_1.png')}
          style={styles.login}
          />


        <Button
          title={'LOG IN'}
          containerStyle={{width: 500, position:'absolute'}}
          />

    </View>
  )
}

export default Login

const styles = StyleSheet.create({

    container: {
        flex:1,
        height:'100%',
        width:'100%',
        backgroundColor: '#241332',
        alignItems: 'center',
        justifyContent: 'center',
      },
      mainTitle: {
        color:'#BCBCBC',
        fontSize: 40,
        marginTop:'20%',
        fontWeight:'bold',
      },
      welcome: {
        flex: 1,
        color:'#BCBCBC',
        fontSize: 30,
        marginTop:'10%',
        fontWeight:'normal',
        alignItems: 'center',
        justifyContent: 'center',
      },
      logo: {
        width:300, 
        height:230, 
        marginTop:'6%',
        position: 'absolute'
      },
      bar: {
        width:200, 
        height:15, 
        marginTop:'0%',
      },
      login: {
        width: '99%',
        height: 130
      },
      button: {
        margin:'0%',
        height:'100%',
        marginBottom:50
    
      },
      buttonsView: {
        marginTop:'0%',
        
      },
      buttonsContainer: {
        
        justifyContent: 'center',
        alignItems: 'center',
        width: '100%',
        
        marginBottom: '20%',
      },
})