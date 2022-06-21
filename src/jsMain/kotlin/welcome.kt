import com.ccfraser.muirwik.components.styles.*
import com.ccfraser.muirwik.components.styles.Color
import freesound.FreesoundFetcher
import freesound.data.Descriptors
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

external interface AuthCredentialsProp : Props {
    var email: String
    var password: String
    var code: String
    var onLoginClick: (email: String) -> Unit
}

data class AuthState(
    val email: String = "",
    val password: String = "",
    val code: String = "",
    val descriptors: List<String> = mutableListOf()
) : State

@JsExport
class AppComponent(props: AuthCredentialsProp) : RComponent<AuthCredentialsProp, AuthState>(props) {

    override fun RBuilder.render() {
        val mainScope = MainScope()
        if (state.descriptors == null) {
            analyze {
                availableDescriptors = state.descriptors
            }
        } else if (state.email == "") {
            styledDiv {
//                mCircularProgress(size = 50.px) { css { margin(2.spacingUnits) } }
            }
            mainScope.launch {
                console.log("START FETCHING")
                val d = freesoundFetcher.availableDescriptors().descriptors
                setState({ st -> AuthState(email = st.email, password = st.password, code = st.code, descriptors = d) })
            }
        } else {
            auth {
                onLoginClick = { email ->
                    setState({ st -> AuthState(email = email, password = st.password, code = st.code) })
                }
            }
        }
    }
}

fun RBuilder.auth(handler: AuthCredentialsProp.() -> Unit) = child(auth) {
    attrs.handler()
}

val auth = fc<AuthCredentialsProp> { props ->
    styledDiv {
//        css {
//            width = 30.pct
//            margin = "auto"
//            backgroundColor = Color("papayawhip")
//            textAlign = TextAlign.center
//        }
        var email = ""
//        Typography("Авторизация", align = TypographyAlign.center) {}
//        OutlinedInput(placeholder = "email", onChange = {
//            email = it.targetInputValue
//        }) {}
////        br {}
//        Button("Login", onClick = {
//            props.onLoginClick(email)
//        })
    }
}

fun RBuilder.analyze(handler: AnalyzeProp.() -> Unit) = child(analyze) {
    attrs.handler()
}

val freesoundFetcher = FreesoundFetcher()

val analyze = fc<AnalyzeProp> { props ->
    val mainScope = MainScope()
//    styledDiv {
//        css {
//            width = 30.pct
//            margin = "auto"
//            backgroundColor = Color("papayawhip")
//            textAlign = TextAlign.center
//        }
//        AutoComplete(props.availableDescriptors.toTypedArray(), { params ->
//            TextField("Choose descriptors", variant = FormControlVariant.outlined) {
//                spreadProps(params)
//            }
//        }) {
//            attrs.id = "multiple-values"
//            attrs.multiple = true
//            attrs.filterSelectedOptions = true
//            attrs.getOptionLabel = { option -> option }
//        }
//        Button("Load", onClick = {
//            mainScope.launch {
//                console.log("START FETCHING")
//                freesoundFetcher.loadAnalyzeData(Descriptors(mutableListOf()))
//            }
//        })
//    }
}


external interface AnalyzeProp : Props {
    var token: String
    var availableDescriptors: List<String>
}

data class AnalyzeState(val descriptor: String = "mfcc") : State


@JsExport
class AnalyzeComponent(props: AnalyzeProp) : RComponent<AnalyzeProp, AnalyzeState>(props) {

    override fun RBuilder.render() {
        styledDiv {
//            css {
//                backgroundColor = Color("papayawhip")
//                textAlign = TextAlign.center
//            }
//            mTypography("Aвторизован", align = MTypographyAlign.center) {
//                css {
//
//                }
//            }
        }
    }


}
