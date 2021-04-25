package com.ilhamakhsani.Tokoilham

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamakhsani.Tokoilham.database.TokoRomDB
import com.ilhamakhsani.Tokoilham.model.Toko
import kotlinx.android.synthetic.main.activity_toko.*

class TokoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toko)
        getTokoData()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, UbahTokoActivity::class.java))
        }
    }  private fun getTokoData(){
        val database = TokoRomDB.getDatabase(applicationContext)
        val dao = database.getTokoDao()
        val listItems = arrayListOf<Toko>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()){
            text_view_note_empty.visibility = View.GONE
        }
        else{
            text_view_note_empty.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Toko>){
        recycler_view_main.apply {
            adapter = TokoAdapter(listItems, object : TokoAdapter.TokoListener{
                override fun OnItemClicked(Toko: Toko) {
                    val intent = Intent(this@TokoActivity, UbahTokoActivity::class.java)
                    intent.putExtra(UbahTokoActivity().UBAH_Toko_EXTRA, Toko)
                    startActivity(intent)
                }
            })

            layoutManager = LinearLayoutManager(this@TokoActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        getTokoData()
    }
}