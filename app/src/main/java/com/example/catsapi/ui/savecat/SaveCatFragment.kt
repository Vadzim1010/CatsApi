package com.example.catsapi.ui.savecat

import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.catsapi.R
import com.example.catsapi.databinding.FragmentSaveCatBinding
import com.example.catsapi.ui.MainActivity
import kotlin.random.Random

class SaveCatFragment : Fragment() {

    private var _binding: FragmentSaveCatBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<SaveCatFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSaveCatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide
            .with(this)
            .load(args.imageUrl)
            .error(R.drawable.ic_launcher_background)
            .into(binding.catImage)

        binding.saveCatButton.setOnClickListener {
            saveImage(binding.catImage.drawToBitmap())
        }
    }

    private fun saveImage(bitmap: Bitmap) {
        val imageFileName = "CAT_" + Random.nextInt()
        MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap,
            imageFileName,
            "CaT Image"
        )
        Toast.makeText(requireContext(), "Image Downloaded", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}