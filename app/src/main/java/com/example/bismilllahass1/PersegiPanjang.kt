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
import com.example.bismilllahass1.databinding.FragmentPersegiPanjangBinding

/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjang : Fragment() {
    private lateinit var binding: FragmentPersegiPanjangBinding
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container,false)

        //set btHitung
        binding.button.setOnClickListener { checkInput() }

        //set btShare
        binding.button2.setOnClickListener { shareHasil() }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_persegi_panjang, container, false)
        return binding.root
    }

    private fun shareHasil() {
//        Toast.makeText(activity, "Hasil Berhasil Dikirim", Toast.LENGTH_SHORT).show()
        val intent = Intent(Intent.ACTION_SENDTO)
        val subject = "Hasil Perrhitungan"
        val pesan = "Hasil Luas : ${binding.hasilLuasPP.text}" +
                "Hasil Keliling : ${binding.hasilKelilingPP.text}".trimIndent()

        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, pesan)
        startActivity(intent)
    }

    private fun checkInput() {
        when{
            binding.panjangEditText.text.isEmpty()->{
                Toast.makeText(activity, "Panjang Harus Terisi", Toast.LENGTH_SHORT).show()
            }
            binding.lebarEditText.text.isEmpty()->{
                Toast.makeText(activity, "Lebar Harus Terisi", Toast.LENGTH_SHORT).show()
            }
            else -> hitunngHasil()
        }
    }

    private fun hitunngHasil() {
        var panjang: Double = binding.panjangEditText.text.toString().toDouble()
        var lebar: Double = binding.lebarEditText.text.toString().toDouble()

        luas = panjang * lebar
        keliling = 2 * (panjang + lebar)

        binding.hasilLuasPP.text = luas.toString()
        binding.hasilKelilingPP.text = keliling.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", luas)
        outState.putDouble("keliling", keliling)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState!=null){
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            binding.hasilLuasPP.text = luas.toString()
            binding.hasilKelilingPP.text = keliling.toString()
        }
        super.onViewStateRestored(savedInstanceState)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Fragment Persegi Panjang"
    }


}
