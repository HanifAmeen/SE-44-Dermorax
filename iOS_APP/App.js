import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image, Button} from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      
      <View style={styles.mainTitle}>
        <Text style={styles.mainTitle}>DERMORAX</Text>
      </View>
      
      <View style={styles.logo}>
      <Image 
        source={require('./assets/logo.png')}
        style={styles.logo}
      />
      </View>
      

      <View style={styles.welcome}>
        <Text style={styles.welcome}>Welcome</Text>
      </View>
      
      

      {/* <Image 
        source={require('./assets/bar.png')}
        style={styles.bar}
      />  */}

      <View style={styles.loginContainer}>
        <Image
          source={require('./assets/bg_1.png')}
          style={styles.loginContainer}
          />

        
      </View>
      
      <Button
        title={'LOG IN'}
        style={styles.login}
        />
      


      
      

      

      


      
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
    justifyContent: 'center'
  },
  mainTitle: {
    color:'#BCBCBC',
    fontSize: 40,
    marginTop:'18%',
    fontWeight:'bold'
  },
  logo: {
    flex:1,
    width:280, 
    height:230,
    position:'absolute'
    
  },
  welcome: {
    flex:1,
    color:'#BCBCBC',
    fontSize: 30,
    marginTop:'20%'
  },
  login: {
    
  },
  loginContainer:{
    
    marginTop:'36%',
    width: '100%',
    height: '47%',
    
  },

  
  bar: {
    width:200, 
    height:15, 
    marginTop:'0%'
  }
  
  
});
