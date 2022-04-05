import { StyleSheet, ScrollView, TouchanbleOpacity,Text, View, Image, Alert} from 'react-native'
import React, {useState} from 'react'

//import ImagePicker from 'react-native-image-crop-picker';
import SwitchSelector from "react-native-switch-selector";
import PredictBtn from '../buttons/predictBtn'


const toggleOptions = [
    {label:'Capture' ,value:'capture' },
    {label:'Upload' ,value:'upload' },
 ];


 
const NewPredictionScreen = () => {

    const [imageOption, setImageOption] = useState('capture')
    const [btnText, setBtnText] = useState('PREDICT')

  return (
    <View style={styles.container} >
    <ScrollView contentContainerStyle={styles.scrollContainer}>

        <Text style={styles.textContainer}>NewPredictionScreen</Text>


        <View style={styles.toggleContainer}>
            <SwitchSelector
                options={toggleOptions}
                initial={0}
                selectedColor={'white'}
                buttonColor={'#663c82'}
                borderColor={'#c9abd1'}
                backgroundColor={'#c9abd1'}
                hasPadding
                fontSize={20}
                onPress = { value => [setImageOption(value),captureOrUploadImage(value)]}
                
                />
        </View>


       <View style={styles.imageViewContainer}>
            <Image
            style={styles.imageView}
            source={{
            uri: 'https://th.bing.com/th/id/OIP.ajk2pbTiXf7j_hYsiBm4bwHaHI?pid=ImgDet&rs=1',
            }}
            />

        </View>

        <View style={styles.predictBtn}>
            <PredictBtn text={btnText} color='#8A56AC' onPress={()=> setBtnText('Processing.....')}></PredictBtn>
        </View>
        
    

        <Text style={styles.end}></Text>

       
      
    </ScrollView>
    </View>
   
  )
}

export default NewPredictionScreen

function captureOrUploadImage(value){

    if(value=='capture'){
        Alert.alert('capture','Success')
        

    }else if(value=='upload'){
        alert('uploading')
        
    }
}

const styles = StyleSheet.create({

    container: {
        
        height:'100%',
        width:'100%',
        backgroundColor: '#241332',
        
    },
    scrollContainer:{
       height:1000,
        width:'100%',
        justifyContent: 'center',
        alignItems: 'center',
        
    },
    textContainer:{
        marginTop:'20%',
        fontSize:40,
        padding:2,
        color:'white'
    },
    toggleContainer:{
        marginTop:'10%',
        width:'80%',
    },
    imageViewContainer:{
        marginTop:'10%',
        width:'90%',
        height:'40%',
        shadowColor: "black",
        shadowOffset: {
            width: 10,
            height: 10,
        },
        shadowOpacity: 0.05,
        shadowRadius: 5,
        elevation: 50,
        
    },
    imageView: {
        width:'100%',
        height:'100%',
        borderRadius:30,
    },
    predictBtn:{
        marginTop:'10%',
        width:'60%',
        height:'6%',
    },


    end:{
        marginTop:'100%'
    }
    
})