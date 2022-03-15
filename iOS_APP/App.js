import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image, Button} from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      
      <Text style={styles.mainTitle}>DERMORAX</Text>

      <Image 
        source={require('./assets/logo.png')}
        style={styles.logo}
      />

      <Text style={styles.welcome}>Welcome</Text>

      <Image 
        source={require('./assets/bar.png')}
        style={styles.bar}
      />

      <View style={styles.buttonsContainer}>
      <Button
        title='SIGN IN' color='#9599B3'  style={styles.button}
        />

      <Button
                title={'React Native Elements'}
                containerStyle={{
                  width: 100,
                  
                }}/>

      

      </View>


      
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
  },
  bar: {
    width:200, 
    height:15, 
    marginTop:'0%',
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
});
