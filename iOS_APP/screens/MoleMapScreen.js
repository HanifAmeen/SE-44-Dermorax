import { StyleSheet, Text, View } from 'react-native'
import React from 'react'

const MoleMapScreen = () => {
  return (
    <View style={styles.container}>
      <Text>MoleMapScreen</Text>
    </View>
  )
}

export default MoleMapScreen

const styles = StyleSheet.create({

    container: {
        flex:1,
        height:'100%',
        width:'100%',
        backgroundColor: '#241332',
        alignItems: 'center',
    },
})