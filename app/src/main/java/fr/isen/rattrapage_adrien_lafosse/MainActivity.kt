import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.rattrapage_adrien_lafosse.R
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter()
        recyclerView.adapter = userAdapter

        val url = "https://randomuser.me/api/?results=25"

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val resultsArray = response.getJSONArray("results")

                for (i in 0 until resultsArray.length()) {
                    val resultObject = resultsArray.getJSONObject(i)
                    val nameObject = resultObject.getJSONObject("name")
                    val firstName = nameObject.getString("first")
                    val lastName = nameObject.getString("last")

                    val user = User(firstName, lastName)
                    userAdapter.addUser(user)
                }
            },
            { error ->
                // Gestion des erreurs de requête ici
                error.printStackTrace()
            }
        )

        Volley.newRequestQueue(this).add(request)
    }
}
