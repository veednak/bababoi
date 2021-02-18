import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.*
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import java.net.URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList


private fun Test()
{
    val array = arrayOfNulls<Int>(100000)
    array.forEach { println(it) }
}
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cur = this
        button.setOnClickListener {
            var mainArr: ArrayList<Model?> = ArrayList()
            val rw: ListView = findViewById(R.id.list)




            CoroutineScope(Dispatchers.IO).launch {
                try {
                    var con = URL("http://www.cbr.ru/scripts/XML_daily.asp").openStream()
                    val o = XmlMapper().readValue(con, ValCurs::class.java)
                    val Items=java.util.ArrayList<Model>()
                    val adapter=MyAdapter(cur,R.layout.row,Items)
                    for (i in o!!.valute!!.indices) {
                        var asd:Double= (o!!.valute!![i]!!.Value.toDouble()/2.4)
                        if(o!!.valute!![i].Name == "Белорусский рубль") {
                            Items.add(Model(o!!.valute!![i].Name, asd, R.drawable.ic_launcher_foreground))
                        }
                        else{
                            Items.add(Model(o!!.valute!![i].Name, asd, R.drawable.ic_launcher_foreground))
                        }
                    }
                    mainArr.addAll(Items)
                    runOnUiThread {

                        rw.adapter = adapter
                    }
                    Log.e("bababoi", "Second courutine done!");
                } catch (e: Exception) {
                    Log.e("bebe2", e.message.toString());
                }
            }
        }
    }
}
