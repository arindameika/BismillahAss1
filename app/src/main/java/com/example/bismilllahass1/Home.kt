package com.example.bismilllahass1


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bismilllahass1.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        //buat variabel data binding
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //tambahkan setHasOptionMenu(true)
        setHasOptionsMenu(true)

        //set button PP ke fragment PP
        binding.persegiPanjangButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_home3_to_persegiPanjang)
        }

        //set button Sgt ke fragmen Sgt
        binding.segitigaButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_home3_to_segitiga)
        }

        // Inflate the layout for this fragment
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Beranda"
    }


}
