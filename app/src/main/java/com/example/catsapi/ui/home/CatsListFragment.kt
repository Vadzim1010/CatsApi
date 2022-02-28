package com.example.catsapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsapi.CatsApp
import com.example.catsapi.adapter.CatsListAdapter
import com.example.catsapi.adapter.CatsLoaderStateAdapter
import com.example.catsapi.databinding.FragmentCatsListBinding
import com.example.catsapi.model.Cat
import com.example.catsapi.ui.CardViewClickListener
import com.example.catsapi.ui.MainActivity
import kotlinx.coroutines.launch


class CatsListFragment : Fragment(), CardViewClickListener {

    private val catAdapter = CatsListAdapter(this)
    private var _binding: FragmentCatsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatViewModel> {
        CatViewModelFactory((activity?.application as CatsApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.catsListRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = catAdapter.withLoadStateHeaderAndFooter(
                header = CatsLoaderStateAdapter(),
                footer = CatsLoaderStateAdapter(),
            )
        }

        viewModel.fetchCatGoImagesLiveData().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                catAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardViewClickListener(cat: Cat) {
        val action =
            CatsListFragmentDirections.actionCatsListFragmentToSaveCatFragment(cat.imageUrl.toString())
        findNavController().navigate(action)
    }
}