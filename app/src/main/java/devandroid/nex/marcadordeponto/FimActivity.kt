package devandroid.nex.marcadordeponto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import devandroid.nex.marcadordeponto.databinding.ActivityFimBinding

class FimActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFimBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding){
            btnFechar.setOnClickListener {
                finish()
            }
        }




    }

    private fun receberDados(){

    }
}