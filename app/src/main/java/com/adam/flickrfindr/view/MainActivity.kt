package com.adam.flickrfindr.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adam.flickrfindr.R
import com.adam.flickrfindr.viewModel.MainViewModel
import dagger.android.AndroidInjection
import dagger.android.DaggerActivity
import javax.inject.Inject

class MainActivity : DaggerActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private val recyclerView : RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.image_recycler_view)
    }

    private val layoutManager = GridLayoutManager(this, ROW_LENGTH)
    private val controller = ImageController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.search()

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = controller.adapter
    }

    companion object {
        private const val ROW_LENGTH = 4
    }
}
