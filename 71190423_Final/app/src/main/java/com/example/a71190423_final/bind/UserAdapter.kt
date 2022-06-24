package com.example.a71190423_final.bind

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.a71190423_final.*
import com.google.firebase.firestore.CollectionReference
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.internal.ContextUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.item_list_user.view.*
import java.util.ArrayList


class UserAdapter (var listBook: ArrayList<Users>, var context: Context): RecyclerView.Adapter<UserAdapter.BookHolder>() {
    var firestore: FirebaseFirestore? = null

    val mainList = listBook
    val searchList = ArrayList<Users>(listBook)

    class BookHolder(val view : View): RecyclerView.ViewHolder(view){

        @SuppressLint("RestrictedApi")
        fun bind(book: Users, context: Context){
            val firestore = FirebaseFirestore.getInstance()

            view.findViewById<TextView>(R.id.TitleBook).setText(book.title)
            view.findViewById<TextView>(R.id.Writer).setText(book.writer)
            view.setOnClickListener {
                val i: Intent = Intent(view.context, BookDetail::class.java)
//                i.putExtra("foto",lagu.foto)
                i.putExtra("title",book.title)
                i.putExtra("writer",book.writer)
                i.putExtra("creator",book.creator)
                i.putExtra("years",book.years)
                i.putExtra("page",book.pageTotal)
                context.startActivity(i)

            }

            val buttonUpdate = view.findViewById<ImageButton>(R.id.editBtn)
            buttonUpdate.setOnClickListener {
                val i: Intent = Intent(view.context, AddEditActivity::class.java)
                i.putExtra("title",book.title)
                i.putExtra("writer",book.writer)
                i.putExtra("creator",book.creator)
                i.putExtra("years",book.years)
                i.putExtra("page",book.pageTotal)
                context.startActivity(i)
            }
            val delBtn = view.findViewById<ImageButton>(R.id.delBtn)
            delBtn.setOnClickListener {
                firestore?.collection(book.account)
                    ?.document(book.title)?.delete()
                    ?.addOnSuccessListener {
                        val i: Intent = Intent(view.context, MainActivity::class.java)
                        context.startActivity(i)
                        ContextUtils.getActivity(view.context)?.finish()
                    }
            }


//            view.setOnClickListener{
//                val i: Intent = Intent(view.context, SongDetail::class.java)
////                i.putExtra("foto",lagu.foto)
//                i.putExtra("judul",lagu.judul)
//                i.putExtra("penyanyi",lagu.penyanyi)
//                i.putExtra("album",lagu.album)
//                i.putExtra("genre",lagu.genre)
//                i.putExtra("tanggal",lagu.tanggal)
//                context.startActivity(i)
//            }
//            val buttonUpdate = view.findViewById<Button>(R.id.buttonUpdate)
//            buttonUpdate.setOnClickListener {
//                val i: Intent = Intent(view.context, UpdateMusic::class.java)
////                i.putExtra("foto",lagu.foto)
//                i.putExtra("judul",lagu.judul)
//                i.putExtra("penyanyi",lagu.penyanyi)
//                i.putExtra("album",lagu.album)
//                i.putExtra("genre",lagu.genre)
//                i.putExtra("tanggal",lagu.tanggal)
//                context.startActivity(i)
//            }
//            val buttonHapus = view.findViewById<Button>(R.id.buttonDelete)
//            buttonHapus.setOnClickListener {
//                firestore?.collection(lagu.akun)
//                    ?.document(lagu.judul)?.delete()
//                    ?.addOnSuccessListener {
//                        val i: Intent = Intent(view.context, LoginScreen::class.java)
//                        context.startActivity(i)
//                        ContextUtils.getActivity(view.context)?.finish()
//                    }
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return BookHolder(v)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(listBook[position],context)
    }

