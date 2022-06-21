import io.github.aakira.napier.Antilog
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    Napier.base(DebugAntilog())
    window.onload = {
        render(document.getElementById("root")!!) {
//            child()
//            child(AppComponent::class) {

//            }
        }
    }
}
