package com.example.myapplication.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.view.adapter.PokemonSquareAdapter
import com.example.myapplication.view.ui.ResultScreen
import com.example.myapplication.view.viewModel.mainViewModel

class FragmentList : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainVM: mainViewModel
    private lateinit var adapter: PokemonSquareAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVM = ViewModelProvider(requireActivity())[mainViewModel::class.java]
        adapter = PokemonSquareAdapter { pokemon ->
            val intent = Intent(requireContext(), ResultScreen::class.java).apply {
                putExtra("PokemonName", pokemon.name)
            }
            startActivity(intent)
        }

        binding.recycleAllList.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recycleAllList.adapter = adapter

        mainVM.requestList()
        mainVM.getPokemonList().observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }

        binding.imgUpload.setOnClickListener {
            mainVM.qtd_lista += 10
            mainVM.requestList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}