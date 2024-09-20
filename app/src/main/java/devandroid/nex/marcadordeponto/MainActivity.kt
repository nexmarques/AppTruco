package devandroid.nex.marcadordeponto

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import devandroid.nex.marcadordeponto.databinding.ActivityMainBinding

enum class Operacao{
    SOMAR, SUBTRAIR
}

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate ( layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
            iniciarOperacoes()
        }

    private fun iniciarOperacoes(){
        with(binding){
            btnSomarNos.setOnClickListener {
                atualizarResultados(txtResultNos, txtResultVitoriaNos, Operacao.SOMAR)
            }
            btnSomarEles.setOnClickListener {
                atualizarResultados(txtResultEles, txtResultVitoriaEles, Operacao.SOMAR)
            }
            btnDiminuirNos.setOnClickListener {
                atualizarResultados(txtResultNos, txtResultVitoriaNos, Operacao.SUBTRAIR)
            }
            btnDiminuirEles.setOnClickListener {
                atualizarResultados(txtResultEles, txtResultVitoriaEles, Operacao.SUBTRAIR)
            }
        }
    }

    private fun atualizarResultados(txtResultado : TextView, txtResultadoVitoria : TextView, operacao: Operacao){
        val resultadoAtual = txtResultado.text.toString()
        var resultadoConvertido = resultadoAtual.toInt()
        val txtResultadoVitoriaVa = txtResultadoVitoria.text.toString()
        var resultadoConvertidoVitoria = txtResultadoVitoriaVa.toInt()
        resultadoConvertidoVitoria += 1
        when(operacao){
            Operacao.SOMAR -> resultadoConvertido += 1
            Operacao.SUBTRAIR -> resultadoConvertido -= 1
        }
        if(resultadoConvertido < 0){
            txtResultado.text = 0.toString()
        }
        else if(resultadoConvertido < 12){
            txtResultado.text = resultadoConvertido.toString()
        }
        else if(resultadoConvertido == 12){
            txtResultado.text = resultadoConvertido.toString()
            Toast.makeText(this, "Parabéns, você ganhou", Toast.LENGTH_SHORT).show()
        }else {
            txtResultado.text = 0.toString()
            txtResultadoVitoria.text = resultadoConvertidoVitoria.toString()
        }
    }





}




