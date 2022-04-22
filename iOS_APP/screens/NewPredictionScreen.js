import { StyleSheet, ScrollView, TouchanbleOpacity,Text, View, Image, Alert, uri} from 'react-native'
import React, {useState, useEffect} from 'react'
import * as ImagePicker from 'expo-image-picker'

//import ImagePicker from 'react-native-image-crop-picker';
import SwitchSelector from "react-native-switch-selector";
import PredictBtn from '../buttons/predictBtn'


const toggleOptions = [
    {label:'Capture' ,value:'capture' },
    {label:'Upload' ,value:'upload' },
 ];


 
const NewPredictionScreen = () => {

    const [imageOption, setImageOption] = useState('capture');
    const [btnText, setBtnText] = useState('PREDICT');
    const [hasGalleryPermission, setHasGalleryPermission] = useState(null);
    const [hasCameraPermission, setHasCameraPermission] = useState(null);
    const [image,setImage] = useState(null);

    useEffect(() => {
        (async () => {
            const galleryStatus = await ImagePicker.requestMediaLibraryPermissionsAsync()
            setHasGalleryPermission( galleryStatus.status === 'granted')
            const cameraStatus = await ImagePicker.requestCameraPermissionsAsync()
            setHasCameraPermission( cameraStatus.status === 'granted')
        }) ();
    }, []);

    const pickImage = async () => {
        let result = ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.Images,
            allowsEditing: true,
            aspect: [1,1],
            quality: 1,
        })

        if(!(await result).cancelled){
            setImage(result._W.uri)
            console.log(JSON.stringify(result))
        }
        if(hasGalleryPermission===false){
            return alert('No Access to Internal Storage')
        }

       
        
    };

    const captureImage = async () => {
        let result = ImagePicker.launchCameraAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.Images,
            allowsEditing: true,
            aspect: [1,1],
            quality:1,
        })

        if(!(await result).cancelled){
            setImage(result._W.uri)
            console.log(JSON.stringify(result))
        }else{
            alert('No Image was Captured')
        }
        if(hasCameraPermission==false){
            return alert('No Access to Camera')
        }
        
        
    };

    function captureOrUploadImage(value){

        if(value=='capture'){
            captureImage()
    
        }else if(value=='upload'){
            pickImage()
            
        }
    }

    

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
                backgroundColor={'#dfc5e6'}
                hasPadding
                fontSize={17}
                onPress = { value => [setImageOption(value),captureOrUploadImage(value)]}
                
                />
        </View>


       <View style={styles.imageViewContainer}>
            <Image
                style={styles.imageView}
                source={{uri: image}}
            />

        </View>

        <View style={styles.predictBtn}>
            <PredictBtn text={btnText} color='#5f3878' onPress={()=> setBtnText('Processing.....')}></PredictBtn>
        </View>
        
    

        <Text style={styles.end}></Text>

       
      
    </ScrollView>
    </View>

   
  )
}

export default NewPredictionScreen



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
        height:'32.5%',
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
        height:'5%',
    },


    end:{
        marginTop:'100%'
    }
    
})