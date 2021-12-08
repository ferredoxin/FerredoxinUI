import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.ferredoxin.ferredoxinui.common.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}