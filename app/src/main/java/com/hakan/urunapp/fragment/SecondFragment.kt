package com.hakan.urunapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Dao
import com.hakan.urunapp.R
import com.hakan.urunapp.data.User
import com.hakan.urunapp.data.UserViewModel
import com.hakan.urunapp.databinding.FragmentFirstBinding
import com.hakan.urunapp.databinding.FragmentSecondBinding
import org.json.JSONObject


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = _binding!!


    private lateinit var userViewModel: UserViewModel

    /**
     * Güncellenecek olan User objesi
     * @author Sefa Tombul
     * @since 26.08.2022
     * */
    private var user: User? = null
    val bottomSheetFragment=BottomSheetFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleArguments()
    }

    /**
     * SecondFragmenta gönderilen veri varsa değişkenlerde tutulur.
     * @author Sefa Tombul
     * @since 26.08.2022
     * */
    private fun handleArguments() {
        arguments?.let {
            user = it.getParcelable("user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        handleClickListener()
    }

    /**
     * Eğer secondFragment güncelleme işlemi için açılmıssa
     * UI'daki viewlar kullanıcı bilgileri ile dolduruldu
     * insertButton'a "Güncelle" yazdırıldı
     * @param user Güncellenecek model
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun handleUpdateProcess(user: User) {
        Log.e(
            SecondFragment::class.java.name,
            "onViewCreated: id : ${user.id} name : ${user.name}"
        )

        binding.apply {
            editName.setText(user.name)
            editMarca.setText(user.marca)
            editPrice.setText(user.price.toString())
            insertButton.text = "Güncelle"
        }
    }

    /**
     * Eğer secondFragment ekleme işlemi için açılmıssa
     * insertButton'a "Ekle" yazdırıldı
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun handleAddProcess() {
        binding.apply {
            insertButton.text = "Ekle "
        }
    }

    /**
     * Gerekli tanımlamalar yapılır
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun initialize() {
        userViewModel = UserViewModel(requireContext())
        if (user != null) {
            Log.e(SecondFragment::class.java.name, "onViewCreated: guncelleme ")
            handleUpdateProcess(user!!)
        } else {
            Log.e(SecondFragment::class.java.name, "onViewCreated: ekleme ")
            handleAddProcess()
        }
    }

    /**
     * SecondFragment'daki cllick eventlarının toplandığı fonksiyon
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun handleClickListener() {
        binding.apply {
            btnShow.setOnClickListener {
                bottomSheetFragment.show(requireActivity().supportFragmentManager,"BottomSheetDialog")
                bottomSheetFragment.isCancelable = false
            }

            insertButton.setOnClickListener {
                user?.let {
                    handleInsertButtonUpdate(it)
                } ?: run {
                    handleInsertButtonAdd()
                }
                findNavController().popBackStack()
            }
        }
    }

    /**
     * Butona tıklandığında ekleme işlemi yapılacak
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun handleInsertButtonAdd() {
        binding.apply {
            userViewModel.addUser(
                User(
                    0,
                    editName.text.toString(),
                    editMarca.text.toString(),
                    editPrice.text.toString().toInt()
                )
            )
        }
    }

    /**
     * Butona tıklandığında güncelleme işlemi yapılacak
     * @author Hakan Akbudak
     * @since 26.08.2022
     * */
    private fun handleInsertButtonUpdate(user: User) {
        binding.apply {
            userViewModel.updateUser(
                User(
                    user.id,
                    editName.text.toString(),
                    editMarca.text.toString(),
                    editPrice.text.toString().toInt()
                )
            )
        }
    }
}






