package com.example.heavyduty.presentation.view.util.customButton


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heavyduty.presentation.view.theme.HeavyDutyTheme

@SuppressLint("ModifierParameter")
@Composable
fun CustomButton(
    addShadow: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    leadingIcon: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
        .width(300.dp)
        .height(40.dp),
    text: String = "Button Text",
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColorAlphaValue: Float = 1f,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    style: TextStyle = MaterialTheme.typography.labelSmall
){
    Button(
        shape = shape,
        onClick = onClick ,
        modifier = if(addShadow)
        { modifier.shadow(elevation = 5.dp, shape = CircleShape)}
        else{modifier},
        colors = ButtonDefaults.buttonColors(backgroundColor.copy(alpha = backgroundColorAlphaValue))

    ) {
        if (leadingIcon != null) leadingIcon()
        Text(
            text = text,
            color = textColor,
            style = style,
            textAlign = TextAlign.Center,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(1f))
    }
}

@Preview(showBackground = false)
@Composable
private fun PreviewCustomButton(){
    HeavyDutyTheme(dynamicColor = false) {
        CustomButton(onClick = {})
    }

}