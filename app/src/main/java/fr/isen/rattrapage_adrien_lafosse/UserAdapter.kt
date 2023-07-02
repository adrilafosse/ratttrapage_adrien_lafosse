import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.rattrapage_adrien_lafosse.User
import fr.isen.rattrapage_adrien_lafosse.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val userList: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addUser(user: User) {
        userList.add(user)
        notifyItemInserted(userList.size - 1)
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewFirstName: TextView = itemView.findViewById(R.id.textViewFirstName)
        private val textViewLastName: TextView = itemView.findViewById(R.id.textViewLastName)

        fun bind(user: User) {
            textViewFirstName.text = user.firstName
            textViewLastName.text = user.lastName
        }
    }
}
