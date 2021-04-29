package tw.edu.pu.csim.tcyang.firestore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    var user: MutableMap<String, Any> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnUpdate.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                user["名字"] = edtName.text.toString()
                user["出生體重"] = edtWeight.text.toString().toInt()
                db.collection("Users")
                        //.document(edtName.text.toString())
                        //.set(user)
                        .add(user)
                        .addOnSuccessListener {
                            Toast.makeText(baseContext, "新增/異動資料成功",
                                    Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(baseContext, "新增/異動資料失敗：" + e.toString(),
                                    Toast.LENGTH_LONG).show()
                        }
            }
        })
    }
}