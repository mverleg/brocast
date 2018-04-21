package nl.markv.brocast.brocast.signup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import java.util.ArrayList

class UserAdapter(private val mContext: Context, private val mDataSource: List<User>) : BaseAdapter() {
    private val mInflater: LayoutInflater

    init {
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    //1
    override fun getCount(): Int {
        return mDataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return mDataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        // Get view for row item
        val rowView = mInflater.inflate(R.layout.list_item_user, parent, false)

        val nameTextView = rowView.findViewById<View>(R.id.recipe_list_name) as TextView

        val (_, name) = getItem(position) as User

        nameTextView.text = name

        return rowView
    }
}
