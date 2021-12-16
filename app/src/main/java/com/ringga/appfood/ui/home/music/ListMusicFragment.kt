package com.ringga.appfood.ui.home.music

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ringga.appfood.R
import com.ringga.appfood.data.adapter.LaguAdapter
//import com.ringga.appfood.data.service.MusicManager
import com.ringga.appfood.databinding.FragmentListMusicBinding

class ListMusicFragment : Fragment() {

    private var _binding: FragmentListMusicBinding? = null

    private val binding get() = _binding!!
    companion object {
        fun newInstance() = ListMusicFragment()
    }

    private lateinit var viewModel: ListMusicViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListMusicBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListMusicViewModel::class.java)
        setupRecler()
        viewModel.getAllAudioFromDevice(requireContext())
        viewModel.getState().observer(this, Observer {
            handleUiState(it)
        })
    }
    private fun setupRecler() {

        binding.rvListLagu.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = LaguAdapter(mutableListOf(), requireContext(), requireFragmentManager())

        }
    }

    private fun handleUiState(it: UserState) {
        when (it) {
            is UserState.ListLagu -> {

                binding.rvListLagu.adapter?.let { adapter ->
                    if (adapter is LaguAdapter) {
                        adapter.setLagu(it.dataLagu)
                    }
                }
            }
        }
    }

}