    override fun getItemCount(): Int {
        return mainList.size
    }
}


//
//class UserAdapter(
//    private val context: Context,
//    private val collection: CollectionReference, options : FirestoreRecyclerOptions<Users>
//) : FirestoreRecyclerAdapter<Users, UserAdapter.UsersViewHolder>(options) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
//        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent,
//            false))
//    }
//
//    override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int, users: Users) {
//        viewHolder.bindItem(users)
//        viewHolder.itemView.setOnClickListener {
//            showDialogMenu(users)
//        }
//    }
//    class UsersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        fun bindItem(users: Users) {
//            view.apply {
//                val Title = "title: ${users.title}"
//                val Creator = "creator: ${users.creator}"
//                val Writer = "writer: ${users.writer}"
//                val Years = "years: ${users.years.toString()}"
//
//
//                TitleBook.text = Title
//                Publisher.text = Creator
//                WriterBook.text = Writer
//                YearsBook.text = Years
//            }
//        }
//    }
//
//    private fun showDialogMenu(users: Users) {
//        //dialog popup edit hapus
//        val builder = AlertDialog.Builder(context, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//        val option = arrayOf("Edit", "Hapus")
//        builder.setItems(option) { dialog, which ->
//            when (which) {
//                //0 -> untuk berpindah ke activity AddEditActivity untuk edit dengan membawa data
//                0 ->
//                    context.startActivity(Intent(context, AddEditActivity::class.java).apply {
//                    putExtra(AddEditActivity.REQ_EDIT, true)
//                    putExtra(AddEditActivity.EXTRA_DATA, users)
//                })
//                1 -> showDialogDel(users.strId)
//            }
//        }
//        builder.create().show()
//    }
//    private fun showDialogDel(strId: String) {
//        //dialog pop delete
//        val builder = AlertDialog.Builder(context, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//            .setTitle("Hapus Data")
//            .setMessage("Yakin mau hapus?")
//            .setPositiveButton(android.R.string.yes) { dialog, which ->
//                deleteById(strId)
//            }
//            .setNegativeButton(android.R.string.cancel, null)
//        builder.create().show()
//    }
//    private fun deleteById(id: String) {
//        //menghapus data berdasarkan id
//        collection.document(id)
//            .delete()
//            .addOnCompleteListener { Toast.makeText(context, "Succes Hapus data", Toast.LENGTH_SHORT).show() }
//            .addOnFailureListener { Toast.makeText(context, "Gagal Hapus data", Toast.LENGTH_SHORT).show() }
//    }
//}

//class UserAdapter(
//    private val context: ArrayList<Users>,
//    private val collection: CollectionReference, options: FirestoreRecyclerOptions<Users>
//) : FirestoreRecyclerAdapter<Users, UserAdapter.UsersViewHolder>(options) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
//        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false))
//    }
//
//    override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int, users: Users) {
//        viewHolder.bindItem(users)
//        viewHolder.itemView.setOnClickListener {
//            showDialogMenu(users)
//        }
//    }
//    class UsersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        fun bindItem(users: Users) {
//            view.apply {
////                val name = "Nama   : ${users.strName}"
////                val addr = "Alamat : ${users.strAddress}"
////                val age = "Umur    : ${users.intAge.toString()}"
////
////                tv_name.text = name
////                tv_adress.text = addr
////                tv_age.text = age
//                val Title = "title: ${users.title}"
//                val Creator = "creator: ${users.creator}"
//                val Writer = "writer: ${users.writer}"
//                val Years = "years: ${users.years.toString()}"
//
//
//                TitleBook.text = Title
//                Publisher.text = Creator
//                WriterBook.text = Writer
//                YearsBook.text = Years
//            }
//        }
//            fun bind(users: Users) {
//                val firestore = FirebaseFirestore.getInstance()

//
//            }
//    }
//
//    private fun showDialogMenu(users: Users) {
//        //dialog popup edit hapus
//        val builder = AlertDialog.Builder(context, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//        val option = arrayOf("Edit", "Hapus")
//        builder.setItems(option) { dialog, which ->
//            when (which) {
//                //0 -> untuk berpindah ke activity AddEditActivity untuk edit dengan membawa data
//                0 -> context.startActivity(Intent(context, AddEditActivity::class.java).apply {
//                    putExtra(AddEditActivity.REQ_EDIT, true)
//                    putExtra(AddEditActivity.EXTRA_DATA, users)
//                })
//                1 -> showDialogDel(users.strId)
//            }
//        }
//        builder.create().show()
//    }
//    private fun showDialogDel(strId: String) {
//        //dialog pop delete
//        val builder = AlertDialog.Builder(context, com.google.android.material.R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
//            .setTitle("Hapus Data")
//            .setMessage("Yakin mau hapus?")
//            .setPositiveButton(android.R.string.yes) { dialog, which ->
//                deleteById(strId)
//            }
//            .setNegativeButton(android.R.string.cancel, null)
//        builder.create().show()
//    }
//    private fun deleteById(id: String) {
//        //menghapus data berdasarkan id
//        collection.document(id)
//            .delete()
//            .addOnCompleteListener { Toast.makeText(context, "Succes Hapus data", Toast.LENGTH_SHORT).show() }
//            .addOnFailureListener { Toast.makeText(context, "Gagal Hapus data", Toast.LENGTH_SHORT).show() }
//    }

