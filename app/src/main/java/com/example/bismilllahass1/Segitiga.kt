package com.example.bismilllahass1


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.example.bismilllahass1.databinding.FragmentSegitigaBinding
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt

class Segitiga : Fragment() {

    private lateinit var binding: FragmentSegitigaBinding
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)

        binding.hitungButton.setOnClickListener {
            cekHitung()
        }

        binding.shareButton.setOnClickListener {
            bagikan()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun bagikan() {
//        Toast.makeText(activity, "Hasil Berhasil Dibagikan", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_SENDTO)
        val subject = "Hasil Perrhitungan"
        val pesan = "Hasil Luas : ${binding.hasilLuasSgtTextView.text}" +
                "Hasil Keliling : ${binding.hasilKelilingSgtTextView.text}".trimIndent()

        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, pesan)
        startActivity(intent)
    }

    private fun cekHitung() {
        when {
            binding.alasSegitigaEditText.text.isEmpty() -> {
                Toast.makeText(activity, "Alas Segitiga belum terisi", Toast.LENGTH_SHORT).show()
            }
            binding.tinggiSegitigaEditText.text.isEmpty() -> {
                Toast.makeText(activity, "Tinggi Segitiga belum diisi", Toast.LENGTH_SHORT).show()
            }
            else ->
                hitung()
        }
    }

    private fun hitung() {
        val df = DecimalFormat("#.##")
        val alas = binding.alasSegitigaEditText.text.toString().toDouble()
        val tinggi = binding.tinggiSegitigaEditText.text.toString().toDouble()
        val sisiMiring = sqrt(alas.pow(2) + tinggi.pow(2))

        luas = (alas * tinggi)/2
        keliling = alas + tinggi + sisiMiring

        binding.hasilLuasSgtTextView.text = df.format(luas).toString()
        binding.hasilKelilingSgtTextView.text = df.format(keliling).toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas",luas)
        outState.putDouble("keliling", keliling)

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null){
            val df = DecimalFormat("#.##")
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            binding.hasilLuasSgtTextView.text = df.format(luas).toString()
            binding.hasilKelilingSgtTextView.text = df.format(keliling).toString()
        }

        super.onViewStateRestored(savedInstanceState)
    }
}
