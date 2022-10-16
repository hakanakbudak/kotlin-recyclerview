package com.hakan.urunapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hakan.urunapp.R
import com.hakan.urunapp.utils.ItemSwipe
import com.hakan.urunapp.adapter.RecyclerViewAdapter
import com.hakan.urunapp.data.UserViewModel
import com.hakan.urunapp.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding get() = _binding!!

    /**
     * ing. acıklama
     * @author kim
     * @since 24.08.2022
     * */
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    /**
     * ing. acıklama
     * @author kim
     * @since 24.08.2022
     * */
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRecyclerview()
        handleClickEvents()
        subscribeObservers()

        userViewModel.readAllData()
    }

    private fun subscribeObservers() {
        userViewModel.allUserData.observe(viewLifecycleOwner) { userList ->
            if (userList.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Liste Boş!", Toast.LENGTH_SHORT).show()
            }
            recyclerViewAdapter.setData(userList)
        }
    }

    private fun setupRecyclerview() {
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = recyclerViewAdapter
        }
        val itemSwipe = ItemSwipe()
        itemSwipe.setOnSwipeListener { position ->
            showDeleteAlertPopup(position)
            Log.e("TAG", "onViewCreated: ITEM SWIPE")
        }

        val itemTouchHelper = ItemTouchHelper(itemSwipe)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

    }

    private fun showDeleteAlertPopup(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Uyarı!")
            .setMessage("Silmek istiyor musun ?")
            .setCancelable(true)
            .setPositiveButton("Evet") { dialogInterface, it ->
                deleteAlertPositiveButtonClickEvent(position)
            }
            .setNegativeButton("Hayır") { dialogInterface, it ->
                dialogInterface.dismiss()
            }.show()
    }

    private fun deleteAlertPositiveButtonClickEvent(position: Int) {
        val user = recyclerViewAdapter.userList[position]
        userViewModel.deleteUser(user)
        recyclerViewAdapter.deleteItem(user)

        Snackbar.make(binding.root, "Item Deleted", Snackbar.LENGTH_SHORT).show()
    }

    private fun initialize() {
        userViewModel = UserViewModel(requireContext())
    }

    /**
     * FirstFragment içerisindeki tüm click eventlarının toplandığı fonksiyon
     * @author Hakan ....
     * @since 24.08.2022
     * @param flag Tıklanan öğenin bayrağı
     * @param flag Tıklanan öğenin bayrağı
     * */
    private fun handleClickEvents() {
        recyclerViewAdapter.setOnClickListener { position, user ->
            val bundle = Bundle()
            bundle.putParcelable("user", user)
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
        }

        binding.fabBtn.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}





