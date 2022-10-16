package com.hakan.urunapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hakan.urunapp.R
import com.hakan.urunapp.databinding.BottomsheetFragmentBinding
import com.hakan.urunapp.databinding.FragmentSecondBinding

class BottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: BottomsheetFragmentBinding? = null
    private val binding: BottomsheetFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleClickListener()
    }


    private fun handleClickListener() {
        binding.apply {
            btnButton1.setOnClickListener {
                Toast.makeText(context, "deneme yapıyorum 1", Toast.LENGTH_SHORT).show()
            }

            btnButton2.setOnClickListener {
                Toast.makeText(context, "deneme yapıyorum 2", Toast.LENGTH_SHORT).show()
                dismiss()

            }

        }
    }
}