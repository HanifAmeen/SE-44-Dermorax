import React, { Component} from 'react';
import { StyleSheet, Text, View, Button, TouchableOpacity} from 'react-native';


const button = props => {

    const content =(

        <View style={[styles.container,{ backgroundColor:props.color}]}>

            <Text style={styles.text}>{props.text}</Text>

        </View>

    )

    return <TouchableOpacity onPress={props.onPress}>{content}</TouchableOpacity>


}

export default button

const styles = StyleSheet.create({

    container: {
        padding: 5,
        width: '100%',
        height: '100%',
        borderTopLeftRadius: 40,
        borderBottomRightRadius: 40,
        alignItems: 'center',
        justifyContent: 'center',
    },
    text: {
        fontSize:20,
        textAlign: 'center',
        color: 'black',
        fontWeight: 'bold'
    }

})