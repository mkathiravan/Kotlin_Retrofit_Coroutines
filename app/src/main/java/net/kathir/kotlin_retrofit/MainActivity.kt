package net.kathir.kotlin_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.kathir.kotlin_retrofit.data.api.ApiHelper
import net.kathir.kotlin_retrofit.data.api.RetrofitBuilder
import net.kathir.kotlin_retrofit.data.model.User
import net.kathir.kotlin_retrofit.ui.base.ViewModelFactory
import net.kathir.kotlin_retrofit.ui.main.adapter.MainAdapter
import net.kathir.kotlin_retrofit.ui.main.viewModel.MainViewModel
import net.kathir.kotlin_retrofit.utlis.Status

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let {resource ->
                when(resource.status)
                {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }


                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {

        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }

    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, (recyclerView.layoutManager as LinearLayoutManager).orientation))
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this,ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }
}