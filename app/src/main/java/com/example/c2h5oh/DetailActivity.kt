import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.c2h5oh.ApiResultActivity
import com.example.c2h5oh.Liquor
import com.example.c2h5oh.fetchLiquorsByTags
import com.example.c2h5oh.screens.DetailScreen
import com.example.c2h5oh.theme.C2h5ohTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedTags = intent.getStringArrayListExtra("selected_tags") ?: arrayListOf()

        setContent {
            val activity = LocalActivity.current
            var liquorList by remember { mutableStateOf<List<Liquor>>(emptyList()) }

            LaunchedEffect(Unit) {
                val result = fetchLiquorsByTags(selectedTags)
                liquorList = result
            }

            C2h5ohTheme {
                DetailScreen(
                    tags = selectedTags,
                    liquors = liquorList,
                    onBackClick = { activity?.finish() },
                    onApiClick = {
                        val intent = Intent(activity, ApiResultActivity::class.java)
                        activity?.startActivity(intent)
                    }
                )
            }
        }
    }

    private fun DetailScreen(tags: ArrayList<String>, liquors: List<Liquor>, onBackClick: () -> Unit, onApiClick: () -> Unit) {

    }
}