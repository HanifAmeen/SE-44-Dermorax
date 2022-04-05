import { StyleSheet, Text, View } from 'react-native'
import React from 'react'

const PreviousPredictionsScreen = () => {
  return (
    <View style={styles.container}>
      <Text>PreviousPredictionsScreen</Text>
    </View>
  )
}

export default PreviousPredictionsScreen

const styles = StyleSheet.create({

    container: {
        flex:1,
        height:'100%',
        width:'100%',
        backgroundColor: '#241332',
        alignItems: 'center',
    },
})