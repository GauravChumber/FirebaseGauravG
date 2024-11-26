package com.example.firebasegauravg

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage

class PersonAdapter(options: FirebaseRecyclerOptions<Person>)
    : FirebaseRecyclerAdapter<Person, PersonAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Person) {
        // Bind data to the view holder here
        holder.txtName.text = model.name
        holder.txtRole.text = model.role
        holder.txtAge.text = model.age.toString()

        val theImage: String = model.photo

        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(theImage)
        Glide.with(holder.imgPhoto.context)
            .load(storageReference)
            .into(holder.imgPhoto)
    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.row_layout, parent, false)) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRole: TextView = itemView.findViewById(R.id.txtRole)
        val txtAge: TextView = itemView.findViewById(R.id.txtAge)
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
    }
}
