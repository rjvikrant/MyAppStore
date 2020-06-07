package com.example.myappstore.detailed_info

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.myappstore.R
import com.example.myappstore.database.getDatabase
import com.example.myappstore.databinding.DetailedFragmentBinding

class DetailedFragment : Fragment() {

    companion object {
        fun newInstance() = DetailedFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val  application = requireNotNull(this.activity).application
        val  dataSource =  getDatabase(application).entryDao
        (activity as AppCompatActivity).supportActionBar?.title=DetailedFragmentArgs.fromBundle(arguments!!).title

        val factory = DetailedViewModelFactory(DetailedFragmentArgs.fromBundle(arguments!!).entryId,activity!!.application,dataSource)
        val binding = DetailedFragmentBinding.inflate(inflater)
        val detailedViewModel = ViewModelProviders.of(this, factory).get(DetailedViewModel::class.java)
        binding.viewModel = detailedViewModel


        detailedViewModel.moreInfo.observe(this, Observer { link ->

            link?.let {
                Log.e("uri",it)
                var intent = Intent(Intent.ACTION_VIEW,it.toUri())
                detailedViewModel.DonemoreInfoClick()
                startActivity(intent)

            }
        })

        binding.setLifecycleOwner(this)
        return binding.root
    }




}
