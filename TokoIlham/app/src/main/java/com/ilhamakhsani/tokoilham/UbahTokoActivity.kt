package com.ilhamakhsani.Tokoilham

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ilhamakhsani.Tokoilham.database.TokoDao
import com.ilhamakhsani.Tokoilham.database.TokoRomDB
import com.ilhamakhsani.Tokoilham.model.Toko
import kotlinx.android.synthetic.main.activity_ubah_toko.*

class UbahTokoActivity : AppCompatActivity() {
    val UBAH_Toko_EXTRA = "ubah_Toko_extra"
    private lateinit var Toko: Toko
    private var isUpdate = false
    private lateinit var database: TokoRomDB
    private lateinit var dao: TokoDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubah_toko)

        database = TokoRomDB.getDatabase(applicationContext)
        dao = database.getTokoDao()

        if (intent.getParcelableExtra<Toko>(UBAH_Toko_EXTRA) != null){
            button_delete.visibility = View.VISIBLE
            isUpdate = true
            Toko = intent.getParcelableExtra(UBAH_Toko_EXTRA)!!
            edit_text_name.setText(Toko.title)
            edit_text_operator.setText(Toko.body)

            edit_text_name.setSelection(Toko.title.length)

        }

        button_save.setOnClickListener {
            val title = edit_text_name.text.toString()
            val body = edit_text_operator.text.toString()

            if (title.isEmpty() && body.isEmpty()){
                Toast.makeText(applicationContext, "Toko tidak boleh ksosong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (isUpdate){
                    saveToko(Toko(id = Toko.id, title = title, body = body))
                }
                else{
                    saveToko(Toko(title = title, body = body))
                }
            }

            finish()
        }

        button_delete.setOnClickListener {
            deleteToko(Toko)
            finish()
        }

    }

    private fun saveToko(Toko: Toko){

        if (dao.getById(Toko.id).isEmpty()){

            dao.insert(Toko)
        }
        else{

            dao.update(Toko)
        }

        Toast.makeText(applicationContext, "Berhasil di simpan", Toast.LENGTH_SHORT).show()

    }

    private fun deleteToko(Toko: Toko){
        dao.delete(Toko)
        Toast.makeText(applicationContext, "berhasil di hapus", Toast.LENGTH_SHORT).show()
    }
}