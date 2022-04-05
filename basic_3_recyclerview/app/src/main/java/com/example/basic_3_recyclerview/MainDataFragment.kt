package com.example.basic_3_recyclerview

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_data.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_MAIN_DATA = "MAIN_DATA"


/**
 * A simple [Fragment] subclass.
 * Use the [MainDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainDataFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mainData: MainData? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mainData = it.getParcelable(ARG_MAIN_DATA)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtTitle.text = mainData!!.title // null이 아님을 개발자가 보장하겠다
        txtContent.text = mainData!!.content
        Glide.with(this).load(mainData!!.image).into(mainImage)

    }

    companion object {
        @JvmStatic
        fun newInstance(mainData: MainData) =
            MainDataFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MAIN_DATA, mainData)
                }
            }
    }
}