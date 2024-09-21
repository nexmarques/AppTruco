package devandroid.nex.marcadordeponto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import devandroid.nex.marcadordeponto.databinding.ActivityMainBinding

enum class Operacao{
    SOMAR, SUBTRAIR
}

class MainActivity : AppCompatActivity() {

    private val RESULTADO_FINAL = 12
    private var valorRodada = 1
    private val binding by lazy {
        ActivityMainBinding.inflate ( layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
            iniciarOperacoes()
        binding.btnDiminuirEles.visibility = View.GONE
        binding.btnDiminuirNos.visibility = View.GONE
    }

    private fun iniciarOperacoes(){
        with(binding){
            btnTruco.setOnClickListener {
                when (valorRodada) {
                    1 -> {
                        btnTruco.text = String.format(btnTruco.context.getString(R.string.truco6))
                        valorRodada = 3
                        btnSomarNos.text = valorRodada.toString()
                        btnSomarEles.text = valorRodada.toString()
                    }
                    3 -> {
                        btnTruco.text = String.format(btnTruco.context.getString(R.string.truco9))
                        valorRodada = 6
                        btnSomarNos.text = valorRodada.toString()
                        btnSomarEles.text = valorRodada.toString()

                    }
                    6 -> {
                        btnTruco.text = String.format(btnTruco.context.getString(R.string.truco12))
                        valorRodada = 9
                        btnSomarNos.text = valorRodada.toString()
                        btnSomarEles.text = valorRodada.toString()

                    }
                    9 -> {
                        btnTruco.text = String.format(btnTruco.context.getString(R.string.truco1))
                        valorRodada = 12
                        btnSomarNos.text = valorRodada.toString()
                        btnSomarEles.text = valorRodada.toString()

                    }
                    else -> {
                        btnTruco.text = String.format(btnTruco.context.getString(R.string.truco))
                        valorRodada = 1
                        btnSomarNos.text = valorRodada.toString()
                        btnSomarEles.text = valorRodada.toString()
                    }
                }
            }
            btnSomarNos.setOnClickListener {
                atualizarResultados(txtResultNos, txtResultVitoriaNos, Operacao.SOMAR, valorRodada)
                btnSomarNos.text = String.format(btnSomarNos.context.getString(R.string.soma))
                btnSomarEles.text = String.format(btnSomarEles.context.getString(R.string.soma))
            }
            btnSomarEles.setOnClickListener {
                atualizarResultados(txtResultEles, txtResultVitoriaEles, Operacao.SOMAR,valorRodada )
                btnSomarNos.text = String.format(btnSomarNos.context.getString(R.string.soma))
                btnSomarEles.text = String.format(btnSomarEles.context.getString(R.string.soma))
            }
            btnDiminuirNos.setOnClickListener {
                atualizarResultados(txtResultNos, txtResultVitoriaNos, Operacao.SUBTRAIR,1)
            }
            btnDiminuirEles.setOnClickListener {
                atualizarResultados(txtResultEles, txtResultVitoriaEles, Operacao.SUBTRAIR,1)
            }
        }
    }

    private fun atualizarResultados(
        txtResultado : TextView,
        txtResultadoVitoria : TextView,
        operacao: Operacao,
        valorDaRodada: Int
    ){
        val resultadoAtual = txtResultado.text.toString()
        var resultadoConvertido = resultadoAtual.toInt()
        val txtResultadoVitoriaVa = txtResultadoVitoria.text.toString()
        var resultadoConvertidoVitoria = txtResultadoVitoriaVa.toInt()
        resultadoConvertidoVitoria += 1
        when(operacao){
            Operacao.SOMAR -> resultadoConvertido += valorDaRodada
            Operacao.SUBTRAIR -> resultadoConvertido -= valorDaRodada
        }
        when {
            resultadoConvertido == 0 -> {
                txtResultado.text = 0.toString()
            }
            resultadoConvertido in 1..<RESULTADO_FINAL -> {
                txtResultado.text = resultadoConvertido.toString()
                binding.btnDiminuirEles.visibility = View.VISIBLE
                binding.btnDiminuirNos.visibility = View.VISIBLE
            }
            resultadoConvertido >= RESULTADO_FINAL -> {
                txtResultado.text = resultadoConvertido.toString()
                val i = Intent(this, FimActivity::class.java)
                i.putExtra("Ganhador", resultadoConvertido)
                startActivity(i)

                binding.txtResultNos.text = 0.toString()
                binding.txtResultEles.text = 0.toString()
                txtResultadoVitoria.text = resultadoConvertidoVitoria.toString()
            }
        }
        valorRodada = 1
        binding.btnTruco.text = String.format(binding.btnTruco.context.getString(R.string.truco))
    }
}




