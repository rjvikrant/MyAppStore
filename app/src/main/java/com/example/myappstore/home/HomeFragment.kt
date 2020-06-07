package com.example.myappstore.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myappstore.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

 /*   private val homeViewModel: HomeViewModel by lazy {

        val factory = HomeViewModelFactory(activity!!.application)
        ViewModelProviders.of(this, factory)
            .get(HomeViewModel::class.java)
    }*/

    lateinit var homeViewModel :HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = HomeViewModelFactory(activity!!.application)
        homeViewModel = ViewModelProviders.of(this, factory)
            .get(HomeViewModel::class.java)
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.viewModel = homeViewModel

        val layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerList.layoutManager = layoutManager

        binding.recyclerList.adapter = AppListAdapter(AppListAdapter.PhotoOnclickListener {

            this.findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailedFragment(
                        it.entryid,
                        it.title.label
                    )
                )
        })


        homeViewModel.response.observe(viewLifecycleOwner, Observer {
            Log.d("respone", it)

        })




        homeViewModel.eventNetworkError.observe(this, Observer { isNetworkError ->
            if (isNetworkError) {
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show()
                homeViewModel.onNetworkShown()

            }

        })

        homeViewModel.ProgressbarEvent.observe(this, Observer {
            if (it){
                progress_circular.visibility= View.VISIBLE


            }else{

                progress_circular.visibility= View.GONE
            }
        })


        binding.setLifecycleOwner(this)
        return binding.root
    }


}
