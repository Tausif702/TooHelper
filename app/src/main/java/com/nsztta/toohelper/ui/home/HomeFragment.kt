package com.nsztta.toohelper.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nsztta.toohelper.ui.screen.News_Screen_Activity
import com.nsztta.toohelper.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val handler = Handler()
    private val folderName="news"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root




        binding.date.text = homeViewModel.getDate
        binding.months.text = homeViewModel.getMonth
        binding.time.timeZone = "Asia/Kolkata"

        binding.dainikJagran.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"DJ")
            startActivity(intent)
        }
        binding.TOI.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"TOI")
            startActivity(intent)
        }
        binding.DB.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"DB")
            startActivity(intent)
        }
        binding.hindustan.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"Hindustan")
            startActivity(intent)
        }
        binding.TheHindu.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"The Hindu")
            startActivity(intent)
        }
        binding.TheHindu.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"TheHindu")
            startActivity(intent)
        }
        binding.urdu.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"Urdu")
            startActivity(intent)
        }
        binding.programmingNotes.setOnClickListener{
            val intent = Intent(requireContext(), News_Screen_Activity::class.java)
            intent.putExtra(folderName,"Programming Notes")
            startActivity(intent)
        }
        binding.Quantums.setOnClickListener{
            val intent = Intent(requireContext(),News_Screen_Activity::class.java)
            intent.putExtra(folderName,"Quantums")
            startActivity(intent)
        }
        binding.roadMap.setOnClickListener{
            val intent = Intent(requireContext(),News_Screen_Activity::class.java)
            intent.putExtra(folderName,"RoadMaps")
            startActivity(intent)
        }
        binding.hindiBook.setOnClickListener{
            val intent = Intent(requireContext(),News_Screen_Activity::class.java)
            intent.putExtra(folderName,"Hindi Books")
            startActivity(intent)
        }
        binding.upscBpsc.setOnClickListener{
            val intent = Intent(requireContext(),News_Screen_Activity::class.java)
            intent.putExtra(folderName,"UPSC/BPSC")
            startActivity(intent)
        }




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        handler.removeCallbacksAndMessages(null)
    }
}