import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2ndclass_2.databinding.ActivityNewBinding

class NewActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //layoutInflator -> XML 파일을 읽어서 객체로 만들어주는 역할할
       viewBinding = ActivityNewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}